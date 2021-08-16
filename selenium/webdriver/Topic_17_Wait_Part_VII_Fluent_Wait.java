package webdriver;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_Part_VII_Fluent_Wait {

    WebDriver driver;

    WebDriverWait explicitWait;

    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    //    @Test
    public void TC_01() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        waitForElementAndClick(By.cssSelector("div#start>button"));

        Assert.assertTrue(waitForElementAndDisplay(By.cssSelector("div#finish>h4")));
    }

//    @Test
    public void TC_02_Implicit() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")).isDisplayed());
    }

//    @Test
    public void TC_03_Explicit() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        explicitWait = new WebDriverWait(driver, 13);

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")).isDisplayed());
    }

//    @Test
    public void TC_04_Fluent() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countDownElement = getWebElement(By.cssSelector("#javascript_countdown_time"));

        FluentWait<WebElement> wait = new FluentWait<WebElement>(countDownElement);

        wait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        return element.getText().endsWith("00");
                    }
                });
    }

    /**
     * Hàm sử dụng Fluent Wait, có thể tự set timeout, set interval
     * Dùng để find element (gần giống hàm find element mà bị ảnh hưởng bởi implicit wait)
     *
     * @param locator
     * @return Web Element
     */
    public WebElement getWebElement(By locator) {
        //  Khai báo và khởi tạo Fluent Wait
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                // Tổng thời gian chờ là bao nhêu giây
                .withTimeout(Duration.ofSeconds(15))
                // Thời gian để lặp lại là bao nhiêu giây
                .pollingEvery(Duration.ofSeconds(1))
                // Nếu sau mỗi lần lặp mà gặp exception thì sẽ ignore
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(locator);
            }
        });
        return element;
    }

    /**
     * Hàm sử dụng Fluent Wait, có thể tự set timeout, set interval
     * Dùng để find element (gần giống hàm find element mà bị ảnh hưởng bởi implicit wait) + Click vào element đó
     *
     * @param locator
     */
    public void waitForElementAndClick(By locator) {
        WebElement element = getWebElement(locator);
        element.click();
    }

    /**
     * Hàm sử dụng Fluent Wait, có thể tự set timeout, set interval
     * DÙng để thao tác với element có locator là tham số truyền vào, kiểm tra xem element đó có display hay không.
     *
     * @param locator
     * @return isDisplayed
     */
    public boolean waitForElementAndDisplay(By locator) {
        WebElement element = getWebElement(locator);

        FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                boolean flag = element.isDisplayed();
                return flag;
            }
        });
        return isDisplayed;
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
