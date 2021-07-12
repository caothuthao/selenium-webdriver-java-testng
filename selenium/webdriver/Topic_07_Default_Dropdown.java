package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Default_Dropdown {

    WebDriver driver;
    Select select;
    // Cho phép thực thi được đoạn code JS trong trình duyệt
    JavascriptExecutor javascriptExecutor;
    String firstName, lastName, email, companyName, day, month, year, password;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Data Test
        firstName = "Cao";
        lastName = "Thu Thao";
        email = "thao" + generateEmailAddress();
        companyName = "ABC Corporation";
        day = "8";
        month = "September";
        year = "1999";
        password = "123456";
    }

    @Test
    public void TC_01_NopCommerce() {
        // Open Home Page
        driver.get("https://demo.nopcommerce.com/");

        // Click on Register button to go to register page
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        sleepInSecond(2);

        // Enter information
        // First Name
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);

        // Last Name
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);

        // Init select variable which is reference to element
        select = new Select(driver.findElement(By.name("DateOfBirthDay")));

        // Select Day:
        // Select value in dropdown --> 3 ways
//		select.selectByIndex(0);
//		select.selectByValue("");
        select.selectByVisibleText(day);

        // Get all option tag --> can verify size of dropdown
        select.getOptions();
        Assert.assertEquals(select.getOptions().size(), 32);

        // Check is multiple dropdown or not
        select.isMultiple();
        Assert.assertFalse(select.isMultiple());

        // Check: select true item
        select.getFirstSelectedOption().getText();
        Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

        // Select Month:
        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        select.selectByVisibleText(month);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
        Assert.assertEquals(select.getOptions().size(), 13);
        // Select Year:
        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        select.selectByVisibleText(year);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
        Assert.assertEquals(select.getOptions().size(), 112);

        // Email
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);

        // Company Name
        driver.findElement(By.xpath("//input[@id='Company']")).sendKeys(companyName);

        // Password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);

        // Confirm password
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);

        // Click on Register button
        clickByJS(By.xpath("//button[@id='register-button']"));
        sleepInSecond(2);

        // Verify Register Successfully
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");

        // Verify information
        // Click on My account button
        driver.findElement(By.xpath("//a[@class='ico-account']")).click();
        sleepInSecond(2);

        // Verify data
        // First Name
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), lastName);

        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), email);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Company']")).getAttribute("value"), companyName);
    }

    @Test
    public void TC_02_Rode() {
        // Open Home Page
        driver.get("https://www.rode.com/wheretobuy");

        // Check isMultiple
        select = new Select(driver.findElement(By.xpath("//select[@id='where_country']")));
        Assert.assertFalse(select.isMultiple());

        // Select Vietnam
        select.selectByVisibleText("Vietnam");

        // Verify value is selected successfully
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");

        // Click on Search button
        driver.findElement(By.xpath("//input[@id='search_loc_submit']")).click();

        // Verify result
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result_count']/span")).getText(), "28");

        // Print all Distributor Name
        List<WebElement> distributorNameList = driver.findElements(By.xpath("//div[@class='store_name']"));
        List<String> distributorNameTextList = new ArrayList<>();
        for (WebElement we : distributorNameList) {
            distributorNameTextList.add(we.getText());
        }
        for (Integer i = 0; i < distributorNameTextList.size(); i++) {
            System.out.println("Name " + (i + 1) + ": " + distributorNameTextList.get(i));
        }
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

    public void clickByJS(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }

}
