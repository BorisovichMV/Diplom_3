import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Map;

import static steps.AccountPageSteps.getUserInfoFields;
import static steps.LoginPageSteps.login;
import static steps.MainPageSteps.openMainPageAndClickLoginButton;

@RunWith(Parameterized.class)
public class NavigateToAccountTest extends BaseTestWithCreatedUser {

    public NavigateToAccountTest(String driverType) {
        super(driverType);
    }

    @Test
    @DisplayName("Тест входа по кнопке «Войти в аккаунт» на главной")
    public void testNavigateToAccount() {
        openMainPageAndClickLoginButton(this.driver);
        login(this.driver, this.user);
        Map<String, String> userInfoFields = getUserInfoFields(this.driver);
        Assert.assertEquals(user.getName(), userInfoFields.get("name"));
        Assert.assertEquals(user.getEmail().toLowerCase(), userInfoFields.get("email"));
    }
}
