package webdriver;

import com.sun.source.tree.AssertTree;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_08_Custom_Dropdown {

    WebDriver driver;
    // Wait
    WebDriverWait explicitWait;
    // Inject JS code
    JavascriptExecutor jsExecutor;

    String projectPath = System.getProperty("user.dir");

    String[] expectedItemsMonths = {"January", "September", "October"};

    String[] expectedItemsMonths2 = {"January", "March", "June", "September", "October"};

    String[] expectedItemsMonths3 = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
        driver = new ChromeDriver();

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

    //	@Test
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

    //	@Test
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

    //	@Test
    public void TC_04_Angular() {
        driver.get("https://valor-software.com/ng2-select/");

        selectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//a[@class='dropdown-item']/div", "Bremen");
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//tab[@heading='Single']//span[contains(@class,'ui-select-match-text') and contains(text(),'Bremen')]")).isDisplayed());
    }

    //    @Test
    public void TC_05_Editable_01() {
        driver.get("https://valor-software.com/ng2-select/");

        enterAndSelectItemInCustomDropdown("//tab[@heading='Single']//span[contains(@class,'ui-select-toggle')]", "//input[contains(@class,'ui-select-search')]", "//a[@class='dropdown-item']/div", "Berlin");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//tab[@heading='Single']//span[contains(@class,'ui-select-allow-clear')]")).getText(), "Berlin");

        enterAndSelectItemInCustomDropdown("//tab[@heading='Single']//span[contains(@class,'ui-select-toggle')]", "//input[contains(@class,'ui-select-search')]", "//a[@class='dropdown-item']/div", "Athens");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//tab[@heading='Single']//span[contains(@class,'ui-select-allow-clear') and contains(text(),'Athens')]")).isDisplayed());
    }

    //    @Test
    public void TC_05_Editable_02() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        enterAndTabToCustomDropdown("//input[@class='search']", "Benin");
        sleepInSecond(1);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Benin");

        enterAndTabToCustomDropdown("//input[@class='search']", "Austria");
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and contains(string(),'Austria')]")).isDisplayed());
    }

    @Test
    public void TC_06_Multiple_Select() {
        driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

        selectMultipleItemInDropdown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span", expectedItemsMonths);
        sleepInSecond(2);
        Assert.assertTrue(areItemsSelected(expectedItemsMonths));

        driver.navigate().refresh();

        selectMultipleItemInDropdown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span", expectedItemsMonths2);
        sleepInSecond(2);
        Assert.assertTrue(areItemsSelected(expectedItemsMonths2));

        driver.navigate().refresh();

        selectMultipleItemInDropdown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span", expectedItemsMonths3);
        sleepInSecond(2);
        Assert.assertTrue(areItemsSelected(expectedItemsMonths3));
    }

    public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedItem) {
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
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                if (!item.isDisplayed()) {
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                    sleepInSecond(1);
                }
                item.click();
                break;
            }
        }
    }

    public void enterAndSelectItemInCustomDropdown(String parentXpath, String textboxXpath, String childXpath, String expectedItem) {
        // Click in parent item to show textbox
        driver.findElement(By.xpath(parentXpath)).click();
        sleepInSecond(1);

        // Enter expected item into textbox
        driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedItem);
        sleepInSecond(1);

        // Chờ cho tất cả các item được load ra thành công
        // Chờ xong sẽ lấy hết tất cả các items lưu vào một List WebElement
        // Cần tìm item cần chọn --> Gộp vào luôn
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        // Duyệt qua từng item và kiểm tra
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                if (!item.isDisplayed()) {
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                    sleepInSecond(1);
                }
                item.click();
                break;
            }
        }
    }

    public void enterAndTabToCustomDropdown(String textboxXpath, String expecteditem) {
        driver.findElement(By.xpath(textboxXpath)).sendKeys(expecteditem);
        sleepInSecond(1);

        driver.findElement(By.xpath(textboxXpath)).sendKeys(Keys.TAB);
        sleepInSecond(1);
    }

    public void selectMultipleItemInDropdown(String parentXpath, String childXpath, String[] expectedItemList) {
        // Click vào parent
        driver.findElement(By.xpath(parentXpath)).click();
        sleepInSecond(2);

        // Chờ item con load hết ra rồi get cho vào 1 list
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

        // Dùng 2 vòng for để duyệt
        for (String eachItemExpected : expectedItemList) {
            for (WebElement eachDropdownValue : allItems) {
                if (eachItemExpected.equals(eachDropdownValue.getText())) {
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", eachDropdownValue);
                    sleepInSecond(1);
                    eachDropdownValue.click();
                    break;
                }
            }
        }
    }

    public boolean areItemsSelected(String[] expectedItems) {
        Integer numberSelectedItems = driver.findElements(By.xpath("//li[@class='selected']//input")).size();

        String selectedText = driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span")).getText();

        boolean status = false;

        if (numberSelectedItems >= 12) {
            status = selectedText.equals("All selected");
        } else if (numberSelectedItems > 3) {
            status = selectedText.contains(numberSelectedItems.toString() + " of 12 selected");
        } else if (numberSelectedItems > 0) {
            for (String item : expectedItems) {
                if (!selectedText.contains(item)) {
                    return status;
                }
            }
            status = true;
        }
        return status;
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
