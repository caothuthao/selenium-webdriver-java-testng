package webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class Topic_11_User_Interaction_Part_II {

    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;

    String projectPath = System.getProperty("user.dir");
    String jsHelperPath = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";
//    String jqueryPath = projectPath + "\\dragAndDrop\\jquery_load_helper.js";

    @BeforeClass
    public void beforeClass() {
//        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//        driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver91.exe");
        driver = new ChromeDriver();

        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //    @Test
    public void TC_01_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Scroll to element (Need if using Firefox browser)
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));

        // Double Click on button
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        sleepInSecond(3);

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
    }

    //    @Test
    public void TC_02_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        // Right click on button
        action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        sleepInSecond(3);

        // Hover to cut item
        action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-cut"))).perform();
        sleepInSecond(3);

        // Verify Hover to cut item successfully
        Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-cut.context-menu-visible.context-menu-hover")).isDisplayed());

        // Click on cut item
        action.click(driver.findElement(By.cssSelector(".context-menu-icon-cut.context-menu-visible.context-menu-hover"))).perform();
        sleepInSecond(3);

        // Verify alert is opened successfully
        Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: cut");

        // Accept alert
        driver.switchTo().alert().accept();
    }

    //    @Test
    public void TC_03_Drag_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.id("draggable"));
        WebElement bigCircle = driver.findElement(By.id("droptarget"));

        action.dragAndDrop(smallCircle, bigCircle).perform();
        sleepInSecond(3);

        // Verify
        Assert.assertEquals(bigCircle.getText(), "You did great!");
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex(), "#03a9f4");

    }

//    @Test
    public void TC_04_Drag_Drop_HTML5_JS_JQuery_Css_Selector() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String sourceCss = "#column-a";
        String targetCss = "#column-b";

        // Inject Jquery lib to site
        // String jqueryScript = readFile(jqueryPath);
        // javascriptExecutor.executeScript(jqueryScript);

        // A to B
        String jsHelperScript = readFile(jsHelperPath) + "$('" + sourceCss + "').simulateDragDrop({ dropTarget: '" + targetCss + "'});";
        jsExecutor.executeScript(jsHelperScript);
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

        // B to A
        jsExecutor.executeScript(jsHelperScript);
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());

    }

//    @Test
    public void TC_05_Drag_Drop_HTML5_Robot_Xpath_Css() throws AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String sourceLocator = "//div[@id='column-a']";
        String targetLocator = "//div[@id='column-b']";

        drag_the_and_drop_html5_by_xpath(sourceLocator, targetLocator);
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

        drag_the_and_drop_html5_by_xpath(sourceLocator, targetLocator);
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());

    }

    public String readFile(String file) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(file);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
