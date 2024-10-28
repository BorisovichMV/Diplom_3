import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static steps.MainPageSteps.*;

@RunWith(Parameterized.class)
public class ScrollSectionTest extends BaseTest {

    public ScrollSectionTest(String driverType) {
        super(driverType);
    }

    @Test
    @DisplayName("Тест прокрутки секций")
    public void testScrollOnSauces() throws InterruptedException {
        openMainPage(this.driver);
        clickOnSauces(this.driver);
        Assert.assertTrue(isSaucesSectionAtTop(this.driver));
    }

    @Test
    @DisplayName("Тест прокрутки секций")
    public void testScrollOnFillings() throws InterruptedException {
        openMainPage(this.driver);
        clickOnFillings(this.driver);
        Assert.assertTrue(isFillingsSectionAtTop(this.driver));
    }

    @Test
    @DisplayName("Тест прокрутки секций")
    public void testScrollOnBuns() throws InterruptedException {
        openMainPage(this.driver);
        clickOnFillings(this.driver);
        clickOnBuns(this.driver);
        Assert.assertTrue(isBunsSectionAtTop(this.driver));
    }
}
