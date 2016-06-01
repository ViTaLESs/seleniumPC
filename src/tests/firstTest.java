package tests;

import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;
import pages.MainPage;
import utils.WebElementsActions;

import java.util.concurrent.TimeUnit;


public class firstTest {
    private static WebDriver driver;
    private String baseUrl = "http://www.ellos.se/";
    static WebElementsActions web;
    private static final Logger log = Logger.getLogger(firstTest.class);

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
        web = new WebElementsActions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void positiveLogin() throws Exception {
        web.openPage(baseUrl);
        web.refreshPage();
        log.info("1");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();
        log.trace("2");
        log.debug("3");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm("ellostest@mailinator.com", "ellostest");
        loginPage.confirmLoginForm();

        System.out.println(web.isElementPresent(".//a[@id='showlogout']"));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

}