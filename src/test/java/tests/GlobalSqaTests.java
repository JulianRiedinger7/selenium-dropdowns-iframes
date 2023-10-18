package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
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
    public void iframesTabsTest() {
        WebElement clickHereBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-labelledby=\"tab_item-0\"] a:last-child")));
        clickHereBtn.click();

        String[] windowHandles = driver.getWindowHandles().toArray(new String[0]);

        driver.switchTo().window(windowHandles[1]);

        WebElement iFrameTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("iFrame")));

        iFrameTab.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");

        driver.switchTo().frame("globalSqa");

        WebElement trainingsDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("portfolio_filter")));
        trainingsDropdown.click();


        WebElement softwareTestingOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#filter_list li:nth-child(3)")));
        softwareTestingOption.click();

        js.executeScript("window.scrollBy(0, 200)");

        WebElement manualTestingTraining = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".softwaretesting:nth-child(4) a")));
        manualTestingTraining.click();

        WebElement pageHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Manual Testing Training']")));

        Assert.assertTrue(pageHeading.isDisplayed());
        Assert.assertEquals(pageHeading.getText(), "Manual Testing Training", "The titles does not match");

        driver.switchTo().defaultContent();

        js.executeScript("window.scrollBy(0,500)");

        WebElement alertBox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#menu-miscellaneous li:first-child a")));
        alertBox.click();

        driver.close();
        driver.switchTo().window(windowHandles[0]);
    }

    @Test(dependsOnMethods = "iframesTabsTest")
    public void alertsTest() {
        driver.get("https://demo.automationtesting.in/Alerts.html");

        WebElement alertOkCancel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tabpane li:nth-child(2)")));
        alertOkCancel.click();

        WebElement displayBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#CancelTab button")));
        displayBtn.click();

        Alert alert = driver.switchTo().alert();

        alert.dismiss();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("demo")));

        Assert.assertTrue(message.isDisplayed());
        Assert.assertEquals(message.getText(), "You Pressed Cancel", "The messages does not match");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
