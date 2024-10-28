import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static steps.AccountPageSteps.goToMainPageByConstructorButton;
import static steps.AccountPageSteps.goToMainPageByHeaderButton;
import static steps.LoginPageSteps.login;
import static steps.MainPageSteps.isMainPageOpened;
import static steps.MainPageSteps.openMainPageAndClickAccountButton;

@RunWith(Parameterized.class)
public class NavigateFromAccountToConstructorTest extends BaseTestWithCreatedUser {

    public NavigateFromAccountToConstructorTest(String driverType) {
        super(driverType);
    }

    @Test
    @DisplayName("Тест перехода из аккаунта в конструктор по клику на логотип")
    public void testNavigateFromAccountByHeaderButton() {
        openMainPageAndClickAccountButton(this.driver);
        login(this.driver, this.user);
        goToMainPageByHeaderButton(this.driver);
        Assert.assertTrue("Ожидалось открытие главной страницы", isMainPageOpened(this.driver));
    }

    @Test
    @DisplayName("Тест перехода из аккаунта в конструктор по клику на кнопку «Конструктор»")
    public void testNavigateFromAccountByConstructorButton() {
        openMainPageAndClickAccountButton(this.driver);
        login(this.driver, this.user);
        goToMainPageByConstructorButton(this.driver);
        Assert.assertTrue("Ожидалось открытие главной страницы", isMainPageOpened(this.driver));
    }
}
