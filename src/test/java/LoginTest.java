import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static steps.AccountPageSteps.checkRedirectedToAccountPage;
import static steps.ForgotPasswordPageSteps.openForgotPasswordAndClickAccountButton;
import static steps.LoginPageSteps.login;
import static steps.MainPageSteps.openMainPageAndClickAccountButton;
import static steps.MainPageSteps.openMainPageAndClickLoginButton;
import static steps.RegistrationPageSteps.clickLoginButtonInRegistrationPage;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTestWithCreatedUser {

    public LoginTest(String driverType) {
        super(driverType);
    }

    @Test
    @DisplayName("Тест входа по кнопке «Войти в аккаунт» на главной")
    public void testLogin() {
        openMainPageAndClickLoginButton(this.driver);
        login(this.driver, this.user);
        Assert.assertTrue("URL не совпадает с ожидаемым для страницы личного кабинета", checkRedirectedToAccountPage(this.driver));
    }

    @Test
    @DisplayName("Тест входа через кнопку «Личный кабинет»")
    public void testPersonalAccountLogin() {
        openMainPageAndClickAccountButton(this.driver);
        login(this.driver, this.user);
        Assert.assertTrue("URL не совпадает с ожидаемым для страницы личного кабинета", checkRedirectedToAccountPage(this.driver));
    }

    @Test
    @DisplayName("Тест входа через кнопку в форме регистрации")
    public void testRegistrationLogin() {
        clickLoginButtonInRegistrationPage(this.driver);
        login(this.driver, this.user);
        Assert.assertTrue("URL не совпадает с ожидаемым для страницы личного кабинета", checkRedirectedToAccountPage(this.driver));
    }

    @Test
    @DisplayName("Тест входа через кнопку в форме восстановления пароля")
    public void testRestorePasswordLogin() {
        openForgotPasswordAndClickAccountButton(this.driver);
        login(this.driver, this.user);
        Assert.assertTrue("URL не совпадает с ожидаемым для страницы личного кабинета", checkRedirectedToAccountPage(this.driver));
    }
}
