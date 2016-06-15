package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebElementsActions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ViTaLES on 27.05.2016.
 */
public class ProductPage {

    WebElementsActions web;
    private static final Logger log = Logger.getLogger(LoginPage.class);

    public ProductPage(WebDriver driver) {
        web = new WebElementsActions(driver);
    }



    public void checkDDLabelsPresent() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        //click

        List<WebElement> ddLabels = new ArrayList<>();
        ddLabels.addAll(web.getElements("ProductPageSizeDDLabels_SP"));

        //ddLabels.forEach();
    }

    public void clickOnSizeDD() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        web.clickButton("SizeDD");
    }
}
