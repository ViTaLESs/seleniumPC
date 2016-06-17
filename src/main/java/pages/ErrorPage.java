package pages;

import org.apache.log4j.Logger;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;

/**
 * Created by borys on 05.06.2016.
 */
public class ErrorPage extends Page{

    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public ErrorPage(WebDriverWrapper driver) {
        super(driver);
    }

    public void moveToMainPage() {
        web.clickButton("MainPageButton");
    }
}
