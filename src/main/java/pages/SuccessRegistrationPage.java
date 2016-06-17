package pages;

import org.apache.log4j.Logger;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;

/**
 * Created by borys on 03.06.2016.
 */
public class SuccessRegistrationPage extends Page{

    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public SuccessRegistrationPage(WebDriverWrapper dr) {
        super(dr);
    }


    public boolean checkRegisteredEmail(String email) {
        return email.equals(web.getElementText("RegisteredEmailContainer"));
    }

    public void logOut() {
        web.clickLink("LogoutLink");
    }
}
