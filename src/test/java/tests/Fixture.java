package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.Ellos;
import utils.ClassNameUtil;
import utils.PropertyLoader;
import utils.WebDriverWrapper;
import utils.WebElementsActions;

import java.util.concurrent.TimeUnit;

/**
 * Created by ViTaLES on 06.06.2016.
 */
public class Fixture {

    static WebDriverWrapper driver;
    public static Ellos ellos;

    private static final String IMPLICIT_WAIT = PropertyLoader.loadProperty("wait.timeout");
    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());


    @BeforeSuite
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Tool\\chromedriver.exe");
        driver = new WebDriverWrapper(new ChromeDriver());
        driver.manage().timeouts().implicitlyWait(Long.parseLong(IMPLICIT_WAIT), TimeUnit.SECONDS);
        driver.manage().window().maximize();

        ellos = new Ellos(driver);

        log.info("<=== Start ?????? tests ===>");
    }

    @AfterSuite
    public static void tearDown() throws Exception {
        log.info("<=== Finished ?????? tests ===>");
        log.info("Close Browser!");
        driver.quit();
    }

}
