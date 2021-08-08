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

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Upload_File_Part_II {

    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;
    String firefoxAutoITOnePath = projectPath + File.separator + "autoIT" + File.separator + "firefoxUploadOneTime.exe";
    String firefoxAutoITMultiplePath = projectPath + File.separator + "autoIT" + File.separator + "firefoxUploadMultiple.exe";
    String chromeAutoITOnePath = projectPath + File.separator + "autoIT" + File.separator + "chromeUploadOneTime.exe";
    String chromeAutoITMultiplePath = projectPath + File.separator + "autoIT" + File.separator + "chromeUploadMultiple.exe";

    // Data test
    String imgName01 = "001.jpg";
    String imgName02 = "002.jpg";
    String imgName03 = "003.jpg";

    String imgFilePath01 = uploadFilePath + imgName01;
    String imgFilePath02 = uploadFilePath + imgName02;
    String imgFilePath03 = uploadFilePath + imgName03;

    @BeforeClass
    public void beforeClass() {
//        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//        driver = new FirefoxDriver();
//
        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
    }

    //    @Test
    public void TC_01_AutoIT_One_File() throws IOException {
        if (driver.toString().contains("firefox")) {
            driver.findElement(By.cssSelector(".fileinput-button")).click();
            Runtime.getRuntime().exec(new String[]{firefoxAutoITOnePath, imgFilePath01});
            sleepInSecond(4);

            driver.findElement(By.cssSelector(".fileinput-button")).click();
            Runtime.getRuntime().exec(new String[]{firefoxAutoITOnePath, imgFilePath02});
            sleepInSecond(4);

            driver.findElement(By.cssSelector(".fileinput-button")).click();
            Runtime.getRuntime().exec(new String[]{firefoxAutoITOnePath, imgFilePath03});
            sleepInSecond(4);

        } else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
            // Click add file button to show open file dialog
            driver.findElement(By.cssSelector(".fileinput-button")).click();
            // Dùng autoIT để xử lý đoạn load file lên
            // Use to run exe file
            Runtime.getRuntime().exec(new String[]{chromeAutoITOnePath, imgFilePath01});
            sleepInSecond(4);

            driver.findElement(By.cssSelector(".fileinput-button")).click();
            Runtime.getRuntime().exec(new String[]{chromeAutoITOnePath, imgFilePath02});
            sleepInSecond(4);

            driver.findElement(By.cssSelector(".fileinput-button")).click();
            Runtime.getRuntime().exec(new String[]{chromeAutoITOnePath, imgFilePath03});
            sleepInSecond(4);
        }

        // verify load file successfully
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[1]")).getText(), imgName01);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[2]")).getText(), imgName02);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[3]")).getText(), imgName03);


        // click on start upload file button
        List<WebElement> startbuttons = driver.findElements(By.xpath("//tbody[@class='files']//button[contains(@class, 'start')]"));
        for (WebElement startButton : startbuttons) {
            startButton.click();
            sleepInSecond(3);
        }

        // verify upload file successfully
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[1]")).getText(), imgName01);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[2]")).getText(), imgName02);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[3]")).getText(), imgName03);

    }

//    @Test
    public void TC_02_AutoIT_Multiple_File() throws IOException {
        if (driver.toString().contains("firefox")) {
            driver.findElement(By.cssSelector(".fileinput-button")).click();
            Runtime.getRuntime().exec(new String[]{firefoxAutoITMultiplePath, imgFilePath01, imgFilePath02});
            sleepInSecond(4);

        } else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
            // Click add file button to show open file dialog
            driver.findElement(By.cssSelector(".fileinput-button")).click();
            // Dùng autoIT để xử lý đoạn load file lên
            // Use to run exe file
            Runtime.getRuntime().exec(new String[]{chromeAutoITMultiplePath, imgFilePath01, imgFilePath02});
            sleepInSecond(4);
        }

        // verify load file successfully
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[1]")).getText(), imgName01);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[2]")).getText(), imgName02);

        // click on start upload file button
        List<WebElement> startbuttons = driver.findElements(By.xpath("//tbody[@class='files']//button[contains(@class, 'start')]"));
        for (WebElement startButton : startbuttons) {
            startButton.click();
            sleepInSecond(3);
        }

        // verify upload file successfully
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[1]")).getText(), imgName01);
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[2]")).getText(), imgName02);
    }

//    @Test
    public void TC_03_Robot_One_File() throws AWTException {
        // Copy đường dẫn (path) của một file --> lưu vào trong Clipboard

        // Bật Open File Dialog lên --> giả lập phím Paste vào textbox để upload được file lên

        // Start:
        // Specify the file  location with extension
        StringSelection  select = new StringSelection(imgFilePath01);

        // Copy to clipboard
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

        // Click on button to open Open File Dialog
        driver.findElement(By.cssSelector(".fileinput-button")).click();

        Robot robot = new Robot();
        sleepInSecond(1);

        // Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Press Ctrl + V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        // Nha phim Ctrl + V
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        sleepInSecond(1);

        // Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


        // verify load file successfully
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name'])[1]")).getText(), imgName01);


        // click on start upload file button
        List<WebElement> startbuttons = driver.findElements(By.xpath("//tbody[@class='files']//button[contains(@class, 'start')]"));
        for (WebElement startButton : startbuttons) {
            startButton.click();
            sleepInSecond(3);
        }

        // verify upload file successfully
        Assert.assertEquals(driver.findElement(By.xpath("(//tbody[@class='files']//p[@class='name']/a)[1]")).getText(), imgName01);
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
