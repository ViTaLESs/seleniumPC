import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;



public class firstTest {
    private static WebDriver driver;
    private String baseUrl = "http://www.ellos.se/";


    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testForLoginEmptyFieldsValidationError() throws Exception {
        driver.get(baseUrl);
        driver.navigate().refresh();
        driver.findElement(By.cssSelector("a.ellos.active")).click();

        driver.findElement(By.xpath(".//a[@id='showlogin']/span")).click();
        driver.findElement(By.xpath(".//a[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginButton']")).click();

        System.out.println(isElementPresent(By.xpath(".//div[@id='ctl00_ctl00_conMain_conMain_LoginControl_pnl' and @class='boxFrameError']")));
    }

    @Test
    public void testForLoginEmptyFieldsValidationError1() throws Exception {
        driver.get(baseUrl);
        driver.navigate().refresh();
        driver.findElement(By.cssSelector("a.ellos.active")).click();

        driver.findElement(By.xpath(".//a[@id='showlogin']/span")).click();
        driver.findElement(By.xpath(".//a[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginButton']")).click();

        System.out.println(isElementPresent(By.xpath(".//div[@id='ctl00_ctl00_conMain_conMain_LoginControl_pnl' and @class='boxFrameError']")));
    }

    //
    //HW =>>    1)Add positive test
    //          2)Add 2 methods for login field validation
    //


    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }



}