package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_08_Custom_Dropdown_II {

    WebDriver driver;

    // Inject JS code
    JavascriptExecutor jsExecutor;

    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
        driver = new ChromeDriver();

        // Ép kiểu tường minh
        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_JQuery() {
        // Open Home Page
        driver.get("http://indrimuska.github.io/jquery-editable-select/");

        // select value in dropdown
        driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("Audi");
        driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys(Keys.TAB);

        // Verify
        String inputValue = (String) jsExecutor.executeScript("return document.querySelector('#default-place input').value");
        Assert.assertEquals(inputValue, "Audi");
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
