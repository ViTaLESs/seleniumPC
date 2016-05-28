import org.openqa.selenium.WebDriver;

/**
 * Created by ViTaLES on 27.05.2016.
 */
public class MainPage {

    WebElementsActions web;

    public MainPage(WebDriver driver) {
        web = new WebElementsActions(driver);
    }

    public void clickLoginLink() {
        web.clickLink(".//a[@id='showlogin']/span");
    }






}
