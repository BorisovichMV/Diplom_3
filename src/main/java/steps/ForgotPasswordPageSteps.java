package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.ForgotPasswordPage;

public class ForgotPasswordPageSteps {
    @Step("Открываем страницу восстановления пароля и нажимаем на кнопку «Войти»")
    public static void openForgotPasswordAndClickAccountButton(WebDriver driver) {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.open();
        forgotPasswordPage.clickLoginButton();
    }
}
