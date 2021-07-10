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

public class Topic_06_Textbox_TextArea {

    WebDriver driver;
    String loginPageUrl;
    String emailAddress, userId, password, name, gender, dateOfBirth, address, city, state, pin, mobileNumber, customerID;
    String addressEdit, cityEdit, stateEdit, pinEdit, mobileNumberEdit, emailAddressEdit;
    By nameTextboxBy, genderMaleRadioBy, genderFemaleRadioBy, genderTextboxBy, dateOfBirthBy, dateOfBirthTextboxBy, addressAreaBy, cityTextboxBy, stateTextboxBy, pinTextboxBy, mobileNumberTextboxBy, emailAddressTextboxBy, passwordTextboxBy;
    By nameTextboxVerifyBy, genderTextboxVerifyBy, dobTextboxVerifyBy, addressAreaVerifyBy, cityTextboxVerifyBy, stateTextboxVerifyBy, pinTextboxVerifyBy, mobileNumberTextboxVerifyBy, emailAddressTextboxVerifyBy;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://demo.guru99.com/v4");

        // Data Test (New Customer)
        emailAddress = "thao" + generateEmailAddress();
        name = "Cao Thu Thao";
        gender = "female";
        dateOfBirth = "1999-08-09";
        address = "Times city Minh Khai HBT";
        city = "Hanoi";
        state = "Park Hill";
        pin = "100000";
        mobileNumber = "0123321123";

        // Data Test (Edit Customer)
        addressEdit = "HB7 Minh Khai HBT";
        cityEdit = "Quang Ninh";
        stateEdit = "Hai Ha";
        pinEdit = "100001";
        mobileNumberEdit = "0123321178";
        emailAddressEdit = "thao" + generateEmailAddress();

        // UI (New Customer/ Edit Customer)
        nameTextboxBy = By.xpath("//input[@name='name']");
        genderMaleRadioBy = By.xpath("//input[@value='m']");
        genderFemaleRadioBy = By.xpath("//input[@value='f']");
        genderTextboxBy = By.xpath("//input[@name='gender']");
        dateOfBirthBy = By.xpath("//input[@id='dob']");
        dateOfBirthTextboxBy = By.xpath("//input[@name='dob']");
        addressAreaBy = By.xpath("//textarea[@name='addr']");
        cityTextboxBy = By.xpath("//input[@name='city']");
        stateTextboxBy = By.xpath("//input[@name='state']");
        pinTextboxBy = By.xpath("//input[@name='pinno']");
        mobileNumberTextboxBy = By.xpath("//input[@name='telephoneno']");
        emailAddressTextboxBy = By.xpath("//input[@name='emailid']");
        passwordTextboxBy = By.xpath("//input[@name='password']");

