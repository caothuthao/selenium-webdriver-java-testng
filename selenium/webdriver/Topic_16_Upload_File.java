package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_16_Upload_File {

    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;

    // Data test
    String imgName01 = "001.jpg";
    String imgName02 = "002.jpg";
    String imgName03 = "003.jpg";

    String imgFilePath01 = uploadFilePath + imgName01;
    String imgFilePath02 = uploadFilePath + imgName02;
    String imgFilePath03 = uploadFilePath + imgName03;

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

//    @Test
    public void TC_01_Sendkey_One_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        // Send key to upload file element
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgFilePath01);
        sleepInSecond(1);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgFilePath02);
        sleepInSecond(1);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgFilePath03);
        sleepInSecond(1);

        // verify load file successfully
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[1]")).getText(), imgName01);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[2]")).getText(), imgName02);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[3]")).getText(), imgName03);

        // click on start upload file button
        List<WebElement> startbuttons = driver.findElements(By.xpath("//tbody[@class='files']//button[contains(@class, 'start')]"));
        for (WebElement startButton: startbuttons) {
            startButton.click();
            sleepInSecond(3);
        }

        // verify upload file successfully
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[1]")).getText(), imgName01);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[2]")).getText(), imgName02);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[3]")).getText(), imgName03);

    }

//    @Test
    public void TC_02_Sendkey_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        // Send key to upload file element
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgFilePath01 + "\n" + imgFilePath02 + "\n" + imgFilePath03);
        sleepInSecond(1);

        // verify load file successfully
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[1]")).getText(), imgName01);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[2]")).getText(), imgName02);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[3]")).getText(), imgName03);

        // click on start upload file button
        List<WebElement> startbuttons = driver.findElements(By.xpath("//tbody[@class='files']//button[contains(@class, 'start')]"));
        for (WebElement startButton: startbuttons) {
            startButton.click();
            sleepInSecond(3);
        }

        // verify upload file successfully
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[1]")).getText(), imgName01);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[2]")).getText(), imgName02);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[3]")).getText(), imgName03);
    }

//    @Test
    public void TC_03_Sendkey() {
        driver.get("https://gofile.io/?t=uploadFiles");

        // Send key to upload file element
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgFilePath01);
        sleepInSecond(5);

        // Click on link to verify
        driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).click();

        // Switch to new tab
        String parentID = driver.getWindowHandle();
        switchToWindowByID(parentID);

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='contentName']")).getText(), imgName01);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void switchToWindowByID(String parentID) {
        // Get ra tất cả các tab/ windows đang có
        Set<String> allWindows = driver.getWindowHandles();

        // Dùng vòng lặp để duyệt qua từng window
        for (String id : allWindows) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
            }
        }
    }

    public void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
