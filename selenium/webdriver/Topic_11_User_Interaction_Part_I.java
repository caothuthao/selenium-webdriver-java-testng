package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_11_User_Interaction_Part_I {

    WebDriver driver;
    Actions action;

    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        action = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //	@Test
    public void TC_01_Hover_Mouse_I() {
//		driver.get("https://automationfc.github.io/jquery-tooltip/");
//
//		// Hover chuột vào textbox
//		action.moveToElement(driver.findElement(By.id("age"))).perform();
//		sleepInSecond(5);
//
//		Assert.assertEquals(driver.findElement(By.cssSelector("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");

        driver.get("https://tiki.vn/");

        // Hover chuột vào Profile icon
        action.moveToElement(driver.findElement(By.cssSelector(".profile-icon"))).perform();
        sleepInSecond(5);

        // Click on Login button
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        // Verify
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='tel']")).isDisplayed());
    }

    //	@Test
    public void TC_01_Hover_Mouse_II() {
        driver.get("https://www.myntra.com/");

        // Hover chuột vào Profile icon
        action.moveToElement(driver.findElement(By.xpath(" //div[contains(@class,'desktop-navLink')]/a[text()='Kids']"))).perform();
        sleepInSecond(5);

        // Choose Home & Bath
        driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();

        // Verify
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
    }

    //	@Test
    public void TC_02_CLick_And_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Get ra list các phần tử Web Element
        List<WebElement> rectangleNumber = driver.findElements(By.cssSelector("#selectable>li"));

        // Click and hold vào element source + move đến element target + nhả chuột
        action.clickAndHold(rectangleNumber.get(0)).moveToElement(rectangleNumber.get(3)).release().perform();
        sleepInSecond(5);

        // Verify
        Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
    }

//    @Test
    public void TC_02_CLick_And_Hold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Get ra list các phần tử Web Element
        List<WebElement> rectangleNumber = driver.findElements(By.cssSelector("#selectable>li"));

        // Nhấn phím Ctrl xuống
        action.keyDown(Keys.CONTROL).perform();

        // Chọn các element 1-3-6-11
        action.click(rectangleNumber.get(0))
                .click(rectangleNumber.get(2))
                .click(rectangleNumber.get(5))
                .click(rectangleNumber.get(10)).perform();

        // Thả phím Ctrl ra
        action.keyUp(Keys.CONTROL).perform();

        sleepInSecond(3);

        // Verify
        Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
    }

    @Test
    public void TC_03_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
