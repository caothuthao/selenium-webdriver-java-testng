package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_Part_VI_Mixing {

    WebDriver driver;
    WebDriverWait explicitWait;

    String projectPath = System.getProperty("user.dir");

    By confimationTextbox = By.xpath("//input[@name='reg_email_confirmation__']");

    @BeforeClass
    public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

        driver.manage().window().maximize();
    }

//    @Test
    public void TC_01_Element_Found_Implicit_Explicit() {
        driver.get("https://www.facebook.com/");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 15);

        // Implicit
        System.out.println("Start implicit: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#email"));
        System.out.println("End implicit: " + getDateTimeNow());

        // Explicit
        System.out.println("Start Explicit: " + getDateTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
        System.out.println("End Explicit: " + getDateTimeNow());
    }

//    @Test
    public void TC_02_1_Element_Not_Found_Only_Implicit() {
        driver.get("https://www.facebook.com/");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Implicit
        System.out.println("Start implicit: " + getDateTimeNow());
        try {
            driver.findElement(By.cssSelector("input#testing"));
        } finally {
            System.out.println("End implicit: " + getDateTimeNow());
        }
    }

//    @Test
    public void TC_02_2_Element_Not_Found_Only_Explicit() {
        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, 15);

        // Explicit
        System.out.println("Start Explicit: " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
        } finally {
            System.out.println("End implicit: " + getDateTimeNow());
        }

    }

//    @Test
    public void TC_02_3_Element_Not_Found_Implicit_Explicit() {
        driver.get("https://www.facebook.com/");

        // Implicit > Explicit
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 5);

        // Implicit
        System.out.println("Start implicit: " + getDateTimeNow());
        // Nhận timeout của implicit
        try {
            driver.findElement(By.cssSelector("input#tiki"));
        } catch (Exception e){

        }

        System.out.println("End implicit: " + getDateTimeNow());


        // Explicit
        System.out.println("Start Explicit: " + getDateTimeNow());
        // Nhận timeout của cả 2 trong hàm visibilityOfElementLocated
        // driver.findElementLocator(locator) => bị ảnh hưởng timeout của implicit: 5s
        // elementIfVisible => bị ảnh hưởng timeout của explicit: 3s
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
        } catch (Exception e){

        }
        System.out.println("End Explicit: " + getDateTimeNow());
    }

//    @Test
    public void TC_03() {
        driver.get("https://www.facebook.com/");

        // Implicit > Explicit
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 15);

        // Explicit
        System.out.println("Start Explicit: " + getDateTimeNow());

        try {
//            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#tiki"))));
        } finally {
            System.out.println("End Explicit: " + getDateTimeNow());
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

    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }

}
