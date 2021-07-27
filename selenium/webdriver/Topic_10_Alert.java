package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Topic_10_Alert {

    WebDriver driver;

    Alert alert;

    WebDriverWait explicitWait;

    String projectPath = System.getProperty("user.dir");

    String authAutoIT = projectPath + "\\autoIT\\authen_firefox.exe";

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //	@Test
    public void TC_01_Accept_Alert_01() {
        driver.get("http://demo.guru99.com/v4/index.php");

        driver.findElement(By.name("btnLogin")).click();

        // Wait cho alert xuất hiện + Switch qua alert
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        sleepInSecond(5);

        // Verify alert text
        Assert.assertEquals(alert.getText(), "User or Password is not valid");

        // Accept
        alert.accept();
    }

    //	@Test
    public void TC_01_Accept_Alert_02() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        // Wait cho alert xuất hiện + Switch qua alert
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        sleepInSecond(5);

        // Verify alert text
        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        // Accept
        alert.accept();

        // Verify text in result div
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
    }

    //	@Test
    public void TC_02_Confirmation_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        // Wait cho alert xuất hiện + Switch qua alert
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        sleepInSecond(5);

        // Verify alert text
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        // Accept
//		alert.accept();

        // Cancel
        alert.dismiss();

        // Verify text in result div
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
    }

    //	@Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String fullName = "Automation FC";

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        // Wait cho alert xuất hiện + Switch qua alert
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        sleepInSecond(5);

        // Verify alert text
        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        // Send keys to alert
        alert.sendKeys(fullName);

        // Accept
        alert.accept();

        // Verify text in result div
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + fullName);
    }

    //	@Test
    public void TC_04_Authentication_Alert_01() {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        // Verify access successfully
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']/following-sibling::p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    //    @Test
    public void TC_04_Authentication_Alert_02() {
        driver.get("http://the-internet.herokuapp.com");

        String hrefValue = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        driver.get(passValueToUrl("admin", "admin", hrefValue));

        // Verify access successfully
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']/following-sibling::p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }

    @Test
    public void TC_04_Authentication_Alert_03() throws IOException {
        // Execute script trước
        Runtime.getRuntime().exec(new String[]{
                authAutoIT, "admin", "admin"
        });

        // Mở app
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        sleepInSecond(15);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Basic Auth']/following-sibling::p[contains(text(),'Congratulations! You must have the proper credentials.')]")));
        // Verify access successfully
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']/following-sibling::p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    public String passValueToUrl(String username, String password, String url) {
        String[] hrefValue = url.split("//");
        return hrefValue[0] + "//" + username + ":" + password + "@" + hrefValue[1];
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
