package steps;

import entities.User;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;
import java.util.Objects;

import static steps.MainPageSteps.checkItPossibleToOpenAccountPage;
import static steps.UtilitySteps.takeScreenshot;

public class LoginPageSteps {
    @Step("Входим в аккаунт")
    public static void login(WebDriver driver, User user) {
        fillAndSubmitLoginForm(driver, user);
        checkItPossibleToOpenAccountPage(driver);
    }

    @Step("Заполняем и нажимаем на кнопку «Войти»")
    public static void fillAndSubmitLoginForm(WebDriver driver, User user) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        takeScreenshot(driver);
        loginPage.submitLoginForm();
    }

    @Step("Проверяем, что перешли на страницу авторизации")
    public static Boolean checkRedirectedToLoginPage(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.urlContains(loginPage.getPageUrl()));
        } catch (Exception ignored) {}
        takeScreenshot(driver);
        return Objects.equals(loginPage.getPageUrl(), driver.getCurrentUrl());
    }
}
