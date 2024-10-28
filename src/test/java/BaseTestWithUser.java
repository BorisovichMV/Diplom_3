import entities.User;
import helpers.RandomStringGenerator;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import static steps.UserSteps.deleteUser;
import static steps.UserSteps.loginUser;

public class BaseTestWithUser extends BaseTest {
    User user;
    Integer passwordLength;

    public BaseTestWithUser(String driverType) {
        super(driverType);
    }

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api";
    }

    @Before
    public void setup() {
        String name = RandomStringGenerator.generateUsername();
        String email = RandomStringGenerator.generateEmail();
        String password = RandomStringGenerator.generatePassword(passwordLength);
        this.user = new User(email, name, password);
    }

    @After
    public void teardown() {
        if (this.driver != null) {
            this.driver.quit();
        }
        if (this.user != null && this.user.getAccessToken() != null) {
            loginUser(this.user);
            deleteUser(this.user);
        }
    }
}
