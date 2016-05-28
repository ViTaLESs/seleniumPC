import org.openqa.selenium.WebDriver;

/**
 * Created by ViTaLES on 27.05.2016.
 */
public class LoginPage {

    WebElementsActions web;

    public LoginPage(WebDriver driver) {
        web = new WebElementsActions(driver);
    }

    public void fillLoginForm(String email, String password){
        web.input(".//input[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginUsername']", email);
        web.clickElement(".//input[@id='LoginPasswordText']");
        web.input(".//input[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginPassword']", password);
    }

    public void confirmLoginForm(){
        web.clickButton(".//a[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginButton']");
    }


    public void switchToMainPage() {
        //TODO
    }
}
