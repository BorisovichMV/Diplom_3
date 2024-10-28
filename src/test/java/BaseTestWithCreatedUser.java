import org.junit.Before;

import static steps.UserSteps.createUser;

public class BaseTestWithCreatedUser extends BaseTestWithUser {

    public BaseTestWithCreatedUser(String driverType) {
        super(driverType);
    }

    @Before
    public void setup() {
        this.passwordLength = 6;
        super.setup();
        createUser(this.user);
    }

}
