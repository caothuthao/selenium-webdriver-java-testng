package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_Textbox_Textarea_P2 {

    WebDriver driver;
    String homePageUrl;
    String firstName, lastName, emailAddress, password;
    String firstNameEdit, lastNameEdit, emailAddressEdit;
    By firstNameBy, lastNameBy, emailAddressBy, passwordBy, confirmPasswordBy;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");

        // Data Test (Create)
        firstName = "Cao";
        lastName = "Thu Thao";
        emailAddress = "thao" + generateEmailAddress();
        password = "123456";

        // Data Test (Update)
        firstNameEdit = "Cao";
        lastNameEdit = "Mai Huong";
        emailAddressEdit = "huong" + generateEmailAddress();

        // UI (Create)
        firstNameBy = By.xpath("//input[@id='FirstName']");
        lastNameBy = By.xpath("//input[@id='LastName']");
        emailAddressBy = By.xpath("//input[@id='Email']");
        passwordBy = By.xpath("//input[@id='Password']");
        confirmPasswordBy = By.xpath("//input[@id='ConfirmPassword']");
    }

    @Test
    public void TC_01_Register() {
        homePageUrl = driver.getCurrentUrl();

        // Click on Register button
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        // Enter necessary information
        driver.findElement(firstNameBy).sendKeys(firstName);
        driver.findElement(lastNameBy).sendKeys(lastName);
        driver.findElement(emailAddressBy).sendKeys(emailAddress);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(confirmPasswordBy).sendKeys(password);

        // Click Register button
        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        // Verify Register successfully
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");

        // Logout
        driver.findElement(By.xpath("//a[text()='Log out']")).click();
    }

    @Test
    public void TC_02_Login() {
        // Click on Log in button
        driver.findElement(By.xpath("//a[text()='Log in']")).click();

        // Enter email and password to login
        driver.findElement(emailAddressBy).sendKeys(emailAddress);
        driver.findElement(passwordBy).sendKeys(password);

        // Click on Login button
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        // Verify
        // CLick on My account button
        driver.findElement(By.xpath("//div[@class='header-links']//a[text()='My account']")).click();
        // Vefiry information
        Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(emailAddressBy).getAttribute("value"), emailAddress);
    }

    @Test
    public void TC_03_Update_Customer() {
        // Enter new information
        driver.findElement(firstNameBy).clear();
        driver.findElement(firstNameBy).sendKeys(firstNameEdit);
        driver.findElement(lastNameBy).clear();
        driver.findElement(lastNameBy).sendKeys(lastNameEdit);
        driver.findElement(emailAddressBy).clear();
        driver.findElement(emailAddressBy).sendKeys(emailAddressEdit);

        // CLick Save button
        driver.findElement(By.xpath("//button[@id='save-info-button']")).click();

        // Verify
        Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstNameEdit);
        Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastNameEdit);
        Assert.assertEquals(driver.findElement(emailAddressBy).getAttribute("value"), emailAddressEdit);
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

    public String generateEmailAddress() {
        Random random = new Random();
        return random.nextInt(9999) + "@mail.com";
    }

}
