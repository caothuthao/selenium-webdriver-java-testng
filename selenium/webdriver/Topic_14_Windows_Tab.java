package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_14_Windows_Tab {

    WebDriver driver;

    String projectPath = System.getProperty("user.dir");

    JavascriptExecutor javascriptExecutor;
    Alert alert;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
        driver = new ChromeDriver();

        javascriptExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, 30);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //    @Test
    public void TC_01_Windows_01() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Get ra ID của active tab/ windows (driver đang đứng) --> 1 cái
        String parentID = driver.getWindowHandle();

        // Open new tab
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

        // Switch qua new window
        switchToWindowByID(parentID);

        // Enter keyword into email field at facebook page (child page)
        driver.findElement(By.cssSelector("#email")).sendKeys("child@gmail.com");

        // Switch lại qua parent
        // Lúc này childID lại là ID của window đóng vai trò là parent
        String childID = driver.getWindowHandle();
        switchToWindowByID(childID);

        // Enter keyword into email field at parent page
        driver.findElement(By.cssSelector("#email")).sendKeys("parent@gmail.com");

    }

    //    @Test
    public void TC_02_Windows_02() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Get ra ID của active tab/ windows (driver đang đứng) --> 1 cái
        String parentID = driver.getWindowHandle();

        // Open new tab - Facebook
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

        // Switch vào facebook tab bằng Title
        switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

        // Enter keyword into email field at facebook page (child page)
        driver.findElement(By.cssSelector("#email")).sendKeys("child@gmail.com");

        // Switch vào parent page
        switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

        // Enter keyword into email field at parent page
        driver.findElement(By.cssSelector("#email")).sendKeys("parent@gmail.com");

        // Open new tab - Tiki
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();

        // Switch vào Tiki tab bằng Title
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        // Enter keyword into email field at facebook page (child page)
        driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).sendKeys("iPhone");

        // Click on search button
        driver.findElement(By.xpath("//button[@data-view-id='main_search_form_button']")).click();

        // Switch vào parent page
        switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

        // Open new tab - LAZADA
        driver.findElement(By.xpath("//a[text()='LAZADA']")).click();

        // Switch vào LAZADA tab bằng Title
        switchToWindowByTitle("Shopping online - Buy online on Lazada.vn");

        // Enter keyword into email field at facebook page (child page)
        driver.findElement(By.xpath("//input[@id='q']")).sendKeys("iPhone");

        // Close all tab are not parent tab
        closeAllWindowsWithoutParent(parentID);

        // Enter keyword into email field at parent page
        driver.findElement(By.cssSelector("#email")).sendKeys("parent@gmail.com");
    }

    //    @Test
    public void TC_03_Kyna() {
        driver.get("https://kyna.vn/");

        // Close popup nếu có xuất hiện

        // Get ID of parent page
        String parentID = driver.getWindowHandle();

        // Scroll to bottom page
        scrollToButtomPage();

        // Click on Facebook link
        driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();

        // Switch qua Facebook tab
        switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");

        // Verify switch to facebook tab successfully
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

        // Switch to parent page
        driver.switchTo().window(parentID);

        // Click on Youtube link
        driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();

        // Switch qua Youtube tab
        switchToWindowByTitle("Kyna.vn - YouTube");

        // Verify switch to Youtube tab successfully
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");

        // Switch to parent page and close all new tab
        closeAllWindowsWithoutParent(parentID);
    }

    //    @Test
    public void TC_04_Demoguru99() {
        driver.get("http://live.demoguru99.com/index.php/");

        // Get parent ID
        String parentID = driver.getWindowHandle();

        // Click on Mobile link
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        // Add to compare 3 items
        // Samsung Galaxy
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSecond(2);
        // Verify choose item successfully
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        // Sony
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSecond(2);
        // Verify choose item successfully
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSecond(2);
        // Verify choose item successfully
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product IPhone has been added to comparison list.");

        // CLick on Compare button to switch to compare window
        driver.findElement(By.xpath("//button[@title='Compare']")).click();

        // Switch to compare window
        switchToWindowByID(parentID);

        // Verify title of new window
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

        // Close tab and switch to parent window
        closeAllWindowsWithoutParent(parentID);

        // Click on CLear all link
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        // Accept alert
        // Wait cho alert xuất hiện + Switch qua alert
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        sleepInSecond(5);

        // Verify alert text
        Assert.assertEquals(alert.getText(), "Are you sure you would like to remove all products from your comparison?");

        // Accept
        alert.accept();

        // Verify showed message
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
    }

    /**
     * Dùng cho đúng 2 windows/ tab
     * Kiểm tra id khác với parent thì switch sang window/ tab có id đó
     *
     * @param parentID
     */
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

    /**
     * Dùng cho 2 hoặc >2 windows/ tab
     * Switch vào từng window trước
     * Get ra title của window đó
     * Kiểm tra với title mong muốn
     * Nếu bằng thì dừng lại không kiểm tra tiếp nữa
     *
     * @param expectedTitle
     */
    public void switchToWindowByTitle(String expectedTitle) {
        // Get ra tất cả các tab/ windows đang có
        Set<String> allWindows = driver.getWindowHandles();

        // Dùng vòng lặp để duyệt qua từng window
        for (String id : allWindows) {
            driver.switchTo().window(id);
            String windowTitle = driver.getTitle();
            if (windowTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    /**
     * Dùng để close các windows/ tab không phải là parent
     * Nếu windows/ tab nào có id khác id của parent thì sẽ switch qua và close đi
     *
     * @param parentID
     */
    public void closeAllWindowsWithoutParent(String parentID) {
        // Get ra tất cả các tab/ windows đang có
        Set<String> allWindows = driver.getWindowHandles();

        // Dùng vòng lặp để duyệt qua từng window
        for (String id : allWindows) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        // Switch về parent
        driver.switchTo().window(parentID);
    }

    public void scrollToButtomPage() {
        javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
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