        // UI (Verify after create/ edit customer)
        nameTextboxVerifyBy = By.xpath("//td[text()='Customer Name']/following-sibling::td");
        genderTextboxVerifyBy = By.xpath("//td[text()='Gender']/following-sibling::td");
        dobTextboxVerifyBy = By.xpath("//td[text()='Birthdate']/following-sibling::td");
        addressAreaVerifyBy = By.xpath("//td[text()='Address']/following-sibling::td");
        cityTextboxVerifyBy = By.xpath("//td[text()='City']/following-sibling::td");
        stateTextboxVerifyBy = By.xpath("//td[text()='State']/following-sibling::td");
        pinTextboxVerifyBy = By.xpath("//td[text()='Pin']/following-sibling::td");
        mobileNumberTextboxVerifyBy = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
        emailAddressTextboxVerifyBy = By.xpath("//td[text()='Email']/following-sibling::td");
    }

    @Test
    public void TC_01_Register() {
        loginPageUrl = driver.getCurrentUrl();

        // Open register page
        driver.findElement(By.xpath("//a[text()='here']")).click();

        // Enter email address to register
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailAddress);

        // Click on Submit button
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        // Get userId and password
        userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
    }

    @Test
    public void TC_02_Login() {
        // Open login page
        driver.get(loginPageUrl);

        // Enter UserId and password to login
        driver.findElement(By.xpath("//input[@name='uid']")).clear();
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userId);
        driver.findElement(By.xpath("//input[@name='password']")).clear();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);

        // Click to Login button
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        // Verify Login Successfully
        Assert.assertEquals(driver.findElement(By.xpath("//marquee")).getText(), "Welcome To Manager's Page of Guru99 Bank");
    }

    @Test
    public void TC_03_Create_New_User() {
        // Click on New Customer to enter new information
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        sleepInSecond(3);
        
        // Enter information
        driver.findElement(nameTextboxBy).sendKeys(name);

        if(gender.equals("female")){
            driver.findElement(genderFemaleRadioBy).click();
        } else {
            driver.findElement(genderMaleRadioBy).click();
        }

        driver.findElement(dateOfBirthBy).sendKeys(dateOfBirth);
        driver.findElement(addressAreaBy).sendKeys(address);
        driver.findElement(cityTextboxBy).sendKeys(city);
        driver.findElement(stateTextboxBy).sendKeys(state);
        driver.findElement(pinTextboxBy).sendKeys(pin);
        driver.findElement(mobileNumberTextboxBy).sendKeys(mobileNumber);
        driver.findElement(emailAddressTextboxBy).clear();
        driver.findElement(emailAddressTextboxBy).sendKeys(emailAddress);
        driver.findElement(passwordTextboxBy).clear();
        driver.findElement(passwordTextboxBy).sendKeys(password);

        // Click on submit button
        driver.findElement(By.xpath("//input[@name='sub']")).click();

        // Verify successful message
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");

       // Get customer ID
        customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

        // Verify successful information
        Assert.assertEquals(driver.findElement(nameTextboxVerifyBy).getText(), name);
        Assert.assertEquals(driver.findElement(genderTextboxVerifyBy).getText(), gender);
        Assert.assertEquals(driver.findElement(dobTextboxVerifyBy).getText(), dateOfBirth);
        Assert.assertEquals(driver.findElement(addressAreaVerifyBy).getText(), address);
        Assert.assertEquals(driver.findElement(cityTextboxVerifyBy).getText(), city);
        Assert.assertEquals(driver.findElement(stateTextboxVerifyBy).getText(), state);
        Assert.assertEquals(driver.findElement(pinTextboxVerifyBy).getText(), pin);
        Assert.assertEquals(driver.findElement(mobileNumberTextboxVerifyBy).getText(), mobileNumber);
        Assert.assertEquals(driver.findElement(emailAddressTextboxVerifyBy).getText(), emailAddress);
    }

    @Test
    public void TC_04_Update_User() {
        // Click on Edit Customer button
        driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

        // Enter customer ID
        driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);

        // Click on Submit button
        driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

        // Verify disable field
        // Name
        Assert.assertFalse(driver.findElement(nameTextboxBy).isEnabled());
        // Gender
        Assert.assertFalse(driver.findElement(genderTextboxBy).isEnabled());
        // Dob
        Assert.assertFalse(driver.findElement(dateOfBirthTextboxBy).isEnabled());

        // Verify available information
        Assert.assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"), name);
        Assert.assertEquals(driver.findElement(genderTextboxBy).getAttribute("value"), gender);
        Assert.assertEquals(driver.findElement(dateOfBirthTextboxBy).getAttribute("value"), dateOfBirth);
        Assert.assertEquals(driver.findElement(addressAreaBy).getText(), address);
        Assert.assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"), city);
        Assert.assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"), state);
        Assert.assertEquals(driver.findElement(pinTextboxBy).getAttribute("value"), pin);
        Assert.assertEquals(driver.findElement(mobileNumberTextboxBy).getAttribute("value"), mobileNumber);
        Assert.assertEquals(driver.findElement(emailAddressTextboxBy).getAttribute("value"), emailAddress);

        // Enter new information except fields which is disable
        // Address
        driver.findElement(addressAreaBy).clear();
        driver.findElement(addressAreaBy).sendKeys(addressEdit);
        driver.findElement(cityTextboxBy).clear();
        driver.findElement(cityTextboxBy).sendKeys(cityEdit);
        driver.findElement(stateTextboxBy).clear();
        driver.findElement(stateTextboxBy).sendKeys(stateEdit);
        driver.findElement(pinTextboxBy).clear();
        driver.findElement(pinTextboxBy).sendKeys(pinEdit);
        driver.findElement(mobileNumberTextboxBy).clear();
        driver.findElement(mobileNumberTextboxBy).sendKeys(mobileNumberEdit);
        driver.findElement(emailAddressTextboxBy).clear();
        driver.findElement(emailAddressTextboxBy).sendKeys(emailAddressEdit);

        // Click Submit button
        driver.findElement(By.xpath("//input[@name='sub']")).click();

        // Verify edited information
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer details updated Successfully!!!");
        Assert.assertEquals(driver.findElement(addressAreaVerifyBy).getText(), addressEdit);
        Assert.assertEquals(driver.findElement(cityTextboxVerifyBy).getText(), cityEdit);
        Assert.assertEquals(driver.findElement(stateTextboxVerifyBy).getText(), stateEdit);
        Assert.assertEquals(driver.findElement(pinTextboxVerifyBy).getText(), pinEdit);
        Assert.assertEquals(driver.findElement(mobileNumberTextboxVerifyBy).getText(), mobileNumberEdit);
        Assert.assertEquals(driver.findElement(emailAddressTextboxVerifyBy).getText(), emailAddressEdit);
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
