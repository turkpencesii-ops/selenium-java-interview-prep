package day02;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Day02LocatorsTest {

    private WebDriver driver;

    @BeforeMethod                       // runs before EACH test (like SetUp in NUnit)
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterMethod                        // runs after EACH test (like TearDown in NUnit)
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void loginWithDifferentLocators() {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
        ).getText();
       // String message = driver.findElement(By.id("flash")).getText();
        System.out.println("Message: " + message);
        Assert.assertTrue(message.contains("You logged into a secure area!"));
    }

    @Test
    public void xpathWithText() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement heading = driver.findElement(By.xpath("//h2[contains(text(),'Login')]"));
        Assert.assertEquals(heading.getText(), "Login Page");
    }

    @Test
    public void findElementsReturnsList() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement addButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Element']")));

        for (int i = 0; i < 3; i++) {
            addButton.click();
        }

        List<WebElement> added = wait.until(
                ExpectedConditions.numberOfElementsToBe(By.className("added-manually"), 3));

        System.out.println("Buttons added: " + added.size());
        Assert.assertEquals(added.size(), 3);
    }

    @Test
    public void findElementsReturnsEmptyListNotException() {
        driver.get("https://the-internet.herokuapp.com/login");
        List<WebElement> nothing = driver.findElements(By.id("doesNotExist"));
        System.out.println("Found: " + nothing.size());
        Assert.assertTrue(nothing.isEmpty());
    }
}