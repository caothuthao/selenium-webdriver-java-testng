package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Web_Browser_Exercise {
	
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_Verify_Url() {
		// Click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Get current page url
		String loginPageUrl = driver.getCurrentUrl();

		// Verify
		Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

		// Click để chuyển vào trang register
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Get current page url
		String createAnAccountPageUrl = driver.getCurrentUrl();

		// Verify
		Assert.assertEquals(createAnAccountPageUrl,"http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Verify_Title() {
		// Move to home page
		driver.findElement(By.xpath("//img[@class='large' and @alt='Magento Commerce']")).click();

		// Click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Get current page title
		String loginPageTitle = driver.getTitle();

		// Verify
		Assert.assertEquals(loginPageTitle, "Customer Login");

		// Click để chuyển vào trang register
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Get current page title
		String createAnAccountPageTitle = driver.getTitle();

		// Verify
		Assert.assertEquals(createAnAccountPageTitle, "Create New Customer Account");
	}

	@Test
	public void TC_03_Verify_Navigation() {
		// Truy cập vào trang http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");

		// Click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Click để chuyển vào trang register
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Verify url của page register
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

		// Back lại trang login page
		driver.navigate().back();
		sleepInSecond(3);

		// Verify url của login page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		// Forward tới lại trang Register Pgage
		driver.navigate().forward();
		sleepInSecond(3);

		// Verify Title của login page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_04_Verify_Page_Source() {
		// Truy cập vào trang http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");

		// Click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Get curent page source
		String currentPageSource = driver.getPageSource();

		// Verify Login page chứa text 'Login or Create an Account'
		Assert.assertTrue(currentPageSource.contains("Login or Create an Account"));

		// Click Create an Account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Verify Login page chứa text 'Create an Account'
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond (long timeoutInSecond){
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
