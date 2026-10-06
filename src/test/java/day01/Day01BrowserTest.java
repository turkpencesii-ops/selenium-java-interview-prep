package day01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day01BrowserTest {

    @Test
    public void openPageAndCheckTitle() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL:   " + driver.getCurrentUrl());
        Assert.assertEquals(driver.getTitle(), "The Internet");
        driver.quit();
    }

    @Test
    public void navigation() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Checkboxes")).click();
        System.out.println("After click:   " + driver.getCurrentUrl());
        driver.navigate().back();
        System.out.println("After back:    " + driver.getCurrentUrl());
        driver.navigate().forward();
        System.out.println("After forward: " + driver.getCurrentUrl());
        driver.navigate().refresh();
        driver.quit();
    }

    @Test
    public void closeVersusQuit() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.findElement(By.linkText("Click Here")).click();
        System.out.println("Windows open:          " + driver.getWindowHandles().size());

        driver.close();          // closes ONLY the current window
        Thread.sleep(2000);      // only so you can SEE it, never in real tests
        System.out.println("Windows after close(): " + driver.getWindowHandles().size());

        driver.quit();           // closes EVERYTHING and ends the session
    }
}
