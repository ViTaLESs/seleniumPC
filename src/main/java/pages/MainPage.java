package pages;

import org.apache.log4j.Logger;
import utils.ClassNameUtil;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

/**
 * Created by ViTaLES on 27.05.2016.
 */
public class MainPage extends Page{

    private static final String MAIN_PAGE = PropertyLoader.loadProperty("site.url");
    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public MainPage(WebDriverWrapper dr) {
        super(dr, MAIN_PAGE);
    }

    public void clickLoginLink() {
        web.clickLink("LoginRegistrationLink");
        log.info("Login/Registration link is clicked");
    }

    public boolean isCurrentPageMain() {
        return getCurrentPageURL().equals("http://www.ellos.se");
    }

    public void moveToHerrGoodsPage() {
        web.clickLink("HerrSectionLink");
        log.info("\"Herr\" menu section is clicked");
    }
}