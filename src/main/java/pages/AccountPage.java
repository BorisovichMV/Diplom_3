package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private final String pageUrl = "https://stellarburgers.nomoreparties.site/account/profile";
    private WebDriver driver;
    private WebDriverWait wait;

    public String getPageUrl() {
        return pageUrl;
    }

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }
}
