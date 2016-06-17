package tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ClassNameUtil;


public class LoginTests extends Fixture {

//    Usual goods order flow

//    Tests TBD:
//    Success login                   +
//    Non-exist user login            +
//    Empty Login                     +
//    Empty pass                      +
//    Empty all                       +
//    Spaces in login and password    +
//    Tabs in login and password      +
//    Input 72k symbols in password   +-
//    Incorrect data                  +
//    ...

    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    @Test
    public void positiveLogin() {
        ellos.mainPage.openPage();

        ellos.web.refreshPage();
        ellos.mainPage.clickLoginLink();
        ellos.loginPage.fillLoginForm("ellostest@mailinator.com", "ellostest");
        ellos.loginPage.confirmLoginForm();
        ellos.loginPage.logOut();

        Assert.assertTrue(ellos.web.isElementPresent("LoginRegistrationLink"), "Logout link not found. Login failed");

    }

    /*//@Test
    public void nonExistUserValidation() throws Exception, NoSuchLocatorException {
        ellos.web.openPage(baseUrl);
        ellos.web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm("invalid@mail.com", "invalidpas");
        loginPage.confirmLoginForm();

        Assert.assertTrue(loginPage.checkNonExistUserErrorText(), "Non existant user error text incorrect or absent");
    }

    //@Test
    public void emptyEmailValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFillPassword("ellostest");
        loginPage.confirmLoginForm();
        loginPage.switchToMainPage();

        Assert.assertTrue(loginPage.checkEmptyEmailErrorText(), "Error about empty email in Login form is incorrect or absent");
    }

    //@Test
    public void emptyPasswordValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFillEmail("ellostest@mailinator.com");
        loginPage.confirmLoginForm();
        loginPage.switchToMainPage();


        Assert.assertTrue(loginPage.checkEmptyPasswordErrorText(), "Error about empty password is incorrect or absent");
    }

    //@Test
    public void emptyEmailAndPasswordValidation() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.confirmLoginForm();
        loginPage.switchToMainPage();


        Assert.assertTrue(loginPage.checkEmptyEmailPasswordErrorText(), "Error about email and password is incorrect or absent");
    }

    //    Check trancate spaces from begin and end of text function in e-mail annd password login input fields
    //@Test
    public void spacesTrancateSuccessLogin() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm("  ellostest@mailinator.com  ", "  ellostest  ");
        loginPage.confirmLoginForm();
        loginPage.logOut();

        Assert.assertTrue( web.isElementPresent("LogoutLink"), "Logout link is absent. Login failed");
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

        Assert.assertTrue(web.isElementPresent("LogoutLink"), "Logout link is absent. Login failed");
    }

    //@Test
    public void excessiveSymbolsInputError() throws Exception, NoSuchLocatorException {
        web.openPage(baseUrl);
        web.refreshPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFillEmail("ellostest@mailinator.com");
        loginPage.loginFillHugePassword();
        loginPage.confirmLoginForm();
        Assert.assertTrue(loginPage.checkCurrentURL(), "Error page is displayed");

        ErrorPage errorPage = new ErrorPage(driver);
        errorPage.moveToMainPage();

        *//*?????*//*
        Assert.assertTrue(mainPage.isCurrentPageMain(), "Redirection to main page is not performed");
    }
*/

}
