package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.WebElementsActions;

import java.io.IOException;

/**
 * Created by ViTaLES on 27.05.2016.
 */
public class LoginPage {

    WebElementsActions web;
    private static final Logger log = Logger.getLogger(LoginPage.class);


    public LoginPage(WebDriver driver) {
        web = new WebElementsActions(driver);
    }

    public void fillLoginForm(String email, String password) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        web.input("LoginEmailField", email);
        web.clickElement("PasswordFieldPlaceholder");
        web.input("PasswordField", password);
        log.info("Fill login form correct");
    }

    public void confirmLoginForm() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        web.clickButton("LoginButton");
    }

    public void switchToMainPage() {
        //TODO
    }

    public void confirmForm(String confirmButton) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        web.clickButton(confirmButton);
    }



}


