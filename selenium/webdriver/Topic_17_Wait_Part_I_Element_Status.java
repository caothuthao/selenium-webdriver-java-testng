package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_Part_I_Element_Status {

    WebDriver driver;
    WebDriverWait explicitWait;

    String projectPath = System.getProperty("user.dir");

    By confimationTextbox = By.xpath("//input[@name='reg_email_confirmation__']");

    @BeforeClass
    public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

//        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
//        driver = new ChromeDriver();

        explicitWait = new WebDriverWait(driver, 15);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

//    @Test
    public void TC_01_Visible() {
        driver.get("https://www.facebook.com/");

        // Click on "Tạo tài khoản mới" button
        driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();

        // Input email into email text box
        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("automationfc@gmail.com");

        // Chờ cho element được hiển thị
        // Vừa hiển thị trong UI + có trong DOM
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(confimationTextbox));

        // Đợi cho element hiển thị rồi thì send key vào
        driver.findElement(confimationTextbox).sendKeys("automationfc@gmail.com");
    }

//    @Test
    public void TC_02_Invisible_01() {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();

        // Case 1: Ko hiển thị trên UI + có trong HTML
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confimationTextbox));
    }

//    @Test
    public void TC_02_Invisible_02() {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();

        // Click on code icon to close popup
        driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();

        // Case 2: Ko hiển thị trên UI + ko có trong HTML
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confimationTextbox));
    }

//    @Test
    public void TC_03_Presence() {
        driver.get("https://www.facebook.com/");

        // Click on "Tạo tài khoản mới" button
        driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();

        // Case 1: Không hiển thị trên UI + có trong DOM
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(confimationTextbox));

        // Input email into email text box
        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("automationfc@gmail.com");

        // Case 2: Hiển thị trên UI + có trong DOM
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(confimationTextbox));
    }

    @Test
    public void TC_03_Staleness() {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();

        WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='reg_email__']"));

        // Close popup
        driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();

        explicitWait.until(ExpectedConditions.stalenessOf(emailTextbox));
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
