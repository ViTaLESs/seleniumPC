package tests;

import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;
import pages.MainPage;
import utils.WebElementsActions;

import java.util.concurrent.TimeUnit;


public class FirstTest {
    private static WebDriver driver;
    private String baseUrl = "http://www.ellos.se/";
    static WebElementsActions web;
    private static final Logger log = Logger.getLogger(FirstTest.class);

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
        web = new WebElementsActions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //log
    }

    //    @Test
    public void positiveLogin() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm("ellostest@mailinator.com", "ellostest");
        loginPage.confirmLoginForm();
        loginPage.logOut();

        System.out.println(web.isElementPresent("LogoutLink"));
    }

    //    @Test
    public void emptyEmailLoginValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFillPassword("ellostest");
        loginPage.confirmLoginForm();
        loginPage.switchToMainPage();

        System.out.println(loginPage.checkEmptyEmailErrorText());
    }

    //    @Test
    public void emptyEmaiPasswordValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFillEmail("ellostest@mailinator.com");
        loginPage.confirmLoginForm();
        loginPage.switchToMainPage();


        System.out.println(loginPage.checkEmptyPasswordErrorText());
    }

    //    @Test
    public void emptyEmailAndPasswordValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.confirmLoginForm();
        loginPage.switchToMainPage();


        System.out.println(loginPage.checkEmptyEmailPasswordErrorText());
    }



    //    @Test
    public void nonExistUserValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm("invalid@mail.com", "invalidpas");
        loginPage.confirmLoginForm();

        System.out.println(loginPage.checkNonExistUserErrorText());
    }

    //    Check trancate spaces from begin and end of text function in e-mail annd password login input fields
    //    @Test
    public void spacesTrancateSuccessLogin() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm("  ellostest@mailinator.com  ", "  ellostest  ");
        loginPage.confirmLoginForm();
        loginPage.logOut();

        System.out.println(web.isElementPresent("LogoutLink"));
    }

    //@Test
    public void tabsTrancateSuccessLogin() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm("   ellostest@mailinator.com\t", "\tellostest   ");
        loginPage.confirmLoginForm();
        loginPage.logOut();

        System.out.println(web.isElementPresent("LogoutLink"));
    }

    //    @Test
    public void execiveSymbolsInputError() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFillEmail("ellostest@mailinator.com");
        loginPage.loginFillHugePassword();
        loginPage.confirmLoginForm();
        System.out.println(loginPage.checkCurrentURL());

        ErrorPage errorPage = new ErrorPage(driver);
        errorPage.moveToMainPage();

        System.out.println(mainPage.checkCurrentURL());
    }

//    Registration functionality tests

    //        @Test
    public void NewUserSuccessRegistration() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        String email = loginPage.fillInValidRegistrationData();
        loginPage.uncheckSubscriptionCheckbox();
        loginPage.confirmRegistrationForm();

        SuccessRegistrationPage successRegistrationPage = new SuccessRegistrationPage(driver);
        System.out.println(successRegistrationPage.checkRegisteredEmail(email));
        successRegistrationPage.logOut();


    }

    //    @Test
    public void emailMaskRegistarion() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.regFill1email("ellostest.com");
        loginPage.regFill2email("ellostest.com");
        loginPage.regFill1pass("ellostest");
        loginPage.regFill2pass("ellostest");
        loginPage.confirmRegistrationForm();

        System.out.println(loginPage.checkIncorrectEmailMaskErrorText());
    }

    //    @Test
    public void emptyEmailAndPassRegistrationValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.confirmRegistrationForm();

        System.out.println(loginPage.checkEmptyEmailPassRegistrationErrorText());
    }

    //    @Test
    public void emptyEmailRegistrationValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.regFill1pass("ellostest");
        loginPage.regFill2pass("ellostest");
        loginPage.confirmRegistrationForm();

        System.out.println(loginPage.checkEmptyEmailRegistrationErrorText());
    }

    //    @Test
    public void emptyPassRegistrationValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.regFill1email("ellostest@mailinator.com");
        loginPage.regFill2email("ellostest@mailinator.com");
        loginPage.confirmRegistrationForm();

        System.out.println(loginPage.checkEmptyPassRegistrationErrorText());
    }

    //    @Test
    public void differentEmailsRegValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.regFill1email("ellostest@mailinator.com");
        loginPage.regFill2email("elloste@mailinator.com");
        loginPage.regFill1pass("ellostest");
        loginPage.regFill2pass("ellostest");
        loginPage.confirmRegistrationForm();

        System.out.println(loginPage.checkDifferentEmailsErrorText());
    }

    //    @Test
    public void differentPasswordsRegValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.regFill1email("ellostest@mailinator.com");
        loginPage.regFill2email("ellostest@mailinator.com");
        loginPage.regFill1pass("ellostest");
        loginPage.regFill2pass("ellost");
        loginPage.confirmRegistrationForm();

        System.out.println(loginPage.checkDifferentPasswordsErrorText());
    }

    //    @Test
    public void shortPasswordRegValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.regFill1email("ellostest@mailinator.com");
        loginPage.regFill2email("ellostest@mailinator.com");
        loginPage.regFill1pass("@");
        loginPage.regFill2pass("@");
        loginPage.confirmRegistrationForm();

        System.out.println(loginPage.checkShortPasswordErrorText());
    }

    public void registeredAccountValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.regFill1email("ellostest@mailinator.com");
        loginPage.regFill2email("ellostest@mailinator.com");
        loginPage.regFill1pass("ellos");
        loginPage.regFill2pass("ellos");
        loginPage.confirmRegistrationForm();

        System.out.println(loginPage.checkAlreadyRegisteredEmailErrorText());
    }



//    Usual goods order flow





//    Tests TBD:
//    Success login                   +
//    Non-exist user login            +
//    Empty Login                     +
//    Empty pass                      +
//    Empty all                       +
//    Mask of login                   -
//    Spaces in login and password    +
//    Tabs in login and password      +
//    Input 72k symbols in password   +-
//    Incorrect data                  +
//    ...


    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
        //log
    }

}