package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_13_IFrame_Frame {

    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
        driver = new ChromeDriver();

        javascriptExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

//    @Test
    public void TC_01_Iframe() {
        driver.get("https://kyna.vn/");

        scrollToButtomPage();

        // Switch qua Facebook Iframe
//        driver.switchTo().frame(0);
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage div iframe")));

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "168K lượt thích");

        // Switch tp parent
        driver.switchTo().defaultContent();

        // Switch qua chat Iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));

        // Click on title to show chat
        driver.findElement(By.cssSelector(".button_bar")).click();
        sleepInSecond(5);

        // Click on Submit button on Iframe
        driver.findElement(By.cssSelector("input.submit")).click();
        sleepInSecond(2);

        // Verify message
        Assert.assertEquals(driver.findElement(By.cssSelector("input.input_name+div.error_message")).getText(), "Tên của bạn chưa được nhập");
        Assert.assertEquals(driver.findElement(By.cssSelector("#serviceSelect+div.error_message")).getText(), "Bạn chưa chọn dịch vụ hỗ trợ");

        // Switch tp parent
        driver.switchTo().defaultContent();

        // Enter keyword to search form
        driver.findElement(By.cssSelector("#live-search-bar")).sendKeys("Excel");

        // CLick on search icon
        driver.findElement(By.cssSelector("button.search-button>img")).click();

        // Verify
        List<WebElement> listResult = driver.findElements(By.cssSelector("div.content h4"));
        Assert.assertEquals(listResult.size(), 9);
        for (WebElement element: listResult) {
            Assert.assertTrue(element.getText().contains("Excel"));
        }
    }

//    @Test
    public void TC_02_Frame() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        // Switch to login page
        driver.switchTo().frame("login_page");

        // Send key to login form
        driver.findElement(By.cssSelector(".input_password")).sendKeys("automationfc");

        // Click continue button
        driver.findElement(By.cssSelector(".lForm img[alt='continue']")).click();

        // Verify password field is display
        Assert.assertTrue(driver.findElement(By.cssSelector("input[name='fldPassword']")).isDisplayed());

        // Switch to parent
        driver.switchTo().defaultContent();

        // Switch to footer frame
        driver.switchTo().frame("footer");

        // Click on Term and condition
        driver.findElement(By.xpath("//form[@class='width']//a[text()='Terms and Conditions']")).click();
    }

    public void scrollToButtomPage() {
        javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
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
