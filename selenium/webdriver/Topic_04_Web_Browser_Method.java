package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Web_Browser_Method {
	
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_Browser() {
		// Bien tuong tac voi Browser

		// Mở 1 page ra -> **
		driver.get("www.facebook.com/r.php");

		// Lấy ra đường dẫn (URL) của page hiện tại -> **
		String localPageUrl = driver.getCurrentUrl();

		// Lấy ra Title của page hiện tại -> **
		driver.getTitle();

		// Lấy ra toàn bộ HTMl code của page hiện tại
		driver.getPageSource();

		// Xử lí tab/ windows -> **
		driver.getWindowHandle();
		driver.getWindowHandles();

		// Framework (Share Class State)
		// driver.manage().addCookie(cookie);

        // Chờ cho element được tìm thấy trong xxx thời gian -> **
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // setScriptTimeout
        // pageLoadTimeout

        // Back về page trước đó
        // Forward tới page trước đó
        // Refresh page hiện tại
        // Mở 1 url ra
        driver.navigate().back();
        // History (navigate().to("url"))

        // Đóng luôn cả trình duyệt -> **
        driver.quit();

        // Đóng tab đang active
        // Dùng trong xử lý switch tab/ windows
        driver.close();

        // Windows/ tab -> **
        // Alert -> **
        // Frame/ Iframe -> **
        driver.switchTo().window("");
        driver.switchTo().alert();
        driver.switchTo().frame("");

        //
        driver.manage().window().fullscreen();

        // -> **
        driver.manage().window().maximize();

        // Test GUI
        // Lấy ra vị trí browser so với độ phân giải màn hình hiện tại
        driver.manage().window().getPosition();
        // driver.manage().window().setPosition(targetPosition);

        driver.manage().window().getSize();
        // driver.manage().window().setSize(targetSize);
	}
}
