import entities.User;
import entities.UserLoginModel;
import entities.UserRegistrationModel;
import helpers.DriverFactory;
import helpers.RandomStringGenerator;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class NavigateToAccountTest {
    private final WebDriver driver;
    private User user;

    public NavigateToAccountTest(String driverType) {
        this.driver = DriverFactory.getDriver(driverType);
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                { "chrome" },
                { "yandex" },
        };
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api";
    }

    @Before
    public void setup() {
        String name = RandomStringGenerator.generateUsername();
        String email = RandomStringGenerator.generateEmail();
        String password = RandomStringGenerator.generatePassword(6);
        this.user = new User(email, name, password);
        createUser();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        if (user != null && user.getAccessToken() != null) {
            loginUser();
            deleteUser(user);
        }
    }

    @Test
    @DisplayName("Тест входа по кнопке «Войти в аккаунт» на главной")
    public void testNavigateToAccount() {
        openMainPageAndClickLoginButton();
        checkRedirectedToLoginPage();
        login();
        checkItPossibleToOpenAccountPage();
        checkUserInfoFields();
    }

    @Step("Проверяем правильность заполнения полей email и name на странице аккаунта")
    private void checkUserInfoFields() {
        AccountPage accountPage = new AccountPage(driver);
        String name = accountPage.getName();
        String email = accountPage.getEmail();

        Assert.assertEquals(user.getName(), name);
        Assert.assertEquals(user.getEmail().toLowerCase(), email);
    }

    @Step("Открываем главную страницу и нажимаем на кнопку «Войти в аккаунт»")
    private void openMainPageAndClickLoginButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();
    }

    @Step("Входим в аккаунт")
    private void login() {
        fillAndSubmitLoginForm();
        checkRedirectToMainPage();
    }

    @Step("Заполняем и нажимаем на кнопку «Войти»")
    private void fillAndSubmitLoginForm() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        takeScreenshot(this.driver);
        loginPage.submitLoginForm();
    }

    @Step("Проверяем, что выполнен редирект на главную страницу")
    private void checkRedirectToMainPage() {
        MainPage mainPage = new MainPage(driver);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.urlContains(mainPage.getPageUrl()));
        } catch (Exception ignored) {}
        takeScreenshot(this.driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(mainPage.getPageUrl()));
    }

    @Step("Нажимеам на кнопку «Личный кабинет»")
    private void checkItPossibleToOpenAccountPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();
        checkRedirectedToAccountPage();
    }

    @Step("Проверяем, что перешли на страницу аккаунта")
    private void checkRedirectedToAccountPage() {
        AccountPage accountPage = new AccountPage(driver);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.urlContains(accountPage.getPageUrl()));
        } catch (Exception ignored) {}
        takeScreenshot(this.driver);
        Assert.assertEquals(accountPage.getPageUrl(), driver.getCurrentUrl());
    }

    @Step("Проверяем, что перешли на страницу авторизации")
    private void checkRedirectedToLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.urlContains(loginPage.getPageUrl()));
        } catch (Exception ignored) {}
        takeScreenshot(this.driver);
        Assert.assertEquals(loginPage.getPageUrl(), driver.getCurrentUrl());
    }

    @Step("Отправляем запрос на создание пользователя")
    private void createUser() {
        Response response = sendRequest("POST", UserRegistrationModel.fromUser(user), "/auth/register", 200);
        rememberTokens(response.jsonPath());
    }

    @Step("Отправляем запрос на вход пользователя")
    private void loginUser() {
        UserLoginModel model = UserLoginModel.fromUser(user);
        Response response = sendRequest("POST", model, "/auth/login", 200);
        rememberTokens(response.jsonPath());
    }

    @Step("Запоминаем токены")
    private void rememberTokens(JsonPath jsonPath) {
        boolean isSuccessful = jsonPath.getBoolean("success");
        Assert.assertTrue(isSuccessful);
        String accessToken = jsonPath.getString("accessToken");
        String refreshToken = jsonPath.getString("refreshToken");
        this.user.setAccessToken(accessToken);
        this.user.setRefreshToken(refreshToken);
    }

    @Step("Отправляем запрос")
    private Response sendRequest(String method, Object obj, String uri, Integer statusCode) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(obj)
                .log().body()
                .when()
                .request(method, uri)
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
    }

    @Step("Отправляем запрос с авторизацией")
    private Response sendAuthorizedRequest(String method, Object obj, String uri, Integer statusCode) {
        if (method.equals("DELETE")) {
            return RestAssured.given()
                    .header("Authorization", user.getAccessToken())
                    .when()
                    .request(method, uri)
                    .then()
                    .log().body()
                    .statusCode(statusCode)
                    .extract().response();
        }
        return RestAssured.given()
                .header("Authorization", user.getAccessToken())
                .header("Content-Type", "application/json")
                .body(obj)
                .log().body()
                .when()
                .request(method, uri)
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
    }

    @Step("Удаляем пользователя")
    private void deleteUser(User user) {
        sendAuthorizedRequest("DELETE", user, "/auth/user", 202);
    }
}
