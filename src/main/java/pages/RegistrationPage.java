package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final String pageUrl = "https://stellarburgers.nomoreparties.site/register";
    private WebDriver driver;
    private WebDriverWait wait;

    private final By nameField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    private final By emailField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    private final By passwordField = By.name("Пароль");
    private final By registrationForm = By.xpath("//form");
    private final By inputFields = By.xpath("//input");
    private final By submitButton = By.xpath("//button[contains(@class, 'button_button')]");
    private final By loginButton = By.xpath("//a[contains(@class, 'Auth_link')]");

    public String getPageUrl() {
        return pageUrl;
    }

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }

    public void open() {
        this.driver.get(pageUrl);
    }

    public void fillRegistrationForm(String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(registrationForm, inputFields));
        driver.findElement(this.nameField).sendKeys(name);
        driver.findElement(this.emailField).sendKeys(email);
        driver.findElement(this.passwordField).sendKeys(password);
    }

    public void submitRegistrationForm() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        button.click();
    }

    public void clickLoginButton() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        button.click();
    }
}