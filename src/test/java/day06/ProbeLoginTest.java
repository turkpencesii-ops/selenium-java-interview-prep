
package day06;

import day06.ProbeBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import day06.ProbeLoginPage;

public class ProbeLoginTest extends ProbeBaseTest {

    private ProbeLoginPage loginPage;

    @BeforeMethod
    public void setUpPage() {
        loginPage = new ProbeLoginPage(driver, wait);
    }

    @Test
    public void validLogin() {
        ProbeSecureAreaPage secureArea = loginPage
                .open()
                .login("tomsmith", "SuperSecretPassword!");

        Assert.assertTrue(
                secureArea.isDisplayed(),
                "Secure Area page not shown"
        );
    }

    @Test
    public void invalidPassword() {
        loginPage
                .open()
                .login("tomsmith", "wrong_password");

        Assert.assertTrue(
                loginPage.getFlashMessage()
                        .contains("Your password is invalid!"),
                "Expected invalid password message was not displayed"
        );
    }
}
