package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import utils.WebElementsActions;

import java.util.concurrent.TimeUnit;

/**
 * Created by ViTaLES on 30.05.2016.
 */
public class firstTestPO {

    private static WebDriver driver;
    private String baseUrl = "http://www.ellos.se/";
    static WebElementsActions web;


    @FindBy(id = "j_idt5:nome")
    private WebElement inputNome;
    //private WebElement Input_Name = driver.findElement(By.id("vcxcsfdfsf"));

    @FindBy(id = "j_idt5:idade")
    private WebElement inputIdade1;

    @FindBy(id = "j_idt5:nome")
    private WebElement inputNome2;

    @FindBy(id = "j_idt5:idade")
    private WebElement inputIdade3;

    @FindBy(id = "j_idt5:nome")
    private WebElement inputNome4;

    @FindBy(id = "j_idt5:idade")
    private WebElement inputIdade5;

    @FindBy(id = "j_idt5:nome")
    private WebElement inputNome6;

    @FindBy(id = "j_idt5:idade")
    private WebElement inputIdade7;



    @Test
    public void digitarTexto() {

        inputNome.sendKeys("Diego");
        inputIdade5.sendKeys("29");
        inputIdade5.sendKeys("29");
        inputIdade5.sendKeys("29");
        inputIdade5.sendKeys("29");
        inputIdade5.sendKeys("29");
        //...
    }




    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
        web = new WebElementsActions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

}
