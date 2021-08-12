package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_Part_V_Explicit {

    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");


    By startButtonBy = By.xpath("//div[@id='start']/button");
    By loadingIconBy = By.xpath("//div[@id='loading']");
    By helloWorldTextBy = By.xpath(" //div[@id='finish']/h4");

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

//    @Test
    public void TC_01_Less_Than() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver, 15);

        // Trước khi click thì wait cho start button có thể click được chưa
        explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
        driver.findElement(startButtonBy).click();

        explicitWait = new WebDriverWait(driver, 3);

        // 1 - Wait cho Loading Icon biến mất
//        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconBy));

        // 2 - Wait cho Hello World text hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldTextBy));

        Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
    }

//    @Test
    public void TC_02_Enough() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver, 15);

        // Trước khi click thì wait cho start button có thể click được chưa
        explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
        driver.findElement(startButtonBy).click();

        explicitWait = new WebDriverWait(driver, 5);

        // 1 - Wait cho Loading Icon biến mất
//        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconBy));

        // 2 - Wait cho Hello World text hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldTextBy));

        Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
    }

//    @Test
    public void TC_03_Greater_Than() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver, 15);

        // Trước khi click thì wait cho start button có thể click được chưa
        explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
        driver.findElement(startButtonBy).click();

        explicitWait = new WebDriverWait(driver, 15);

        // 1 - Wait cho Loading Icon biến mất
//        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconBy));

        // 2 - Wait cho Hello World text hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldTextBy));

        Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
    }

//    @Test
    public void TC_04_Ajax_Loading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        By dateTimePickerBy = By.xpath("//div[contains(@class, 'RadCalendar')]");
        By chosenDateBy = By.xpath("//legend[text()='Selected Dates:']/following-sibling::div/span");
        By todayBy = By.xpath("//a[text()='12']");
        By ajaxLoadingIconBy = By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']");

        explicitWait = new WebDriverWait(driver, 15);

        // Wait cho calender hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(dateTimePickerBy));

        // Verify text in chosen date text box
        Assert.assertEquals(driver.findElement(chosenDateBy).getText(), "No Selected Dates to display.");

        // Wait cho ngày hiện tại hiển thị trước khi click
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(todayBy));
        driver.findElement(todayBy).click();

        // 1 - Wait cho đến khi Ajax loading icon ko còn visible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoadingIconBy));

        // 2 - Wait cho đến khi today được chọn visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='12']")));

        Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='12']")).isDisplayed());

        // Verify today text
        Assert.assertEquals(driver.findElement(chosenDateBy).getText(), "Thursday, August 12, 2021");
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
