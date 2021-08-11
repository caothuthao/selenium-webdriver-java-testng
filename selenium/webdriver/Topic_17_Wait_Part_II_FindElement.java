package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_Part_II_FindElement {

    WebDriver driver;
    WebDriverWait explicitWait;

    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();

//        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
//        driver = new ChromeDriver();

        explicitWait = new WebDriverWait(driver, 15);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //    @Test
    public void TC_01_Find_Element() {
        driver.get("https://www.facebook.com/");

        // Case 1: Do not find any element
//        System.out.println("Start Time:" + printCurrentDateTime());
//        try {
//            driver.findElement(By.xpath("//div[@id='123']")).sendKeys("automationfc");
//        } finally {
//            System.out.println("End Time:" + printCurrentDateTime());
//        }

        // Case 2: Result is 1 element
//        System.out.println("Start Time:" + printCurrentDateTime());
//        WebElement element = driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']"));
//        System.out.println("End Time:" + printCurrentDateTime());

        // Case 3: Result is a list of element
        WebElement element = driver.findElement(By.xpath("//div[@id='pageFooter']//a"));
        element.click();
    }

//    @Test
    public void TC_02_Find_Elements() {
        driver.get("https://www.facebook.com/");

        // Case 1: Do not find any element
//        List<WebElement> lstElement = driver.findElements(By.xpath("//div[@id='123']"));
//        System.out.println("lst size: " + lstElement.size());

        // Case 2: Result is 1 element
        List<WebElement> lstElement = driver.findElements(By.xpath("//a[text()='Tạo tài khoản mới']"));
        System.out.println("lst size: " + lstElement.size());

        // Case 3: Result is a list of element
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='pageFooter']//a"));
        System.out.println("List element size: " + elements.size());
    }

    public LocalDateTime printCurrentDateTime() {
        return LocalDateTime.now();
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
