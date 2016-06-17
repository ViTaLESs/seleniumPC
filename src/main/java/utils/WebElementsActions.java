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
    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());


    public WebElementsActions(WebDriver driver) {
        this.driver = driver;
        waitForElement = new WebDriverWait(driver, 20);
    }

    public void clickElement(String elementLocator) {
        driver.findElement(UIMappingSingleton.ui(elementLocator)).click();
    }

    /**
     * Click a button
     */
    public void clickButton(String buttonLocator) {
        driver.findElement(UIMappingSingleton.ui(buttonLocator)).click();
        log.info("Click on Button " + buttonLocator);
    }

    /**
     * Click link
     */
    public void clickLink(String linkLocator) {
        driver.findElement(UIMappingSingleton.ui(linkLocator)).click();
        log.info("Click on Link " + linkLocator);
    }

    /**
     * Insert value into text input HTML field
     */
    public void input(String inputLocator, String inputData) {
        driver.findElement(UIMappingSingleton.ui(inputLocator)).clear();
        driver.findElement(UIMappingSingleton.ui(inputLocator)).sendKeys(inputData);
        log.info("Input in " + inputLocator + ", value - " + inputData);
    }


    /**
     * Insert value into text input HTML field and Click ENTER for Field which used "Value" in the xpath expression
     */
    public void inputAndClickEnter(String inputLocator, String inputData) {
        driver.findElement(UIMappingSingleton.ui(inputLocator)).clear();
        driver.findElement(UIMappingSingleton.ui(inputLocator)).sendKeys(inputData);
        driver.findElement(UIMappingSingleton.ui(inputLocator)).sendKeys(Keys.ENTER);
        log.info("Input in " + inputLocator + ", value - " + inputData);
    }

    /**
     * Method is used to check that element is present on page.
     */
    public boolean isElementPresent(String elementLocator) {
        List<WebElement> list = driver.findElements(UIMappingSingleton.ui(elementLocator));

        if (list.size() == 0) {
            log.warn("Element _" + elementLocator + "_is NOT Present!");
            return false;
        } else {
            log.info("Element _" + elementLocator + "_ is Present");
            return list.get(0).isDisplayed();
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
            log.info("Alert text: " + alertText);
        } catch (NoAlertPresentException ex) {
            alertText = "Alert is not found";
            log.error("Alert is not found");
            ex.printStackTrace();
        }
        return alertText;
    }

    public void moveToElementAndClick(String moveToLocator, String clickToElement) {
        WebElement webElement = driver.findElement(UIMappingSingleton.ui(moveToLocator));

        Actions actions = new Actions(driver);
        actions.moveToElement(webElement);
        actions.perform();  //!!! always need
        clickButton(clickToElement);

        log.info("moved To Element " + moveToLocator + "and clicked on" + clickToElement);
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
        driver.findElement(UIMappingSingleton.ui(inputLocator)).sendKeys(Keys.SPACE);
        log.info("Space clicked on " + inputLocator);
    }

    public void pressEnterKey(String inputLocator) {
        driver.findElement(UIMappingSingleton.ui(inputLocator)).sendKeys(Keys.ENTER);
        log.info("Enter clicked on " + inputLocator);
    }

    public void pressESCAPEKey(String inputLocator) {
        driver.findElement(UIMappingSingleton.ui(inputLocator)).sendKeys(Keys.ESCAPE);
        log.info("Escape clicked on " + inputLocator);
    }

    public void pressPageUp(String inputLocator) {
        driver.findElement(UIMappingSingleton.ui(inputLocator)).sendKeys(Keys.PAGE_UP);
        log.info("PageUp clicked on " + inputLocator);
    }


    /**
     * This method is used to do Focus to Element And Click
     * Use Actions class
     */
    public void doFocusToElementAndClick(String focusElementLocator, String elementLocator) {
        new Actions(driver).moveToElement(getElement(focusElementLocator)).perform();
        log.info("Focus in to element" + focusElementLocator);
        driver.switchTo();
        if (isElementPresent(elementLocator)) {
            clickButton(elementLocator);
        }
    }


    public WebElement getElement(String elementLocator) {
        return driver.findElement(UIMappingSingleton.ui(elementLocator));
    }

    public List<WebElement> getElements(String elementsLocator) {
        return driver.findElements(UIMappingSingleton.ui(elementsLocator));
    }


    public String getElementText(String elementsLocator) {
        return driver.findElement(UIMappingSingleton.ui(elementsLocator)).getText();
    }

    /**
     * Insert value into text input HTML field without Clean
     */
    public void inputWithoutClean(String inputLocator, String inputData) {
        driver.findElement(UIMappingSingleton.ui(inputLocator)).sendKeys(inputData);
        log.info("Input in " + inputLocator + ", value - " + inputData);
    }


    /**
     * Select value from drop down list
     */
    public void selectFromList(String listLocator, String listValue) {
        new Select(driver.findElement(UIMappingSingleton.ui(listLocator))).selectByValue(listValue);
        log.info("ListLocator " + listLocator + ", value - " + listValue);
    }

    /**
     * Select first value from drop down list
     */
     public void selectFirstFromList(String listLocator) {
        new Select(driver.findElement(UIMappingSingleton.ui(listLocator))).getFirstSelectedOption();
        log.info("ListLocator " + listLocator);
    }

    /**
     * This method is used to wait for getting response from all Ajax requests
     */
    public boolean waitForAjaxResponse(int timeoutSeconds) {
        //TODO js executor
        if (driver instanceof JavascriptExecutor) {
            JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

            for (int i = 0; i < timeoutSeconds; i++) {
                Long numberOfConnections = (Long) jsDriver.executeScript("return jQuery.active");
                if (numberOfConnections != null) {
                    log.debug("Number of active jQuery Ajax calls is <" + numberOfConnections + ">");

                    if (numberOfConnections == 0)
                        break;
                }
                // stay(1);
            }
            return false;
        } else {
            log.info("utils.WebElementsActions Driver: <" + driver + "> cann't execute JavaScript");
            return false;
        }
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
    public void waitTextPresentInElementValue(String elementLocator, int timeoutInS, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInS);

        log.info("*Start TO* Wait For Text Present In Element _" + elementLocator + "_ Value");

        wait.until(ExpectedConditions.textToBePresentInElementValue(UIMappingSingleton.ui(elementLocator), text));
    }

    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
     * Advantages of this method over isElementPresent(By elementLocator); is that it expects the appearance of an element.
     */
    public void waitForElementPresent(String elementLocator) {
        log.info("*Start TO* Wait For Element _" + elementLocator + "_ Present");
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(UIMappingSingleton.ui(elementLocator)));
    }

    public void waitForElementDisappear(String elementLocator) {
        log.info("*Start TO* Wait For Element _" + elementLocator + "_ Present");
        waitForElement.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(UIMappingSingleton.ui(elementLocator))));
    }

    /**
     * An expectation for checking that an element is present on the DOM of a
     * page. This does not necessarily mean that the element is visible.
     *
     * @param elementLocator used to find the element
     */
    public void waitForPresenceOfElementLocated(String elementLocator) {
        log.info("*Start TO* Wait For Presence Of Element Located _" + elementLocator + "_");
        waitForElement.until(ExpectedConditions.presenceOfElementLocated(UIMappingSingleton.ui(elementLocator)));
    }

    /**
     * An expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param elementLocator used to find the element
     */
    public void waitForElementToBeClickable(String elementLocator) {
        log.info("*Start TO* Wait For Element _" + elementLocator + "_ To Be Clickable");
        waitForElement.until(ExpectedConditions.elementToBeClickable(UIMappingSingleton.ui(elementLocator)));
    }

    /**
     * An expectation for checking that an element is becomes invisible, but stay on the DOM.
     */
    public void waitForInvisibilityOfElement(String elementLocator) {
        log.info("*Start TO* Wait For Invisibility Of Element _" + elementLocator + "_ ");
        waitForElement.until(ExpectedConditions.invisibilityOfElementLocated(UIMappingSingleton.ui(elementLocator)));
    }

    /**
     * Wait for invisibility Of Element on page specified time
     */
    public void waitForInvisibilityOfElement(String elementLocator, int timeoutInS) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInS);

        log.info("*Start TO* Wait For Element Not Visible _" + elementLocator + "_");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(UIMappingSingleton.ui(elementLocator)));
    }

    /**
     * Wait for invisibility Of Element on page
     */
    public void waitForElementNotVisible(String elementLocator) {
        log.info("*Start TO* Wait For Element Not Visible _" + elementLocator + "_");
        waitForElement.until(ExpectedConditions.invisibilityOfElementLocated(UIMappingSingleton.ui(elementLocator)));
    }


}

