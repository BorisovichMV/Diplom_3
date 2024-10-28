import entities.User;
import entities.UserLoginModel;
import helpers.DriverFactory;
import helpers.RandomStringGenerator;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;

import java.time.Duration;

import static steps.UtilitySteps.takeScreenshot;

@RunWith(Parameterized.class)
public class RegistrationTest {

    private final WebDriver driver;
    private final Integer passwordLength;
    private final Boolean isSuccessfulCombination;
    private User user;


    public RegistrationTest(Integer passwordLength, String driverType, Boolean isSuccessfulCombination) {
        this.passwordLength = passwordLength;
        this.driver = DriverFactory.getDriver(driverType);
        this.isSuccessfulCombination = isSuccessfulCombination;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                { 0, "chrome", false },
                { 1, "chrome", false },
                { 4, "chrome", false },
                { 5, "chrome", false },
                { 6, "chrome", true },
                { 7, "chrome", true },
                { 10, "chrome", true },
                { 50, "chrome", true },
                { 0, "yandex", false },
                { 1, "yandex", false },
                { 4, "yandex", false },
                { 5, "yandex", false },
                { 6, "yandex", true },
                { 7, "yandex", true },
                { 10, "yandex", true },
                { 50, "yandex", true },
        };
    }

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api";
    }

    @Before
    public void setup() {
        String name = RandomStringGenerator.generateUsername();
        String email = RandomStringGenerator.generateEmail();
        String password = RandomStringGenerator.generatePassword(passwordLength);
        this.user = new User(email, name, password);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        if (user != null && user.getAccessToken() != null) {
            deleteUser(user);
        }
    }

    @Test
    @DisplayName("Проверка регистрации пользователя")
    public void testRegistration() {
        RegistrationPage registrationPage = navigate();
        fillRegistrationForm(registrationPage, user);
        checkRedirect();
    }

    @Step("Переходим на страницу")
    private RegistrationPage navigate() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        takeScreenshot(this.driver);
        return registrationPage;
    }

    @Step("Заполняем поля и регистрируемся")
    private void fillRegistrationForm(RegistrationPage registrationPage, User user) {
        registrationPage.fillRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        takeScreenshot(this.driver);
        registrationPage.submitRegistrationForm();
    }

    @Step("Проверяем, редирект на страницу логина")
    private void checkRedirect() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.urlContains("login"));
        } catch (Exception ignored) {}

        takeScreenshot(this.driver);
        if (isSuccessfulCombination) {
            loginUser();
            Assert.assertTrue("Ожидалось, что произойдёт редирект на страницу логина", driver.getCurrentUrl().contains("login"));
        } else {
            Assert.assertFalse("Ожидалось, что не произойдёт редиректа", driver.getCurrentUrl().contains("login"));
        }
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

    @Step("Отправляем запрос на вход пользователя")
    private void loginUser() {
        UserLoginModel model = UserLoginModel.fromUser(user);
        Response response = sendRequest("POST", model, "/auth/login", 200);
        rememberTokens(response.jsonPath());
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
