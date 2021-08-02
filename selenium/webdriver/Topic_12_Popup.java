package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Popup {

    WebDriver driver;

    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //    @Test
    public void TC_01_Fixed_Popup() {
        driver.get("https://ngoaingu24h.vn/");

        // Click on Login button
        driver.findElement(By.xpath("//div[@id='button-login-dialog']//button[contains(text(),'Đăng nhập')]")).click();
        sleepInSecond(5);

        // Verify Popup isDisplay
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='modal-login-v1']/div")).isDisplayed());

        // Enter Information
        driver.findElement(By.cssSelector("div[id='modal-login-v1'] input[id='account-input']")).sendKeys("automationFC");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'] input[id='password-input']")).sendKeys("123456");

        // Click on Login button
        driver.findElement(By.xpath("//button[contains(@class,'buttonLoading') and text()='Đăng nhập']")).click();

        // Verify Login failed
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'error-login-panel') and text()='Tài khoản không tồn tại!']")).isDisplayed());

        // Click on Close icon
        driver.findElement(By.xpath("//div[@id='modal-login-v1']//button[@class='close']")).click();
        sleepInSecond(5);

        // Verify Popup isNotDisplay
        Assert.assertFalse(driver.findElement(By.xpath("//div[@id='modal-login-v1']/div")).isDisplayed());
    }

    //    @Test
    public void TC_02_Random_In_DOM() {
        driver.get("https://blog.testproject.io/");
        sleepInSecond(5);

        // Vì popup có trong DOM nên có thể find được
        WebElement popup = driver.findElement(By.cssSelector("div.mailch-wrap"));

        // Nếu popup hiển thị thì close đi để thực hiện các step sau
        if (popup.isDisplayed()) {
            driver.findElement(By.cssSelector("div#close-mailch")).click();
            sleepInSecond(3);
        }

        // Enter keyword into search form
        driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
        sleepInSecond(5);

        // Click search icon
        driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
        sleepInSecond(5);

        // Verify
        List<WebElement> titleArticle = driver.findElements(By.cssSelector("h3.post-title>a"));
        for (WebElement element : titleArticle) {
            Assert.assertTrue(element.getText().contains("Selenium"));
        }
    }

//    @Test
    public void TC_03_Random_Not_In_DOM() {
        driver.get("https://shopee.vn/");

        // Nếu element ko có trong DOM thì hàm findByElement ko tìm thấy
        // CHờ hết timeout của implicit
        // Đánh fail TC ngay tại step đó luôn --> Không dùng cách này trong TH này được
        // ==> Dùng hàm findByElements --> return về một List
        // Nếu ko tìm thấy element thì List empty -> TC ko bị fail
        List<WebElement> popup = driver.findElements(By.cssSelector("div.shopee-popup img"));

        if (popup.size() > 0 && popup.get(0).isDisplayed()) {
            System.out.println("Popup is display.");
            driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
            sleepInSecond(3);
        } else {
            System.out.println("Popup is not display.");
        }

        // Enter keyword into search form
        driver.findElement(By.cssSelector(".shopee-searchbar-input__input")).sendKeys("Macbook Pro");

        // Click on search icon
        driver.findElement(By.cssSelector(".btn-solid-primary")).click();

        // Verify
        List<WebElement> listTitle = driver.findElements(By.cssSelector(".PFM7lj>div"));

        for (WebElement element : listTitle) {
            Assert.assertTrue(element.getText().contains("Macbook") || element.getText().contains("Pro"));
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

}
