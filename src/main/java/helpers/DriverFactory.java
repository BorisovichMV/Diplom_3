package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    public static WebDriver getDriver(String driverType) {

        switch (driverType.toLowerCase()) {
            case "chrome": return startChrome();
            case "yandex": return startYandex();
            default:
                throw new IllegalArgumentException("Unknown driver type: " + driverType);
        }
    }

    private static WebDriver startChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    private static WebDriver startYandex() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexDriver/yandexdriver.exe");
        ChromeOptions yandexOptions = new ChromeOptions();
        yandexOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        return new ChromeDriver(yandexOptions);
    }
}
