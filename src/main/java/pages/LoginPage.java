package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final String pageUrl = "https://stellarburgers.nomoreparties.site/login";
    private WebDriver driver;
    private WebDriverWait wait;

    private final By emailField = By.name("name");
    private final By passwordField = By.name("Пароль");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registrationForm = By.xpath("//form");
    private final By inputFields = By.xpath("//input");

    public String getPageUrl() {
        return pageUrl;
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }

    public void open() {
        this.driver.get(pageUrl);
    }

    public void fillLoginForm(String login, String password) {
        wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(registrationForm, inputFields));
        driver.findElement(emailField).sendKeys(login);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void submitLoginForm() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

}
