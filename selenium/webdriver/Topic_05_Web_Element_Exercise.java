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

public class Topic_05_Web_Element_Exercise {
    // Khởi tạo biến
    WebDriver driver;

    String firstName, lastName, fullName, emailAddress, password;
    By mailTextbox, educationTextarea, radioButtonUnder18, nameUser5, jobRole1Dropdown, jobRole2Dropdown, interestDevCheckbox, slider1, passwordTxt, ageRadioButton, biography, jobRole3, interestCheckbox, slider2, languagesJava;

    @BeforeClass
    public void beforeClass() {
        // Khởi tạo biến driver
        driver = new FirefoxDriver();

        //
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Khởi tạo data test
        firstName = "Nguyen";
        lastName = "Muoi";
        fullName = firstName + " " + lastName;
        emailAddress = gennerateEmail(lastName);
        password = "123456";

        mailTextbox = By.xpath("//input[@id='mail']");
        educationTextarea = By.xpath("//textarea [@id='edu']");
        radioButtonUnder18 = By.xpath("//input[@id='under_18']");
        nameUser5 = By.xpath("//h5[text()='Name: User5']");
        jobRole1Dropdown = By.cssSelector("#job1");
        jobRole2Dropdown = By.cssSelector("#job2>option");
        interestDevCheckbox = By.cssSelector("#development");
        slider1 = By.cssSelector("#slider-1");
        passwordTxt = By.xpath("//input[@id='password' and @name='user_pass']");
        ageRadioButton = By.cssSelector("#radio-disabled");
        biography = By.cssSelector("#bio");
        jobRole3 = By.cssSelector("#job3");
        interestCheckbox = By.cssSelector("#check-disbaled");
        slider2 = By.cssSelector("#slider-2");
        languagesJava = By.cssSelector("#java");
    }

