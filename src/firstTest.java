import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;



public class firstTest {
    private static WebDriver driver;
    private String baseUrl = "http://www.ellos.se/";
    static WebElementsActions web;

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
        web = new WebElementsActions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //@Test
    public void Before_testPositiveLogin() throws Exception {
        web.openPage(baseUrl);
        web.refreshPage();

        web.clickLink(".//a[@id='showlogin']/span");

        web.input(".//input[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginUsername']", "ellostest@mailinator.com");
        web.clickElement(".//input[@id='LoginPasswordText']");
        web.input(".//input[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginPassword']", "ellostest");

        web.clickButton(".//a[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginButton']");

        System.out.println(web.isElementPresent(".//a[@id='showlogout']"));
    }

    @Test
    public void After_testPositiveLogin() throws Exception {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm("ellostest@mailinator.com", "ellostest");
        loginPage.confirmLoginForm();

        /*loginPage.switchToMainPage();

        ProductPage productPage = new ProductPage(driver);
        productPage.openStandartProduct();
        productPage.chooseColor();
        productPage.selectSize();

        productPage.addToBasket();
        productPage.swithcToCheckoutPage();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.confirmOrder();*/

        System.out.println(web.isElementPresent(".//a[@id='showlogout']"));
    }



    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }



}