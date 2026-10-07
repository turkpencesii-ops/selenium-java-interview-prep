package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecureAreaPage {

    // 1. Locator
    private final By flashMessage = By.id("flash");

    // 2. Felder
    private final WebDriver driver;
    private final WebDriverWait wait;

    // 3. Konstruktor
    public SecureAreaPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // 4. Methode: gibt den Text zurück, prüft NICHTS
    public String getFlashMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(flashMessage)
        ).getText();
    }
}