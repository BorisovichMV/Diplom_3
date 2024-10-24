package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private final String pageUrl = "https://stellarburgers.nomoreparties.site/forgot-password";
    private WebDriver driver;
    private WebDriverWait wait;

    private final By loginButton = By.xpath("//a[contains(@class, 'Auth_link')]");

    public String getPageUrl() {
        return pageUrl;
    }

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }

    public void open() {
        this.driver.get(pageUrl);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();
    }
}
