package day03;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day03WaitsTest extends BaseTest {

    private static final String BASE_URL = "https://the-internet.herokuapp.com";
    private static final By START_BUTTON = By.cssSelector("#start button");
    private static final By FINISH_TEXT  = By.id("finish");
    private static final By LOADING      = By.id("loading");

    @Test
    public void textIsEmptyWithoutWait() {
        driver.get(BASE_URL + "/dynamic_loading/1");
        driver.findElement(START_BUTTON).click();

        String text = driver.findElement(FINISH_TEXT).getText();
        Assert.assertEquals(text, "", "Ohne Wait sollte der Text noch leer sein");
    }

    @Test
    public void helloWorldAppearsWithExplicitWait() {
        driver.get(BASE_URL + "/dynamic_loading/1");

        wait.until(ExpectedConditions.elementToBeClickable(START_BUTTON)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(LOADING));

        String text = wait.until(
                ExpectedConditions.visibilityOfElementLocated(FINISH_TEXT)
        ).getText();

        Assert.assertEquals(text, "Hello World!", "Unerwarteter Text nach dem Laden");
    }
}