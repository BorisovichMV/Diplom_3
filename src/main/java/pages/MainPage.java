package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final String pageUrl = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private By accountButton = By.cssSelector("a[href='/account']");
    private By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    private By callToConstructTheBurger = By.xpath("//h1[text()='Соберите бургер']");

    private By bunsButton = By.xpath("//span[text()='Булки']");
    private By bunsSection = By.xpath("//h2[text()='Булки']");

    private By saucesButton = By.xpath("//span[text()='Соусы']");
    private By saucesSection = By.xpath("//h2[text()='Соусы']");

    private By fillingsButton = By.xpath("//span[text()='Начинки']");
    private By fillingsSection = By.xpath("//h2[text()='Начинки']");

    private By scrollableContainer = By.xpath("//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]");


    public String getPageUrl() {
        return pageUrl;
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
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

    public void clickBunsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsButton)).click();
    }

    public void clickSaucesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesButton)).click();
    }

    public void clickFillingsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsButton)).click();
    }

    private boolean isElementAtTop(WebElement container, WebElement element) {
        Number elementPosition = (Number) js.executeScript(
                "return arguments[1].getBoundingClientRect().top - arguments[0].getBoundingClientRect().top;",
                container, element
        );
        double position = elementPosition.doubleValue();
        return position >= 0 && position <= 10;
    }
    public boolean isBunsSectionAtTop() throws InterruptedException {
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(scrollableContainer));
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(bunsSection));
        Thread.sleep(2000);
        return isElementAtTop(container, section);
    }

    public boolean isSaucesSectionAtTop() throws InterruptedException {
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(scrollableContainer));
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(saucesSection));
        Thread.sleep(2000);
        return isElementAtTop(container, section);
    }

    public boolean isFillingsSectionAtTop() throws InterruptedException {
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(scrollableContainer));
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsSection));
        Thread.sleep(2000);
        return isElementAtTop(container, section);
    }
}
