package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by ViTaLES on 27.05.2016.
 */
public class WebElementsActions {

    private WebDriver driver;
    private static WebDriverWait waitForElement;
    private static final Logger log = Logger.getLogger(WebElementsActions.class);


    public WebElementsActions(WebDriver driver) {
        this.driver = driver;
    }


    public void openPage(String siteURL){
        driver.get(siteURL);
    }

    public void clickElement(String elementLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        driver.findElement(ConfigData.ui(elementLocator)).click();
    }

    /**
     * Click a button
     */
    public void clickButton(String buttonLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        driver.findElement(ConfigData.ui(buttonLocator)).click();
        log.info("Click on Button " + buttonLocator);
    }

    /**
     * Click link
     */
    public void clickLink(String linkLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        driver.findElement(ConfigData.ui(linkLocator)).click();
        log.info("Click on Link " + linkLocator);
    }

    /**
     * Insert value into text input HTML field
     */
    public void input(String inputLocator, String inputData) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        driver.findElement(ConfigData.ui(inputLocator)).clear();
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(inputData);
        log.info("Input in " + inputLocator + ", value - " + inputData);
    }

    /**
     * Insert value into text input HTML field and Click ENTER for Field which used "Value" in the xpath expression
     */
    public void inputAndClickEnter(String inputLocator, String inputData) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        driver.findElement(ConfigData.ui(inputLocator)).clear();
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(inputData);
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(Keys.ENTER);
    }

    /**
     * Method is used to check that element is present on page.
     */
    public boolean isElementPresent(String elementLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        if (!driver.findElement(ConfigData.ui(elementLocator)).isDisplayed()) {
            return false;
        }
        return true;
    }

    //Haha!
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

    public void moveToElementAndClick(String moveToLocator, String clickToElement) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        WebElement webElement = driver.findElement(ConfigData.ui(moveToLocator));

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
    public void pressSpaceKey(String inputLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(Keys.SPACE);
    }

    public void pressEnterKey(String inputLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(Keys.ENTER);
    }

    public void pressESCAPEKey(String inputLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(Keys.ESCAPE);
    }

    public void pressPageUp(String inputLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(Keys.PAGE_UP);
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
            //log.info("utils.WebElementsActions Driver: <" + driver + "> cann't execute JavaScript");
            return false;
        }
    }

    /**
     * This method is used to do Focus to Element And Click
     * Use Actions class
     */
    public void doFocusToElementAndClick(String focusElementLocator, String elementLocator) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        new Actions(driver).moveToElement(getElement(focusElementLocator)).perform();
        log.info("Focus in to element" + focusElementLocator);

        driver.switchTo();

        if (isElementPresent(elementLocator)) {
            clickButton(elementLocator);
        }
    }


    public WebElement getElement(String elementLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        return driver.findElement(ConfigData.ui(elementLocator));
    }

    public List<WebElement> getElements(String elementsLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        return driver.findElements(ConfigData.ui(elementsLocator));
    }


    public String getElementText(String elementsLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        return driver.findElement(ConfigData.ui(elementsLocator)).getText();
    }

    /**
     * Insert value into text input HTML field without Clean
     */
    public void inputWithoutClean(String inputLocator, String inputData) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(inputData);
        log.info("Input in " + inputLocator + ", value - " + inputData);
    }


    /**
     * Select value from drop down list
     */
    public void selectFromList(String listLocator, String listValue) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        new Select(driver.findElement(ConfigData.ui(listLocator))).selectByValue(listValue);
    }
    /**
     * Wait the text in the element value specified time
     */
    public void waitTextPresent(WebElement elementLocator, String text) {
        log.info("*Start TO* Wait For Element _" + elementLocator + "_ Present");
        waitForElement.until(ExpectedConditions.textToBePresentInElement(elementLocator, text));
    }

    /**
     * Wait the text in the element (value tag!) specified time
     */
    public void waitTextPresentInElementValue(String elementLocator, int timeoutInS, String text) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInS);

        log.info("*Start TO* Wait For Text Present In Element _" + elementLocator + "_ Value");

        wait.until(ExpectedConditions.textToBePresentInElementValue(ConfigData.ui(elementLocator), text));
    }

    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
     * Advantages of this method over isElementPresent(By elementLocator); is that it expects the appearance of an element.
     */
    public void waitForElementPresent(String elementLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        log.info("*Start TO* Wait For Element _" + elementLocator + "_ Present");
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(ConfigData.ui(elementLocator)));
    }

    public void waitForElementDisappear(String elementLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        log.info("*Start TO* Wait For Element _" + elementLocator + "_ Present");
        waitForElement.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(ConfigData.ui(elementLocator))));
    }

    /**
     * An expectation for checking that an element is present on the DOM of a
     * page. This does not necessarily mean that the element is visible.
     *
     * @param elementLocator used to find the element
     */
    public void waitForPresenceOfElementLocated(String elementLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        log.info("*Start TO* Wait For Presence Of Element Located _" + elementLocator + "_");
        waitForElement.until(ExpectedConditions.presenceOfElementLocated(ConfigData.ui(elementLocator)));
    }

    /**
     * An expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param elementLocator used to find the element
     */
    public void waitForElementToBeClickable(String elementLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        log.info("*Start TO* Wait For Element _" + elementLocator + "_ To Be Clickable");
        waitForElement.until(ExpectedConditions.elementToBeClickable(ConfigData.ui(elementLocator)));
    }

    /**
     * An expectation for checking that an element is becomes invisible, but stay on the DOM.
     */
    public void waitForInvisibilityOfElement(String elementLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        log.info("*Start TO* Wait For Invisibility Of Element _" + elementLocator + "_ ");
        waitForElement.until(ExpectedConditions.invisibilityOfElementLocated(ConfigData.ui(elementLocator)));
    }

    /**
     * Wait for invisibility Of Element on page specified time
     */
    public void waitForElementNotVisible(String elementLocator, int timeoutInS) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInS);

        log.info("*Start TO* Wait For Element Not Visible _" + elementLocator + "_");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(ConfigData.ui(elementLocator)));
    }

    /**
     * Wait for invisibility Of Element on page
     */
    public void waitForElementNotVisible(String elementLocator) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        log.info("*Start TO* Wait For Element Not Visible _" + elementLocator + "_");
        waitForElement.until(ExpectedConditions.invisibilityOfElementLocated(ConfigData.ui(elementLocator)));
    }


}


