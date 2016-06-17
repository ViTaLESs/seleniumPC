package pages;

import utils.WebDriverWrapper;
import utils.WebElementsActions;

/**
 * Created by ViTaLES on 17.06.2016.
 */
public class Ellos {

    public WebElementsActions web;
    public MainPage mainPage;
    public LoginPage loginPage;
    public SuccessRegistrationPage successRegistrationPage;
    public ProductPage productPage;
    public CheckoutPage checkoutPage;

    public Ellos(WebDriverWrapper driver) {
        web  = new WebElementsActions(driver);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        successRegistrationPage = new SuccessRegistrationPage(driver);
        //screenShotMaker = new ScreenShotMaker(driver);
    }

}
