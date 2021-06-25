package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Run_On_Browser {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @Test
    public void TC_01_Run_On_Firefox() {
        // Older Firefox version (47.0.2)
        driver = new FirefoxDriver();

        // Lastest version (>=48)
//		System.setProperty("webdriver.gecko.driver", "//geckodriver_path");
//		driver = new FirefoxDriver();

        driver.get("http://live.guru99.com");
        driver.quit();
    }

    @Test
    public void TC_02_Run_On_Chrome() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
        driver = new ChromeDriver();

        driver.get("http://live.guru99.com");
        driver.quit();

    }

    @Test
    public void TC_02_Run_On_Edge_Chromium() {
        System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver.exe");
        driver = new EdgeDriver();

        driver.get("http://live.guru99.com");
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
