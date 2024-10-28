package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class MainPageSteps {

    @Step("Открываем главную страницу и нажимаем на кнопку «Личный кабинет»")
    public static void openMainPageAndClickAccountButton(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
    }

    @Step("Открываем главную страницу и нажимаем на кнопку «Войти в аккаунт»")
    public static void openMainPageAndClickLoginButton(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();
    }

    @Step("Открываем «Личный кабинет»")
    public static void checkItPossibleToOpenAccountPage(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();
    }

    @Step("Проверяем, что открыта главная страница")
    public static Boolean isMainPageOpened(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        return mainPage.isConstructorPageOpened();
    }

    @Step("Открываем главную страницу")
    public static void openMainPage(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Step("Нажимаем на прокрутку до секции соусов")
    public static void clickOnSauces(WebDriver driver) throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesButton();
    }

    @Step("Нажимаем на прокрутку до секции начинок")
    public static void clickOnFillings(WebDriver driver) throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsButton();
    }
    @Step("Нажимаем на прокрутку до секции булочек")
    public static void clickOnBuns(WebDriver driver) throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickBunsButton();
    }

    @Step("Проверяем прокрутку до секции соусов")
    public static Boolean isSaucesSectionAtTop(WebDriver driver) throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        return mainPage.isSaucesSectionAtTop();
    }

    @Step("Проверяем прокрутку до секции начинок")
    public static Boolean isFillingsSectionAtTop(WebDriver driver) throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        return mainPage.isFillingsSectionAtTop();
    }
    @Step("Проверяем прокрутку до секции булочек")
    public static Boolean isBunsSectionAtTop(WebDriver driver) throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        return mainPage.isBunsSectionAtTop();
    }
}
