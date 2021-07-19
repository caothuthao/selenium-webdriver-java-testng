package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_09_Button_Radio_Checkbox {

    WebDriver driver;

    JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        javascriptExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Button() {
        // Open Home Page
        driver.get("https://www.fahasa.com/customer/account/create");

        // Click to move to login tab
        driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

        // Verify Login button is disable
        Assert.assertFalse(driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled());

        // Input valid data
        driver.findElement(By.cssSelector("#login_username")).sendKeys("thaoctt@gmail.com");
        driver.findElement(By.cssSelector("#login_password")).sendKeys("123456");

        // Verify login button is enable
        Assert.assertTrue(driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled());

        driver.navigate().refresh();

        // Navigate to Login tab
        driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

        // Trick
        // Remove disable attribute of login button
        javascriptExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(By.cssSelector(".fhs-btn-login")));
        sleepInSecond(3);

        // Verify login button is enable
        Assert.assertTrue(driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled());

        // Click on login button
        driver.findElement(By.cssSelector(".fhs-btn-login")).click();

        // Check error message
        Assert.assertEquals(driver.findElement(By.xpath("(//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert'])[1]")).getText(), "Thông tin này không thể để trống");
        Assert.assertEquals(driver.findElement(By.xpath("(//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert'])[1]")).getText(), "Thông tin này không thể để trống");
    }

    @Test
    public void TC_02_Radio_Checkbox_Default() {
        // Open Home Page
        driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");

        // Click on checkbox
        By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");

        checkToCheckboxOrRadioButton(rearSideCheckbox);
        sleepInSecond(2);

        // Verify checkbox is selected
        Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());

        // Click on checkbox again to deselect that checkbox
        uncheckToCheckbox(rearSideCheckbox);
        sleepInSecond(2);

        // Verify checkbox is deselected
        Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());

        // Demo radio button
        // Open Home Page
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        // Click on radio button
        By oneDotFourRadio = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input");

        checkToCheckboxOrRadioButton(oneDotFourRadio);
        sleepInSecond(2);

        // Verify checkbox is selected
        Assert.assertTrue(driver.findElement(oneDotFourRadio).isSelected());

    }

    @Test
    public void TC_03_Checkbox_Select_All() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        // Select all checkboxes
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                sleepInSecond(1);
            }
        }

        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

    }

    @Test
    public void TC_04_Radio_Checkbox_Custom() {
        driver.get("https://material.angular.io/components/radio/examples");

        By winterRadio = By.xpath("//input[@value='Winter']");
//
//        // 1- Thẻ input bị ẩn ko click được + có thể verify được
//        checkToCheckboxOrRadioButton(winterRadio);
//        sleepInSecond(2);
//
//        Assert.assertTrue(driver.findElement(winterRadio).isSelected());

        // 2 - Thẻ span để click (Có hiển thị) + không verify được
//        By winterSpan = By.xpath("//span[contains(text(),'Winter ')]");
//        driver.findElement(winterSpan).click();
//        sleepInSecond(2);
//
//        Assert.assertTrue(driver.findElement(winterSpan).isSelected());

        // 3 - Thẻ span để click và thẻ input để verify
//        By winterSpan = By.xpath("//span[contains(text(),'Winter ')]");
//        driver.findElement(winterSpan).click();
//        sleepInSecond(2);
//
//        Assert.assertTrue(driver.findElement(winterRadio).isSelected());

        // 4 - Dùng JS để click vào thẻ input bị ẩn
        By winterSpan = By.xpath("//span[contains(text(),'Winter ')]");
        clickToElementByJS(winterRadio);
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(winterRadio).isSelected());

        // Exerciese
        driver.get("https://material.angular.io/components/checkbox/examples");

        // Select and verify
        By checkedXpath = By.xpath("//input[@id='mat-checkbox-1-input']");
        clickToElementByJS(checkedXpath);
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(checkedXpath).isSelected());

        // Deselect and verify
        clickToElementByJS(checkedXpath);
        sleepInSecond(2);
        Assert.assertFalse(driver.findElement(checkedXpath).isSelected());

        // Select and verify
        By indeterminateXpath = By.xpath("//input[@id='mat-checkbox-2-input']");
        clickToElementByJS(indeterminateXpath);
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(indeterminateXpath).isSelected());

        // Deselect and verify
        clickToElementByJS(indeterminateXpath);
        sleepInSecond(2);
        Assert.assertFalse(driver.findElement(indeterminateXpath).isSelected());

    }

    @Test
    public void TC_05_Radio_Checkbox_Custom() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        // Verify checkbox is deselected
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Ninh' and @aria-checked='false']")).isDisplayed());

        driver.findElement(By.xpath("//div[@aria-label='Quảng Ninh']/div[contains(@class,'exportInnerBox')]")).click();
        sleepInSecond(2);

        // Verify checkbox is selected
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Ninh' and @aria-checked='true']")).isDisplayed());

        // Exercise
        // Open Home Page
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        // Verify Can Tho radio is deselected
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']")).isDisplayed());

        // Select Can Tho radio button
        driver.findElement(By.xpath("//div[@data-value='Cần Thơ']/div[@class='appsMaterialWizToggleRadiogroupRadioButtonContainer']/div")).click();

        // Verify Can Tho radio is selected
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());
    }

    public void clickToElementByJS(By by) {
        WebElement element = driver.findElement(by);
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    public void checkToCheckboxOrRadioButton(By by) {
        WebElement checkbox = driver.findElement(by);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void uncheckToCheckbox(By by) {
        WebElement checkbox = driver.findElement(by);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
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
