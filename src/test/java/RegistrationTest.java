import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static steps.LoginPageSteps.checkRedirectedToLoginPage;
import static steps.RegistrationPageSteps.fillAndSubmitRegistrationForm;

@RunWith(Parameterized.class)
public class RegistrationTest extends BaseTestWithUser {

    private final Boolean isSuccessfulCombination;


    public RegistrationTest(Integer passwordLength, String driverType, Boolean isSuccessfulCombination) {
        super(driverType);
        this.passwordLength = passwordLength;
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


    @Test
    @DisplayName("Проверка регистрации пользователя")
    public void testRegistration() {
        fillAndSubmitRegistrationForm(this.driver, this.user);
        Assert.assertEquals(
                isSuccessfulCombination ? "Ожидался редирект на страницу входа" : "Ожидалась ошибка регистрации",
                isSuccessfulCombination,
                checkRedirectedToLoginPage(this.driver)
        );
    }
}
