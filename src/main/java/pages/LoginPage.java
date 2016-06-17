package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.*;

import java.io.IOException;

/**
 * Created by ViTaLES on 27.05.2016.
 */
public class LoginPage extends Page{

    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public LoginPage(WebDriverWrapper dr) {
        super(dr);
    }


    public void fillLoginForm(String email, String password) {
        web.input("LoginEmailInput", email);
        web.clickElement("LoginPasswordInputClickLocator");
        web.input("LoginPasswordInput", password);
        log.info("Fill login form correct");
    }

    public void confirmLoginForm() {
        web.clickButton("LoginConfirmButton");
        log.info("Confim button clicked");
    }

    public void switchToMainPage() {
        web.clickElement("MainPageLogoLink");
    }

    public void confirmForm(String confirmButton) {
        web.clickButton(confirmButton);
    }

    public void loginFillEmail(String email) {
        web.input("LoginEmailInput", email);
    }

    public void loginFillPassword(String password) {
        web.clickElement("LoginPasswordInputClickLocator");
        web.input("LoginPasswordInput", password);
    }

    public void loginFillHugePassword()   {
        web.clickElement("LoginPasswordInputClickLocator");

        StringBuilder builder = new StringBuilder();
        for (int i=0; i < 3300; i++){
            builder.append("ellostest0123456789E");
        }

        log.info(builder);

        String text = builder.toString();

        web.input("LoginPasswordInput", text);
        web.input("LoginPasswordInput", text);

    }

    public String fillInValidRegistrationData() {
        Randomizer rand = new Randomizer();
/*
        web.input("EmailAddressField", web.generateRandomEmail(email));
        */
        String email = "ellostest".concat(Integer.toString(rand.getRandomInt(9999)).concat("@").concat("mailinator.com"));
        String password = "ellostest";

        web.input("RegEmailInput", email);
        web.input("RegEmailRepeatInput", email);
        web.clickElement("RegPasswordFieldClick");
        web.input("RegPasswordInput", password);
        web.clickElement("RegPasswordFieldRepeatClick");
        web.input("RegPasswordRepeatInput", password);
        log.info("Inputted email "+ email);
        log.info("Inputted password " + password);
        return email;
    }

    public void confirmRegistrationForm() {
        web.clickButton("ConfirmRegistrButton");
        log.info("Confirm registration button clicked");
    }

    public boolean checkNonExistUserErrorText() {
        String actualError = web.getElementText("LoginErrorTextArea");
        String expectedError = "* Fel e-postadress eller lösenord. Om du har\n"+
                "glömt dina inloggningsuppgifter kan du gå till\n" +
                "glömt lösenord sidan. Kommer du fortfarande inte\n" +
                "in på ditt konto kontakta oss här.";
        log.info("Invalid user error: "+ actualError);

        return expectedError.equals(actualError);
    }

    public boolean checkEmptyEmailErrorText() {
        String actualError = web.getElementText("LoginErrorTextArea");
        String expectedError = "*Måste ange ett användarnamn.";
        return expectedError.equals(actualError);
    }

    public boolean checkEmptyPasswordErrorText() {
        String actualError = web.getElementText("LoginErrorTextArea");
        String expectedError = "*Måste ange ett lösenord.";
        log.info("Displayed error text is: ");
        log.info(actualError);

        return expectedError.equals(actualError);
    }

    public boolean checkEmptyEmailPasswordErrorText() {
        String actualError = web.getElementText("LoginErrorTextArea");
        String expectedError = "*Måste ange ett användarnamn.\n"+"*Måste ange ett lösenord.";
        log.info("Displayed error text is: ");
        log.info(actualError);

        return expectedError.equals(actualError);
    }

    public void logOut() {
        web.clickLink("LogoutLink");
    }

    public boolean checkCurrentURL() {
        String expectedURL = "http://www.ellos.se/Error/error.aspx?keyword=errorpage_undefined_error";
        String actualURL = getCurrentPageURL();
        return expectedURL.equals(actualURL);
    }

    public void regFill1email(String s) {
        web.input("RegEmailInput", s);
    }

    public void regFill2email(String s) {
        web.input("RegEmailRepeatInput", s);
    }

    public void regFill1pass(String ellostest) {
        web.clickElement("RegPasswordFieldClick");
        web.input("RegPasswordInput", ellostest);
    }

    public void regFill2pass(String ellostest) {
        web.clickElement("RegPasswordFieldRepeatClick");
        web.input("RegPasswordRepeatInput", ellostest);
    }

    public void uncheckSubscriptionCheckbox() {

        web.clickElement("EmailSubscriptionCheckbox");
    }

    public boolean checkIncorrectEmailMaskErrorText() {
        String actualError = web.getElementText("RegistrationServerErrorsContainer");
        String expectedError = "* Felaktig e-postadress";
        log.info("Displayed error text is: ");
        log.info(actualError);

        return expectedError.equals(actualError);
    }

    public boolean checkEmptyEmailPassRegistrationErrorText() {

        String actualError = web.getElementText("RegistrationFrontendErrorsContainer");
        String expectedError = "*Vänligen kontrollera stavningen i din e-postadress och försök igen.\n" +
                                "*Bekräfta e-post\n" +
                                "*Du har inte valt ett lösenord.\n" +
                                "*Bekräfta lösenord";
        log.info("Displayed error text is: ");
        log.info(actualError);

        return expectedError.equals(actualError);
    }

    public boolean checkEmptyEmailRegistrationErrorText() {
        String actualError = web.getElementText("RegistrationFrontendErrorsContainer");
        String expectedError = "*Vänligen kontrollera stavningen i din e-postadress och försök igen.\n" +
                                "*Bekräfta e-post";
        log.info("Displayed error text is: ");
        log.info(actualError);

        return expectedError.equals(actualError);
    }

    public boolean checkEmptyPassRegistrationErrorText() {
        String actualError = web.getElementText("RegistrationFrontendErrorsContainer");
        String expectedError = "*Du har inte valt ett lösenord.\n" +
                                "*Bekräfta lösenord";
        log.info("Displayed error text is: ");
        log.info(actualError);

        return expectedError.equals(actualError);
    }

    public boolean checkDifferentEmailsErrorText() {
        String actualError = web.getElementText("RegistrationServerErrorsContainer");
        String expectedError = "* E-post och bekräfta e-post överensstämmer inte";
        log.info("Displayed error text is: ");
        log.info(actualError);

        return expectedError.equals(actualError);
    }

    public boolean checkDifferentPasswordsErrorText() {
        String actualError = web.getElementText("RegistrationServerErrorsContainer");
        String expectedError = "* Lösenord och bekräfta lösenord överensstämmer inte";
        log.info("Displayed error text is: ");
        log.info(actualError);

        return expectedError.equals(actualError);
    }


    public boolean checkShortPasswordErrorText() {
        String actualError = web.getElementText("RegistrationServerErrorsContainer");
        String expectedError = "* Lösenordet måste innehålla minst 4 tecken.";
        log.info("Displayed error text is: ");
        log.info(actualError);

        return expectedError.equals(actualError);
    }

    public boolean checkAlreadyRegisteredEmailErrorText() {
        String actualError = web.getElementText("RegistrationServerErrorsContainer");
        String expectedError = "* E-postadressen är redan registerad.";
        log.info("Displayed error text is: ");
        log.info(actualError);

        return expectedError.equals(actualError);
    }
}

