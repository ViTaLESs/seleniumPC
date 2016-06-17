package pages;

import org.apache.log4j.Logger;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;

/**
 * Created by borys on 10.06.2016.
 */
public class HerrPage extends Page{

    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public HerrPage(WebDriverWrapper dr) {
        super(dr);
    }


    public void moveToTshortsSection() {
        web.clickLink("TshortSectionLink");
        log.info("T-short menu section link is clicked");

    }
}
