package day03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Day03WaitsTest {

    private static final String BASE_URL = "https://the-internet.herokuapp.com";
    private static final By START_BUTTON = By.cssSelector("#start button");
    private static final By FINISH_TEXT  = By.id("finish");

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ERWARTET ROT: zeigt, warum man einen Wait braucht
    @Test
    public void textIsEmptyWithoutWait() {
        driver.get(BASE_URL + "/dynamic_loading/1");
        driver.findElement(START_BUTTON).click();

        String text = driver.findElement(FINISH_TEXT).getText();
        System.out.println("Ohne Wait: '" + text + "'");

        Assert.assertEquals(text, "", "Text war beim Lesen noch nicht sichtbar");
    }

    // ERWARTET GRÜN
    @Test
    public void helloWorldAppearsWithExplicitWait() {
        driver.get(BASE_URL + "/dynamic_loading/1");
        driver.findElement(START_BUTTON).click();

        String text = wait.until(
                ExpectedConditions.visibilityOfElementLocated(FINISH_TEXT)
        ).getText();
        System.out.println("Mit Wait: '" + text + "'");

        Assert.assertEquals(text, "Hello World!", "Unerwarteter Text nach dem Laden");
    }
}


