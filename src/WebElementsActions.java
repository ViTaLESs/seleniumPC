import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by ViTaLES on 27.05.2016.
 */
public class WebElementsActions {

    private WebDriver driver;
    private static WebDriverWait waitForElement;


    public WebElementsActions(WebDriver driver) {
        this.driver = driver;
    }


    public void openPage(String siteURL){
        driver.get(siteURL);
    }

    public void clickElement(String elementLocator) {
        driver.findElement(By.xpath(elementLocator)).click();
    }

    /**
     * Click a button
     */
    public void clickButton(String buttonLocator) {
        driver.findElement(By.xpath(buttonLocator)).click();
        //log.info("Click on Button " + buttonLocator);
    }

    /**
     * Click link
     */
    public void clickLink(String linkLocator) {
        driver.findElement(By.xpath(linkLocator)).click();
        //log.info("Click on Link " + linkLocator);
    }

    /**
     * Insert value into text input HTML field
     */
    public void input(String inputLocator, String inputData) {
        driver.findElement(By.xpath(inputLocator)).clear();
        driver.findElement(By.xpath(inputLocator)).sendKeys(inputData);
        //log.info("Input in " + inputLocator + ", value - " + inputData);
    }

    /**
     * Insert value into text input HTML field and Click ENTER for Field which used "Value" in the xpath expression
     */
    public void inputAndClickEnter(String inputLocator, String inputData) {
        driver.findElement(By.xpath(inputLocator)).clear();
        driver.findElement(By.xpath(inputLocator)).sendKeys(inputData);
        driver.findElement(By.xpath(inputLocator)).sendKeys(Keys.ENTER);
    }
    /**
     * Method is used to check that element is present on page.
     */
    public boolean isElementPresent(String elementLocator) {
        if (!driver.findElement(By.xpath(elementLocator)).isDisplayed()) {
            return false;
        }
        return true;
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * This method is used to agree messages on pop-up windows
     */
    public boolean isAlertPresent() {
        boolean alertPresence = false;
        try {
            Alert alert = driver.switchTo().alert();
            alertPresence = true;
            alert.accept();
        } catch (NoAlertPresentException ex) {
            ex.printStackTrace();
            return alertPresence;
        }
        return alertPresence;
    }


    /**
     * This method is used to get text from pop-up windows
     */
    public String getAlertText() {
        String alertText;
        try {
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            alert.accept();
            //log.info("Alert text: " + alertText);
        } catch (NoAlertPresentException ex) {
            alertText = "Alert is not found";
            //log.error("Alert is not found");
            ex.printStackTrace();
        }
        return alertText;
    }

    public void moveToElementAndClick(String moveToLocator, String clickToElement) {
        WebElement webElement = driver.findElement(By.xpath(moveToLocator));

        Actions actions = new Actions(driver);
        actions.moveToElement(webElement);
        actions.perform();  //!!! always need
        clickButton(clickToElement);

        //log.info("moved To Element " + moveToLocator + "and clicked on" + clickToElement);
    }

    /**
     *Method#1 for refresh page
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     *Methods for pressing the keypad buttons
     */
    public void pressSpaceKey(String inputLocator) {
        driver.findElement(By.xpath(inputLocator)).sendKeys(Keys.SPACE);
    }

    public void pressEnterKey(String inputLocator) {
        driver.findElement(By.xpath(inputLocator)).sendKeys(Keys.ENTER);
    }

    public void pressESCAPEKey(String inputLocator) {
        driver.findElement(By.xpath(inputLocator)).sendKeys(Keys.ESCAPE);
    }

    public void pressPageUp(String inputLocator) {
        driver.findElement(By.xpath(inputLocator)).sendKeys(Keys.PAGE_UP);
    }


    /**
     * This method is used to wait for getting response from all Ajax requests
     */
    public boolean waitForAjaxResponse(int timeoutSeconds) throws InterruptedException {
        //TODO js executor
        if (driver instanceof JavascriptExecutor) {
            JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

            for (int i = 0; i < timeoutSeconds; i++) {
                Long numberOfConnections = (Long) jsDriver.executeScript("return jQuery.active");
                if (numberOfConnections instanceof Long) {
                    //log.debug("Number of active jQuery Ajax calls is <" + numberOfConnections + ">");

                    if (numberOfConnections == 0)
                        break;
                }
                // stay(1);
            }
            return false;
        } else {
            //log.info("WebElementsActions Driver: <" + driver + "> cann't execute JavaScript");
            return false;
        }
    }

    /**
     * This method is used to do Focus to Element And Click
     * Use Actions class
     */
/*    public void doFocusToElementAndClick(String focusElementLocator, String elementLocator) {
        new Actions(driver.getOriginalDriver()).moveToElement(getElement(focusElementLocator)).perform();
        log.info("Focus in to element" + focusElementLocator);

        driver.switchTo();

        if (isElementPresent(elementLocator)) {
            clickButton(elementLocator);
        }
    }*/


    public WebElement getElement(String elementLocator) {
        return driver.findElement(By.xpath(elementLocator));
    }

    public List<WebElement> getElements(String elementsLocator) {
        return driver.findElements(By.xpath(elementsLocator));
    }


    public String getElementText(String elementsLocator) {
        return driver.findElement(By.xpath(elementsLocator)).getText();
    }

    /**
     * Insert value into text input HTML field without Clean
     */
    public void inputWithoutClean(String inputLocator, String inputData) {
        driver.findElement(By.xpath(inputLocator)).sendKeys(inputData);
        //log.info("Input in " + inputLocator + ", value - " + inputData);
    }


    /**
     * Select value from drop down list
     */
    public void selectFromList(String listLocator, String listValue) {
        new Select(driver.findElement(By.xpath(listLocator))).selectByValue(listValue);
    }
}



