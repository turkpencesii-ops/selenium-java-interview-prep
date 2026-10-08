package day05;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertTrue;

public class Day05DataProviderTest extends BaseTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"wrongUser", "SuperSecretPassword!", "Your username is invalid!"},
                {"tomsmith", "wrongPassword", "Your password is invalid!"},
                {"", "", "Your username is invalid!"}
        };
    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginTest(
            String username,
            String password,
            String expectedMessage) {

        String actualMessage = new LoginPage(driver, wait)
                .open()
                .loginWithInvalidCredentials(username, password)
                .getErrorMessage();

        assertTrue(
                actualMessage.contains(expectedMessage),
                "Expected message: '" + expectedMessage +
                "', but actual message was: '" + actualMessage + "'"
        );
    }
}