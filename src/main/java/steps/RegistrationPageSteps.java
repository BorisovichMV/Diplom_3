package steps;

import entities.User;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;

import static steps.UtilitySteps.takeScreenshot;

public class RegistrationPageSteps {
    @Step("Нажимаем на кнопку «Войти» на форме регистрации")
    public static void clickLoginButtonInRegistrationPage(WebDriver driver) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.clickLoginButton();
    }

    @Step("Заполняем и отправляем форму регистрации")
    public static void fillAndSubmitRegistrationForm(WebDriver driver, User user) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.fillRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.submitRegistrationForm();

    }
}
