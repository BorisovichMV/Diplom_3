package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final String pageUrl = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver;
    private WebDriverWait wait;

    private By accountButton = By.cssSelector("a[href='/account']");
    private By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    private By callToConstructTheBurger = By.xpath("//h1[text()='Соберите бургер']");

    public String getPageUrl() {
        return pageUrl;
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }

    public void open() {
        this.driver.get(pageUrl);
    }

    public void clickAccountButton() {
        this.wait.until(ExpectedConditions.elementToBeClickable(accountButton)).click();
    }

    public void clickLoginButton() {
        this.wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isConstructorPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(callToConstructTheBurger));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
