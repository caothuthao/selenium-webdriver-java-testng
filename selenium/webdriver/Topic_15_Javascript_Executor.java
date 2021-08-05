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
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_15_Javascript_Executor {

    WebDriver driver;

    JavascriptExecutor jsExecutor;

    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();

//        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
//        driver = new ChromeDriver();

        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //    @Test
    public void TC_01_Live_Guru() {
        navigateToUrlByJS("http://live.demoguru99.com/");

        // Get domain and verify
        String domain = (String) executeForBrowser("return document.domain");
        Assert.assertEquals(domain, "live.demoguru99.com");

        // Get URL and verify
        String url = (String) executeForBrowser("return document.URL");

        // Open Mobile page: highlight + click
        highlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        // Add Samsung Galaxy into cat and verify
        highlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']");
        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        // Open Customer Service Page and verify title
        highlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");
        Assert.assertEquals(executeForBrowser("return document.title"), "Customer Service");

        // Scroll to Newsletter textbox at bottom page
        highlightElement("//input[@id='newsletter']");
        scrollToElement("//input[@id='newsletter']");

        // Input valid email address into Newsletter textbox
        sendkeyToElementByJS("//input[@id='newsletter']", generateEmailAddress());

        // Click on Subscribe button and verify showed text
        highlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");
        Assert.assertTrue(getInnerText().contains("Thank you for your subscription"));

        // Navigate to domain and verify
        navigateToUrlByJS("http://demo.guru99.com/v4/");
        Assert.assertEquals(executeForBrowser("return document.domain"), "demo.guru99.com");
    }

    //    @Test
    public void TC_02_HTML5_Validation_Message_Automation_fc() {
        driver.get("https://automationfc.github.io/html5/index.html");

        // Click submit button to verify name field message
        driver.findElement(By.xpath("//input[@name='submit-btn']")).click();

        String nameFieldMessage = getElementValidationMessage("//input[@id='fname']");
        Assert.assertEquals(nameFieldMessage, "Please fill out this field.");

        // Input valid data into name field and click submit button to verify password field message
        driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Cao Thu Thao");
        driver.findElement(By.xpath("//input[@name='submit-btn']")).click();

        String passwordFieldMessage = getElementValidationMessage("//input[@id='pass']");
        Assert.assertEquals(passwordFieldMessage, "Please fill out this field.");

        // Input valid data into name field + password field and click submit button to verify password field message
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@name='submit-btn']")).click();

        String emailFieldMessage = getElementValidationMessage("//input[@id='em']");
        Assert.assertEquals(emailFieldMessage, "Please fill out this field.");

        // Enter invalid data into email field and click submit button to verify message at email textbox
        driver.findElement(By.xpath("//input[@id='em']")).sendKeys("123!@!!!");
        driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
        sleepInSecond(10);
        emailFieldMessage = getElementValidationMessage("//input[@id='em']");
        Assert.assertEquals(emailFieldMessage, "Please enter an email address.");

        driver.findElement(By.xpath("//input[@id='em']")).clear();
        driver.findElement(By.xpath("//input[@id='em']")).sendKeys("123@456");
        driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
        sleepInSecond(10);
        emailFieldMessage = getElementValidationMessage("//input[@id='em']");
        Assert.assertEquals(emailFieldMessage, "Please match the requested format.");

        // Input valid data into email field and click submit button to verify address field message
        driver.findElement(By.xpath("//input[@id='em']")).clear();
        driver.findElement(By.xpath("//input[@id='em']")).sendKeys(generateEmailAddress());
        driver.findElement(By.xpath("//input[@name='submit-btn']")).click();

        String addressFieldMessage = getElementValidationMessage("//b[text()='✱ ADDRESS ']/parent::label/following-sibling::select");
        Assert.assertEquals(addressFieldMessage, "Please select an item in the list.");
    }

    //    @Test
    public void TC_03_HTML5_Validation_Message_Ubuntu() {
        // Page 1: Ubuntu
        driver.get("https://login.ubuntu.com/");

        // Close popup if it exists
        List<WebElement> popups = driver.findElements(By.xpath("//div[@class='p-modal__dialog']"));
        if (popups.size() > 0 && popups.get(0).isDisplayed()) {
            driver.findElement(By.xpath("//button[@id='cookie-policy-button-accept']")).click();
        }

        // Do not enter any data in all field and click Log in button
        driver.findElement(By.xpath("//form[@name='loginform']//button[@name='continue']")).click();
        Assert.assertEquals(getElementValidationMessage("//form[@name='loginform']//input[@name='email']"), "Please fill out this field.");

        // Enter invalid email  and click on Log in button to verify message
        driver.findElement(By.xpath("//form[@name='loginform']//input[@name='email']")).sendKeys("@");
        driver.findElement(By.xpath("//form[@name='loginform']//button[@name='continue']")).click();
        Assert.assertEquals(getElementValidationMessage("//form[@name='loginform']//input[@name='email']"), "Please enter an email address.");

        // Enter valid email + do not enter password + click Log in button to verify message
        driver.findElement(By.xpath("//form[@name='loginform']//input[@name='email']")).sendKeys("thao@gmail.com");
        driver.findElement(By.xpath("//form[@name='loginform']//button[@name='continue']")).click();
        Assert.assertEquals(getElementValidationMessage("//form[@name='loginform']//input[@id='id_password']"), "Please fill out this field.");
    }

    //    @Test
    public void TC_04_HTML5_Validation_Message_Sieuthimaymocthietbi() {
        driver.get("https://sieuthimaymocthietbi.com/account/register");

        String submitButtonLocator = "//button[@value='Đăng ký']";
        String lastNameLocator = "//input[@id='lastName']";
        String firstNameLocator = "//input[@id='firstName']";
        String emailLocator = "//input[@id='email']";
        String passwordLocator = "//input[@id='password']";

        // Case 1: Do not input any data + click Submit button
        driver.findElement(By.xpath(submitButtonLocator)).click();
        Assert.assertEquals(getElementValidationMessage(lastNameLocator), "Please fill out this field.");

        // Case 2: Input last name + click submit button
        driver.findElement(By.xpath(lastNameLocator)).sendKeys("Thu Thao");
        driver.findElement(By.xpath(submitButtonLocator)).click();
        Assert.assertEquals(getElementValidationMessage(firstNameLocator), "Please fill out this field.");

        // Case 3: Input last name + first name + click submit button
        driver.findElement(By.xpath(firstNameLocator)).sendKeys("Cao");
        driver.findElement(By.xpath(submitButtonLocator)).click();
        Assert.assertEquals(getElementValidationMessage(emailLocator), "Please fill out this field.");

        // Case 4: Input last name + first name + invalid email + click submit button
        driver.findElement(By.xpath(emailLocator)).sendKeys("123@456");
        driver.findElement(By.xpath(submitButtonLocator)).click();
        Assert.assertEquals(getElementValidationMessage(emailLocator), "Please match the requested format.");

        // Case 5: Input last name + first name + invalid email + click submit button
        driver.findElement(By.xpath(emailLocator)).clear();
        driver.findElement(By.xpath(emailLocator)).sendKeys("123@$%#@##");
        driver.findElement(By.xpath(submitButtonLocator)).click();
        Assert.assertEquals(getElementValidationMessage(emailLocator), "Please enter an email address.");

        // Case 6: Input last name + first name + correct email + click submit button
        driver.findElement(By.xpath(emailLocator)).clear();
        driver.findElement(By.xpath(emailLocator)).sendKeys(generateEmailAddress());
        driver.findElement(By.xpath(submitButtonLocator)).click();
        Assert.assertEquals(getElementValidationMessage(passwordLocator), "Please fill out this field.");

    }

    //    @Test
    public void TC_05_HTML5_Validation_Message_Warranty() {
        driver.get("https://warranty.rode.com/");

        String buttonLoginLocator = "//button[contains(text(),'Login')]";
        String emailLoginLocator = "//div[contains(text(),'Login')]/following-sibling::div[@class='card-body']//input[@id='email']";
        String passwordLoginLocator = "//div[contains(text(),'Login')]/following-sibling::div[@class='card-body']//input[@id='password']";

        // Case 1: Login - Do not enter any data + click Login button
        driver.findElement(By.xpath(buttonLoginLocator));
        Assert.assertEquals(getElementValidationMessage(emailLoginLocator), "Please fill out this field.");

        // Case 2: Login - Input invalid email + click Login button
        driver.findElement(By.xpath(emailLoginLocator)).sendKeys("123@3$$^#");
        driver.findElement(By.xpath(buttonLoginLocator));
        Assert.assertEquals(getElementValidationMessage(emailLoginLocator), "Please enter an email address.");

        // Case 3: Login - Input valid email + click Login button
        driver.findElement(By.xpath(emailLoginLocator)).sendKeys(generateEmailAddress());
        driver.findElement(By.xpath(buttonLoginLocator));
        Assert.assertEquals(getElementValidationMessage(passwordLoginLocator), "Please fill out this field.");

        // Register: ....
    }

