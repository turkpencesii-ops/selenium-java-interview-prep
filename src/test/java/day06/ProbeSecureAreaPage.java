
package day06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProbeSecureAreaPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Den Logout-Button gibt es nur auf der Secure Area, nicht auf der Login-Seite
    private final By logoutButton = By.cssSelector("a[href='/logout']");

    public ProbeSecureAreaPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(logoutButton)
        ).isDisplayed();
    }
}