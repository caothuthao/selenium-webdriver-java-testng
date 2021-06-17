package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Xpath_Css_Part_II_Technical {
	
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_Login_Empty_Email_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(3);
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		sleepInSecond(3);

		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(3);
	}

	@Test
	public void TC_03_Login_Invalid_Password() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		sleepInSecond(3);

		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(3);
	}

	@Test
	public void TC_04_Login_Incorrect_Email_Password() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		sleepInSecond(3);

		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(3);
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
