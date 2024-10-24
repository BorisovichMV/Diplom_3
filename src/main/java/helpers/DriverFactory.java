package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    public static WebDriver getDriver(String driverType) {

        switch (driverType.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
//                options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                return new ChromeDriver(options);

            case "yandex":
                System.setProperty("webdriver.chrome.driver", "C:\\Diplom_3\\src\\main\\resources\\yandexDriver\\yandexdriver.exe");
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                return new ChromeDriver(yandexOptions);

            default:
                throw new IllegalArgumentException("Unknown driver type: " + driverType);
        }
    }
}
