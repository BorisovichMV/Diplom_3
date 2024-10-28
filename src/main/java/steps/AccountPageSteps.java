package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AccountPage;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;

import static steps.UtilitySteps.takeScreenshot;

public class AccountPageSteps {
    @Step("Проверяем, что перешли на страницу аккаунта")
    public static Boolean checkRedirectedToAccountPage(WebDriver driver) {
        AccountPage accountPage = new AccountPage(driver);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.urlContains(accountPage.getPageUrl()));
        } catch (Exception ignored) {}
        takeScreenshot(driver);
        return Objects.equals(accountPage.getPageUrl(), driver.getCurrentUrl());
    }

    @Step("Нажимаем на кнопку «Выход» и проверяем, что вышли")
    public static void logout(WebDriver driver) {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickLogoutButton();
    }

    @Step("Проверяем переход из аккаунта в конструктор по клику на логотип")
    public static void goToMainPageByHeaderButton(WebDriver driver) {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickHeaderButton();
    }

    @Step("Проверяем переход из аккаунта в конструктор по клику на кнопку «Конструктор»")
    public static void goToMainPageByConstructorButton(WebDriver driver) {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickConstructorButton();
    }

    @Step("Проверяем правильность заполнения полей email и name на странице аккаунта")
    public static Map<String, String> getUserInfoFields(WebDriver driver) {
        AccountPage accountPage = new AccountPage(driver);
        return Map.of("name", accountPage.getName(), "email", accountPage.getEmail());
    }

}
