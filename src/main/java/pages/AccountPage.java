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
    private final By logo = By.xpath("//div[contains(@class, 'AppHeader_header__logo')]/a");
    private final By constructorBtn = By.xpath("//ul[contains(@class, 'AppHeader_header__list')]/li/a[@href='/']");
    private final By logoutBtn = By.xpath("//button[text()='Выход']");

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

    public void clickHeaderButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).click();
    }

    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorBtn)).click();
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
    }
}
