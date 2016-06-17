package pages;

import org.apache.log4j.Logger;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;

/**
 * Created by borys on 10.06.2016.
 */
public class TshortsPage extends Page{

    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public TshortsPage(WebDriverWrapper dr) {
        super(dr);
    }

    //Go to any t-short product page
    public void moveToPDP() {
        web.clickLink("FirstDisplayedTshortLink");
        log.info("First product link is clicked");
    }
}
