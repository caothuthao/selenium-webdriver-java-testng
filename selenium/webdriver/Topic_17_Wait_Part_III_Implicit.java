package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_Part_III_Implicit {

    WebDriver driver;
    WebDriverWait explicitWait;

    String projectPath = System.getProperty("user.dir");

    By startButtonBy = By.xpath("//div[@id='start']/button");
    By loadingIconBy = By.xpath("//div[@id='loading']");
    By helloWorldTextBy = By.xpath(" //div[@id='finish']/h4");

    @BeforeClass
    public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

//        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
//        driver = new ChromeDriver();

        explicitWait = new WebDriverWait(driver, 15);

        driver.manage().window().maximize();
    }

//    @Test
    public void TC_01_Less_Than() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(startButtonBy).click();

        Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

    }

//    @Test
    public void TC_02_Enough() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(startButtonBy).click();

        Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
    }

//    @Test
    public void TC_02_Greater_Than() {
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(startButtonBy).click();

        Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
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
