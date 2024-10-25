package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private final String pageUrl = "https://stellarburgers.nomoreparties.site/account/profile";
    private WebDriver driver;
    private WebDriverWait wait;

    private final By nameField = By.xpath("//input[@name='Name']");
    private final By emailField = By.xpath("//label[text()='Логин']/following-sibling::input[@name='name']");

    public String getPageUrl() {
        return pageUrl;
    }

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }

    public String getName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).getAttribute("value");
    }

    public String getEmail() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).getAttribute("value");
    }
}
