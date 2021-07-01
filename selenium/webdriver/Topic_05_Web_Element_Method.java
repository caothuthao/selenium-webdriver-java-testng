package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_05_Web_Element_Method {
	
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Mở ra 1 trình duyệt
		driver = new FirefoxDriver();

		// Wait cho element xuất hiện trong 1 khoảng thời gian là xxx second
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Mở 1 url lên
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Web_Element() {
		// Muốn thao tác được với element thì phải tìm element trước

		// Thao tác với 1 element
		driver.findElement(By.id("")); // **

		// Thao tác với nhiều element
		driver.findElements(By.id("")); // **

		// Nếu mình chỉ thao tác với element 1 lần thì ko cần khai báo biến
		driver.findElement(By.id("small-searchterms")).sendKeys("Apple"); // **

		// Nếu cần thao tác với element nhiều lần thì nên khai báo biến
		WebElement searchTextBox = driver.findElement(By.id("small-searchterms"));
		searchTextBox.clear(); // **
		searchTextBox.sendKeys("Apple");
		searchTextBox.getAttribute("value"); // **

		// Đếm xem có bao nhiêu element thỏa mãn điều kiện
		// verify số lươngnj elemtn trả về như mong đợi
		// Thao tác với tất cả các loại element giống nhau trong 1 màn hình (textbox/ checkbox/ ...)
		List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@class='inputs']/input[not(@type='checkbox')]"));

		// Verify có đúng 6 ký tự tại form đăng ký
		Assert.assertEquals(checkboxes.size(), 6);

		WebElement singleElement = driver.findElement(By.className(""));

		// Textbox/ Textarea/ Editable dropdown
		// Dữ liệu được toàn vẹn
		singleElement.clear();
		singleElement.sendKeys("");

		// Button/ Link / Radio/ Checkbox/ Custom Dropdown/ ...
		singleElement.click(); // **

		// Các hàm có tiền tố bắt đầu bằng get luôn trả về dữ liệu
		// getTitle / getCurrenUUrl/ getPageSource/ getAttribute/ getCssValue/ getText/ ...
		singleElement = driver.findElement(By.xpath("//input[@id='FirstName']"));
		singleElement.getAttribute("value"); // Automation

		singleElement = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
		singleElement.getAttribute("placeholder"); // Search store

		// Lấy ra giá trị của các thuộc tính css - Thường dùng để test GUI
		// Font/ Size/ Color/ Background/ ...
		singleElement = driver.findElement(By.cssSelector(".search-box-button"));
		singleElement.getCssValue("background-color"); // **

		// Lấy ra tọa độ của element so với base hiện tại (get góc bên ngoài)
		singleElement.getLocation();

		// Lấy ra kích thước của element (Rộng x Cao) - Get góc bên trong
		singleElement.getSize();

		// Get location + size
		singleElement.getRect();

		// Chụp hình lỗi -> Đưa vào HTML Report
		singleElement.getScreenshotAs(OutputType.FILE); // **

		// Nếu mình ko findElementBy Xpath mà get By ID, Css, Name/... (không biết tagname)
		// -> Dùng để get ra tag name
		// --> Truyền vào cho 1 Locator khác
		singleElement = driver.findElement(By.cssSelector(".search-box-button"));
		String searchButtonTagname = singleElement.getTagName(); // **
		// searchTextBox = driver.findElement(By.xpath("//" + searchButtonTagname + "[@class='inputs']/input[not(@type='checkbox')]"))

		// Lấy ra text của element (Header/ Link/ Message/...)
		singleElement.getText(); // **

		// Các hàm có tiền tố là isXXX -> trả về kiểu boolean
		// True/ False

		// Kiểm tra xem 1 element hiển thị cho người dùng thao tác hay không
		// true: đang hiển thị
		// false: không hiển thị
		singleElement.isDisplayed();// **

		// Kiểm tra xem 1 element có disable hay không
		// Disable: người dùng không thao tác được
		singleElement.isEnabled(); // *

		// Kiểm tra xem 1 element đã được chọn rồi hay chưa
		// Checkbox/ Radio/ Dropdown
		// true: đã chọn rồi
		// false: chưa được chọn
		singleElement.isSelected(); // *

		// Thay cho hành vi enter vào textbox/ click vào button
		// Chỉ dùng được cho form thôi (Login/ Search/ Register/ ...)
		singleElement.submit(); // *

		// VD:
		singleElement.findElement(By.id("small-searchterms"));
		singleElement.sendKeys("Apple");
		singleElement.submit(); // Tương tự như find search button ra xong click()
								// Vì button search này ở trong form rồi nên chỉ cần submit thế này là được


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
