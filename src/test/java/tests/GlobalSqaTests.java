package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class GlobalSqaTests {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    @BeforeClass
    public void beforeClass() {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/frames-and-windows/");
    }

    @Test
    public void firstTest() {
        WebElement clickHereBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-labelledby=\"tab_item-0\"] a:last-child")));
        clickHereBtn.click();

        String[] windowHandles = driver.getWindowHandles().toArray(new String[0]);

        driver.switchTo().window(windowHandles[1]);

        WebElement iFrameTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("iFrame")));

        iFrameTab.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
