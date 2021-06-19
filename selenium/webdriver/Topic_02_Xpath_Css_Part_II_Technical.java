package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Xpath_Css_Part_II_Technical {

	// Đây là biến driver đại diện cho thằng Selenium WebDrier
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox lên
		driver = new FirefoxDriver();

		// Time để tìm element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Login_Empty_Email_Password() {
		// Open Home page
		driver.get("http://live.demoguru99.com/");

		// Open My Account page at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		// Bỏ trống email và password
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");

		// Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(3);

		// Compare
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");

	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		// Open Home page
		driver.get("http://live.demoguru99.com/");

		// Open My Account page at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		// Enter invalid email
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		sleepInSecond(3);

		// Enter valid password
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		sleepInSecond(3);

		// Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(3);

		// Compare
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_Invalid_Password() {
		// Open Home page
		driver.get("http://live.demoguru99.com/");

		// Open My Account page at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		// Enter valid email
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		sleepInSecond(3);

		// Enter invalid password
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		sleepInSecond(3);

		// Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(3);

		// Compare
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_Login_Incorrect_Email_Password() {
		// Open Home page
		driver.get("http://live.demoguru99.com/");

		// Open My Account page at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		// Enter incorrect email or password
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		sleepInSecond(3);

		// Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(3);

		// Compare
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
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
