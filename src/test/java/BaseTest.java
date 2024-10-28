import helpers.DriverFactory;
import org.junit.After;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    WebDriver driver;

    public BaseTest(String driverType) {
        this.driver = DriverFactory.getDriver(driverType);
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                { "chrome" },
                { "yandex" },
        };
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
