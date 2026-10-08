package day04;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecureAreaPage;

public class Day04LoginPomTest extends BaseTest {

    @Test
    public void validLoginShowsSecureArea() {
        SecureAreaPage secure = new LoginPage(driver, wait)
                .open()
                .loginAs("tomsmith", "SuperSecretPassword!");
                .fehlgeschlagenen("tomsmith123", "SuperSecretPassword!");

        Assert.assertTrue(
                secure.getFlashMessage().contains("You logged into a secure area!"),
                "Login-Meldung fehlt");
    }


    @Test
    public void invalidPasswordShowsError() {
         String error = new LoginPage(driver, wait)
            .open()
            .loginWithInvalidCredentials("tomsmith", "wrongPassword")
            .getErrorMessage();

    Assert.assertTrue(error.contains(???), "Fehlermeldung fehlt: " + error);
}
}