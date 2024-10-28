import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static steps.AccountPageSteps.logout;
import static steps.LoginPageSteps.checkRedirectedToLoginPage;
import static steps.LoginPageSteps.login;
import static steps.MainPageSteps.openMainPageAndClickLoginButton;

@RunWith(Parameterized.class)
public class LogoutTest extends BaseTestWithCreatedUser {

    public LogoutTest(String driverType) {
        super(driverType);
    }

    @Test
    @DisplayName("Тест выхода по кнопке «Выход» на странице аккаунта")
    public void testLogout() {
        openMainPageAndClickLoginButton(this.driver);
        login(this.driver, this.user);
        logout(this.driver);
        Assert.assertTrue("URL не совпадает с ожидаемым для страницы входа", checkRedirectedToLoginPage(this.driver));
    }
}