//    @Test
    public void TC_06_Remove_Attribute() {
        // Register to get username and pass
        driver.get("http://demo.guru99.com/");

        // Enter email address into email field + click submit button
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(generateEmailAddress());
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        // Get userID and password
        String userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        String password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
        System.out.println("UserID: " + userID);
        System.out.println("Pass: " + password);

        // Open Login page
        driver.get("http://demo.guru99.com/v4");

        // Login with userID and password + click login button
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        sleepInSecond(5);

        // Click on New Customer link
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        sleepInSecond(5);

        // Input all data to create new customer
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Cao Thu Thao");
        driver.findElement(By.xpath("//input[@name='rad1' and @value='f']")).click();

        // Remove date type of input + send key to text box + click submit button
        removeAttributeInDOM("//input[@id='dob']", "type");
        driver.findElement(By.xpath("//input[@id='dob']")).sendKeys("08/09/1999");

        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("Times City");
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Ha Noi");
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys("Hai Ba Trung");
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("100000");
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0123321123");
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(generateEmailAddress());
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@name='sub']")).click();
        sleepInSecond(5);

        // Verify create new customer successfully
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
    }

//    @Test
    public void TC_07_Create_An_Account() {
        navigateToUrlByJS("http://live.demoguru99.com/");

        // Click on My Account to navigate to account page
        clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");

        // Click on Create An Account button
        clickToElementByJS("//a[@title='Create an Account']");

        // Input all information
        driver.findElement(By.cssSelector("#firstname")).sendKeys("Cao");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Thu Thao");
        driver.findElement(By.cssSelector("#email_address")).sendKeys(generateEmailAddress());
        driver.findElement(By.cssSelector("#password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");

        // CLick on Register button
        clickToElementByJS("//button[@title='Register']");
        sleepInSecond(5);

        // Verify register successfully
//        Assert.assertTrue(isExpectedTextInInnerText("Thank you for registering with Main Website Store."));
        Assert.assertTrue(getInnerText().contains("Thank you for registering with Main Website Store."));

        // Log out
        clickToElementByJS("//a[@title='Log Out']");
        sleepInSecond(10);

        // Verify log out successfully
        Assert.assertEquals(executeForBrowser("return document.title;"), "Home page");
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
    }

    public void scrollToElement(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public String generateEmailAddress() {
        Random random = new Random();
        return random.nextInt(9999) + "@mail.com";
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
