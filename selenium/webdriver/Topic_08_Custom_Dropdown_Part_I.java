package webdriver;

import com.sun.source.tree.AssertTree;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_08_Custom_Dropdown_Part_I {
	
	WebDriver driver;
	// Wait
	WebDriverWait explicitWait;
	// Inject JS code
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, 15);

		// Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_JQuery() {
		// Open Home Page
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		// Select item in dropdown and verify
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "3");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='3']")).isDisplayed());
	}

	@Test
	public void TC_02_React() {
		// Open Home Page
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		// Select item in dropdown and verify
		selectItemInCustomDropdown("//div[@id='root']//div[contains(@class,'dropdown')]", "//div[@role='option']/span", "Christian");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and contains(string(), 'Christian')]")).isDisplayed());

		selectItemInCustomDropdown("//div[@id='root']//div[contains(@class,'dropdown')]", "//div[@role='option']/span", "Matt");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and contains(string(), 'Matt')]")).isDisplayed());

		selectItemInCustomDropdown("//div[@id='root']//div[contains(@class,'dropdown')]", "//div[@role='option']/span", "Elliot Fu");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and contains(string(), 'Elliot Fu')]")).isDisplayed());
	}

	@Test
	public void TC_03_VueJS() {
		// Open Home Page
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		// Select item in dropdown and verify
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());

		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
	}

	public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedItem){
		/**
		 * 1: Click vào 1 element để xổ hết các item trong dropdown ra --> Parent element
		 * 2: Chờ cho tất cả các item được load ra thành công --> Child element
		 * 3: Tìm item cần chọn:
		 * Duyệt qua từng item -> Get text của item đó ra và kiểm tra xem nó có bằng vs item text mình mong muốn hay không
		 * 		TH1: Item cần chọn hiển thị → Click vào item đó
		 * 		TH2: Item cần chọn không hiển thị (bị ẩn) → Cần scroll xuống đến item đó → Click vào item
		 */

		// Click vào 1 element để xổ hết các item trong dropdown ra
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);

		// Chờ cho tất cả các item được load ra thành công
		// Chờ xong sẽ lấy hết tất cả các items lưu vào một List WebElement
		// Cần tìm item cần chọn --> Gộp vào luôn
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		// Duyệt qua từng item và kiểm tra
		for (WebElement item: allItems) {
			if (item.getText().trim().equals(expectedItem)){
				if (!item.isDisplayed()){
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);
				}
				item.click();
				break;
			}
		}
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