    @Test
    public void TC_01_Create_New_Account() {
        driver.get("http://live.demoguru99.com/");

        // Open My Account page at footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        // Click 'Create an Account' button
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        // Input data
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);

        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);

        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);

        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);

        // Click Register button
        driver.findElement(By.xpath("//button[@title='Register']")).click();

        // Verify message
        Assert.assertEquals(driver.findElement(By.xpath("//li[contains(@class,'success-msg')]//span")).getText(), "Thank you for registering with Main Website Store.");

        //Verify user information
        // C1: Dùng hàm isDisplay() (để kiểm tra xem elelent p thỏa mãn điều kiện chứa fullName và emailAddress có tồn tại không)
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(), '" + fullName + "' )]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(), '" + emailAddress + "' )]")).isDisplayed());

        // C2: Dùng hàm getText() để get ra text của element p, sau đó dùng hàm contains để xem text vừa get ra đó có chứa fullName và emailAddress hay không
        String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInformation.contains(fullName));
        Assert.assertTrue(contactInformation.contains(emailAddress));

        // Logout khỏi hệ thống
        driver.findElement(By.cssSelector(".skip-account")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
    }

    @Test
    public void TC_02_Login() {
        // Open Home page
        driver.get("http://live.demoguru99.com/");

        // Open My Account page at footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(3);

        // Enter valid data into login form
        driver.findElement(By.cssSelector("#email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("#pass")).sendKeys(password);

        // Click on Login button
        driver.findElement(By.xpath("//button[@title='Login']")).click();

        // Verify account information
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName + "!");

        // C1: Using isDisplay() method
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'" + fullName + "')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'" + emailAddress + "')]")).isDisplayed());

        // C2: Using contains() method
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText().contains(fullName));
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText().contains(emailAddress));
    }

    @Test
    public void TC_03_Displayed_Newbie() {
        // Open Homepage
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Check isDisplay
        // Email
        if (driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed()) {
            driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing");
            System.out.println("Mail textbox is displayed");
        } else {
            System.out.println("Mail textbox is not displayed (undisplayed)");
        }

        // Education
        if (driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed()) {
            driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing");
            System.out.println("Education textarea is displayed");
        } else {
            System.out.println("Education textbox is not displayed (undisplayed)");
        }

        // Age (under 18)
        if (driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed()) {
            driver.findElement(By.xpath("//input[@id='under_18']")).click();
            System.out.println("Radio button 'Under 18' is displayed");
        } else {
            System.out.println("Radio button 'Under 18' is not displayed (undisplayed)");
        }

        // Name: User 5
        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("Name: User5 is displayed");
        } else {
            System.out.println("Name: User5 is not displayed (undisplayed)");
        }
    }

    @Test
    public void TC_03_Displayed_Function() {
        // Open Homepage
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Check isDisplay
        // Email
        if (isElementDisplayed(mailTextbox)) {
            sendKeyToElement(mailTextbox, "Automation Testing");
        }

        // Education
        if (isElementDisplayed(educationTextarea)) {
            sendKeyToElement(educationTextarea, "Automation Testing");
        }

        // Age (under 18)
        if (isElementDisplayed(radioButtonUnder18)) {
            clickToElement(radioButtonUnder18);
        }

        // Name: User5
        if (isElementDisplayed(nameUser5)) {

        }
    }

    @Test
    public void TC_04_Enabled_Function() {
        // Step 1: Open Homepage
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Step 2: Check is Enable
        // Email
        Assert.assertTrue(isElementEnabled(mailTextbox));
        // Age (under 18)
        Assert.assertTrue(isElementEnabled(radioButtonUnder18));
        // Education
        Assert.assertTrue(isElementEnabled(educationTextarea));
        // Job role 1
        Assert.assertTrue(isElementEnabled(jobRole1Dropdown));
        // Job role 2
        Assert.assertTrue(isElementEnabled(jobRole2Dropdown));
        // Interest Development Checkbox
        Assert.assertTrue(isElementEnabled(interestDevCheckbox));
        // Slider 1
        Assert.assertTrue(isElementEnabled(slider1));

        // Step 3: Check is disable
        // Password
        Assert.assertFalse(isElementEnabled(passwordTxt));
        // Age (Radio button is disable)
        Assert.assertFalse(isElementEnabled(ageRadioButton));
        // Biography
        Assert.assertFalse(isElementEnabled(biography));
        // Job role 3
        Assert.assertFalse(isElementEnabled(jobRole3));
        // Interests
        Assert.assertFalse(isElementEnabled(interestCheckbox));
        // Slider 02
        Assert.assertFalse(isElementEnabled(slider2));
    }

    @Test
    public void TC_05_Selected() {
        // Step 1: Open Homepage
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Step 2: Click on Age under 18 radio button and Languages java checkbox
        clickToElement(radioButtonUnder18);
        clickToElement(languagesJava);

        // Step 3: Verify elements which is selected in step 2: is Selected
        Assert.assertTrue(isElementSelected(radioButtonUnder18));
        Assert.assertTrue(isElementSelected(languagesJava));

        // Step 4: Uncheck on Languages java checkbox
        clickToElement(languagesJava);

        // Step 5: Verify elements which is selected in step 2: is not Selected
        Assert.assertFalse(isElementSelected(languagesJava));
    }

    @Test
    public void TC_06_Register_Function() {
        // Variables
        By email = By.id("email");
        By username = By.id("new_username");
        By password = By.id("new_password");
        By validatePassNumber = By.cssSelector(".number-char.completed");
        By validatePassLowerCase = By.cssSelector(".lowercase-char.completed");
        By validatePassUpperCase = By.cssSelector(".uppercase-char.completed");
        By validatePassSpecialCharacter = By.cssSelector(".special-char.completed");
        By validatePassCharacterMin = By.xpath("//li[@class='8-char completed']");
        By signupButton = By.xpath("//button[@id='create-account']");
        By checkbox = By.xpath("//input[@id='marketing_newsletter']");

        // Step 1: Open Homepage
        driver.get("https://login.mailchimp.com/signup/");

        // Step 2: Input invalid data on email and username
        sendKeyToElement(email, "automationfc@gmail.com");
        sendKeyToElement(username, "automationfc");

        // Step 3: Input difference password to verify validation password
        //          and Verify Sign up button is disable when password field is invalid
        // Input number
        sendKeyToElement(password, "1");
        Assert.assertTrue(isElementDisplayed(validatePassNumber));
        Assert.assertFalse(isElementEnabled(signupButton));

        // Input lower case
        driver.findElement(password).clear();
        sendKeyToElement(password, "a");
        Assert.assertTrue(isElementDisplayed(validatePassLowerCase));
        Assert.assertFalse(isElementEnabled(signupButton));

        // Input upper case
        driver.findElement(password).clear();
        sendKeyToElement(password, "A");
        Assert.assertTrue(isElementDisplayed(validatePassUpperCase));
        Assert.assertFalse(isElementEnabled(signupButton));

        // Input Special character
        driver.findElement(password).clear();
        sendKeyToElement(password, "#");
        Assert.assertTrue(isElementDisplayed(validatePassSpecialCharacter));
        Assert.assertFalse(isElementEnabled(signupButton));

        // Input Character minimum
        driver.findElement(password).clear();
        sendKeyToElement(password, "1111111111111111");
        Assert.assertTrue(isElementDisplayed(validatePassCharacterMin));
        Assert.assertTrue(isElementDisplayed(validatePassNumber));
        Assert.assertFalse(isElementEnabled(signupButton));

        // All criteria
        driver.findElement(password).clear();
        sendKeyToElement(password, "Automation123***");

        Assert.assertFalse(isElementDisplayed(validatePassLowerCase));
        Assert.assertFalse(isElementDisplayed(validatePassUpperCase));
        Assert.assertFalse(isElementDisplayed(validatePassNumber));
        Assert.assertFalse(isElementDisplayed(validatePassSpecialCharacter));
        Assert.assertFalse(isElementDisplayed(validatePassCharacterMin));

        Assert.assertTrue(isElementEnabled(signupButton));

        // Step 4: Click on checkbox and verify
        clickToElement(checkbox);
        Assert.assertTrue(isElementSelected(checkbox));
    }

    public boolean isElementDisplayed(By by) {
        if (driver.findElement(by).isDisplayed()) {
            System.out.println(by + " is displayed");
            return true;
        } else {
            System.out.println(by + " is not displayed");
            return false;
        }
    }

    public boolean isElementEnabled(By by) {
        if (driver.findElement(by).isEnabled()) {
            System.out.println(by + " is enabled");
            return true;
        } else {
            System.out.println(by + " is not enabled");
            return false;
        }
    }

    public boolean isElementSelected(By by) {
        if (driver.findElement(by).isSelected()) {
            System.out.println(by + " is selected");
            return true;
        } else {
            System.out.println(by + " is not selected (de-selected)");
            return false;
        }
    }

    public void sendKeyToElement(By by, String key) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(key);
    }

    public void clickToElement(By by) {
        driver.findElement(by).click();
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

    public String gennerateEmail(String lastName) {
        Random rand = new Random();
        return lastName + rand.nextInt(9999) + "@mail.vn";
    }

}
