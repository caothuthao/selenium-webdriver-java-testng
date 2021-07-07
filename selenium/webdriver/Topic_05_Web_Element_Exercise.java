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
    }

//    @Test
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

//    @Test
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

//    @Test
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

        By mailTextbox = By.xpath("//input[@id='mail']");
        if (isElementDisplayed(mailTextbox)){
            sendKeyToElement(mailTextbox, "Automation Testing");
        }

        // Education
        By educationTextarea = By.xpath("//textarea [@id='edu']");
        if (isElementDisplayed(educationTextarea)){
            sendKeyToElement(educationTextarea, "Automation Testing");
        }

        // Age (under 18)
        By radioButtonUnder18 = By.xpath("//input[@id='under_18']");
        if (isElementDisplayed(radioButtonUnder18)){
            clickOnElement(radioButtonUnder18);
        }

        // Name: User5
        By nameUser5 = By.xpath("//h5[text()='Name: User5']");
        if (isElementDisplayed(nameUser5)){

        }
    }

    @Test
    public void TC_04_Enabled_Function() {
        // Open Homepage
        driver.get("https://automationfc.github.io/basic-form/index.html");
    }

    @Test
    public void TC_03_Selected() {
        // Open Homepage
        driver.get("https://automationfc.github.io/basic-form/index.html");

    }

    public boolean isElementDisplayed(By by){
        if (driver.findElement(by).isDisplayed()){
            System.out.println(by + "is displayed");
            return true;
        } else {
            System.out.println(by + "is not displayed");
            return false;
        }
    }

    public void sendKeyToElement(By by, String key){
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(key);
    }

    public void clickOnElement (By by){
        driver.findElement(by).click();
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
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
