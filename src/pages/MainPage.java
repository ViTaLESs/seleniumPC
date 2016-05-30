package pages;

import org.openqa.selenium.WebDriver;
import utils.WebElementsActions;

import java.io.IOException;

/**
 * Created by ViTaLES on 27.05.2016.
 */
public class MainPage {

    WebElementsActions web;

    public MainPage(WebDriver driver) {
        web = new WebElementsActions(driver);
    }

    public void clickLoginLink() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        web.clickLink("LoginLink");
    }

}
