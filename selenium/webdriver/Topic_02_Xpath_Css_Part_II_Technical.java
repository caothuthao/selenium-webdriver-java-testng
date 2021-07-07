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

public class Topic_02_Xpath_Css_Part_II_Technical {

    // Đây là biến driver đại diện cho thằng Selenium WebDrier
    WebDriver driver;

    // Other Variables
    String firstName = "Cao";
    String lastName = "Thao";
    String fullName = firstName + " " + lastName;
    String emailAddress;
    String password = "123456";
    String confirmPassword = "123456";

    @BeforeClass
    public void beforeClass() {
        // Mở trình duyệt Firefox lên
        driver = new FirefoxDriver();

        // Time để tìm element
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    // Register
    @Test
    public void TC_01_Register_Empty_Data() {
        // Open homepage
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Click on Register button
        driver.findElement(By.xpath("//div[contains(@class,'frmRegister')]//button[text()='ĐĂNG KÝ']")).click();

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        // Open homepage
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Enter valid email on fields except Email and Confirm Email
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Cao Thu Thao");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123123321");

        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("123@123.234@");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@123.234@");

        // Click on Register button
        driver.findElement(By.xpath("//div[contains(@class,'frmRegister')]//button[text()='ĐĂNG KÝ']")).click();

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_03_Register_Incorrect_Confirm_Email() {
        // Open homepage
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Enter valid email on fields and incorrect Confirm Email
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Cao Thu Thao");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123123321");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("thao@gmail.com");

        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("muoi@gmail.com");

        // Click on Register button
        driver.findElement(By.xpath("//div[contains(@class,'frmRegister')]//button[text()='ĐĂNG KÝ']")).click();

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_04_Register_Invalid_Password() {
        // Open homepage
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Enter valid email on fields except password and confirm password
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Cao Thu Thao");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123123321");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("thao@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("thao@gmail.com");

        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123");

        // Click on Register button
        driver.findElement(By.xpath("//div[contains(@class,'frmRegister')]//button[text()='ĐĂNG KÝ']")).click();

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Register_Incorrect_Confirm_Password() {
        // Open homepage
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Enter valid email on fields and incorrect confirm password
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Cao Thu Thao");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123123321");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("thao@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("thao@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");

        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123567");

        // Click on Register button
        driver.findElement(By.xpath("//div[contains(@class,'frmRegister')]//button[text()='ĐĂNG KÝ']")).click();

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Register_Invalid_Phone_Number() {
        // Open homepage
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Enter valid email on fields except phone number (string)
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Cao Thu Thao");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("thao@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("thao@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");

        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("thao@gmail.com");

        // Click on Register button
        driver.findElement(By.xpath("//div[contains(@class,'frmRegister')]//button[text()='ĐĂNG KÝ']")).click();

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập con số");

        driver.navigate().refresh();

        // Enter valid email on fields except phone number (<10 characters or >11 characters>)
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Cao Thu Thao");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("thao@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("thao@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");

        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123123");

        // Click on Register button
        driver.findElement(By.xpath("//div[contains(@class,'frmRegister')]//button[text()='ĐĂNG KÝ']")).click();

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");

        driver.navigate().refresh();

        // Enter valid email on fields except phone number (start of phone number is incorrect)
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Cao Thu Thao");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("thao@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("thao@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");

        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("123456");

        // Click on Register button
        driver.findElement(By.xpath("//div[contains(@class,'frmRegister')]//button[text()='ĐĂNG KÝ']")).click();

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
    }


    // Login
    @Test
    public void TC_07_Login_Empty_Email_Password() {
        // Open Home page
        driver.get("http://live.demoguru99.com/");

        // Open My Account page at footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        // Bỏ trống email và password
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");

        // Click Login button
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        // Compare
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");

    }

    @Test
    public void TC_08_Login_Invalid_Email() {
        // Open Home page
        driver.get("http://live.demoguru99.com/");

        // Open My Account page at footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        // Enter invalid email
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");

        // Enter valid password
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");

        // Click Login button
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        // Compare
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_09_Login_Invalid_Password() {
        // Open Home page
        driver.get("http://live.demoguru99.com/");

        // Open My Account page at footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        // Enter valid email
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");

        // Enter invalid password
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");

        // Click Login button
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        // Compare
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_10_Login_Incorrect_Email_Password() {
        // Open Home page
        driver.get("http://live.demoguru99.com/");

        // Open My Account page at footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        // Enter incorrect email or password
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");

        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");

        // Click Login button
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        // Compare
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
    }

    @Test
    public void TC_11_Create_New_Account() {
        // Open Home page
        driver.get("http://live.demoguru99.com/");

        // Open My Account page at footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        // Click on "Create An Account" button
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        // Enter valid data
        Random random = new Random();
        emailAddress = random.nextInt(6) + lastName + "@gmail.com";

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(confirmPassword);

        // Click on Register button
        driver.findElement(By.xpath("//button[@title='Register']")).click();

        // Verify success message
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");

        // Verify account information
        // C1: Using isDisplay() method
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'" + fullName + "')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'" + emailAddress + "')]")).isDisplayed());

        // C2: Using contains() method
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText().contains(fullName));
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText().contains(emailAddress));

        // Logout
        driver.findElement(By.xpath("//a[contains(@class,'skip-account')]")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

    @Test
    public void TC_12_Login_Valid_Email_Password() {
        // Open Home page
        driver.get("http://live.demoguru99.com/");

        // Open My Account page at footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        // Enter valid data into login form
        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);

        // Click on Login button
        driver.findElement(By.xpath("//button[@title='Login']")).click();

        // Verify account information
        // C1: Using isDisplay() method
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong[contains(text(),'" + fullName + "')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'" + fullName + "')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'" + emailAddress + "')]")).isDisplayed());

        // C2: Using contains() method
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText().contains(fullName));
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText().contains(fullName));
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText().contains(emailAddress));
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
