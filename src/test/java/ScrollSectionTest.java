import helpers.DriverFactory;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@RunWith(Parameterized.class)
public class ScrollSectionTest {
    private final WebDriver driver;

    public ScrollSectionTest(String driverType) {
        this.driver = DriverFactory.getDriver(driverType);
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                { "chrome" },
                { "yandex" },
        };
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Тест прокрутки секций")
    public void testScrollSection() throws InterruptedException {
        openMainPage();
        checkScrollOnSauces();
        checkScrollOnFillings();
        checkScrollOnBuns();
    }

    @Step("Проверяем прокрутку до секции соусов")
    private void checkScrollOnSauces() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesButton();
        takeScreenshot(driver);
        Assert.assertTrue(mainPage.isSaucesSectionAtTop());
    }

    @Step("Проверяем прокрутку до секции начинок")
    private void checkScrollOnFillings() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsButton();
        takeScreenshot(driver);
        Assert.assertTrue(mainPage.isFillingsSectionAtTop());
    }
    @Step("Проверяем прокрутку до секции булочек")
    private void checkScrollOnBuns() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickBunsButton();
        takeScreenshot(driver);
        Assert.assertTrue(mainPage.isBunsSectionAtTop());
    }

    @Step("Открываем главную страницу")
    private void openMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
    }
}
