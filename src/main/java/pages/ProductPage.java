package pages;

import org.apache.log4j.Logger;
import org.testng.Assert;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;

/**
 * Created by ViTaLES on 27.05.2016.
 */
public class ProductPage extends Page{

    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public ProductPage(WebDriverWrapper dr) {
        super(dr);
    }


    public void openStandartProduct() {
        //TODO
    }

    public void chooseColor() {

    }

    public void selectSize() {

    }

    public void addToBasket() {

    }

    public void swithcToCheckoutPage() {

    }

    public void checkButtonsPresence() {
        if (isAddToBusketButtonPresent()&& isAddedToBusketGoodsButtonPresent() && isEllosMainButtonPresent()){
            log.info("All buttons are present");
        } else {
            log.error("is add to busket button present -" + isAddToBusketButtonPresent()+ "\n" + "is added to busket goods button present -" +
                    isAddedToBusketGoodsButtonPresent()+ "\n" + "is Ellos main button present -" + isEllosMainButtonPresent());
            Assert.fail("is buy button present -" + isAddToBusketButtonPresent()+ "\n" + "is added to busket goods button present -" +
                    isAddedToBusketGoodsButtonPresent()+ "\n" + "is Ellos main button present -" + isEllosMainButtonPresent());

        }


    }

    private boolean isEllosMainButtonPresent() {
        return web.isElementPresent("EllosMainButton");
    }

    private boolean isAddedToBusketGoodsButtonPresent() {
        return web.isElementPresent("AddedToBusketGoodsButton");
    }

    private boolean isAddToBusketButtonPresent() {
        return web.getElementText("AddToBasketButton").equals("Lägg i varukorgen");
    }

    public void checkProductAttributePresence() {
        if (isColorDropdownListPresent() && isSizeDropdownListPresent() && isPricePresent() && isNamePresent()){
            log.info("All product attributes are present");
        } else {
            log.error("is color drop-down list present -"+isColorDropdownListPresent()+"\n"+
                    "is size drop-down list present -"+isSizeDropdownListPresent()+"\n"+
                    "is price present -"+isPricePresent()+"\n"+
                    "is product name present -"+isNamePresent());
            Assert.fail("is color drop-down list present -"+isColorDropdownListPresent()+"\n"+
                    "is size drop-down list present -"+isSizeDropdownListPresent()+"\n"+
                    "is price present -"+isPricePresent()+"\n"+
                    "is product name present -"+isNamePresent());

        }

    }

    private boolean isColorDropdownListPresent() {
        return web.isElementPresent("ColorDropdownList");
    }

    private boolean isSizeDropdownListPresent() {
        return web.isElementPresent("SizeDropdownList");
    }

    private boolean isPricePresent() {
        return web.isElementPresent("PriceText");
    }

    private boolean isNamePresent() {
        return web.isElementPresent("NameText");
    }

    public void checkColorDropdownlist() {
        if (isFirstElementSelected()){
            log.info("First element of color DDL successfully selected");
        } else {
            log.error("is first element of color DDL selected - " + isFirstElementSelected());
            Assert.fail("is first element of color DDL selected - " + isFirstElementSelected());
        }
    }

    private boolean isFirstElementSelected() {
        web.selectFirstFromList("ColorDropdownList");
        return web.getElementText("ColorDDLtextContainer").equals(web.getElementText("FirstElementOfColorDDL"));
    }

    public void switchToMainPage() {
        web.clickLink("MainPageLogoLink");
        log.info("Logo link is clicked");
    }

    public void checkAddToCartFunctionality() {
        if (isAddedToCartIsCorrect()){
            log.info("Product added to cart is correct");
        } else {
            log.error("Is added to cart product is correct - " + isAddedToCartIsCorrect());
        }
    }

    private boolean isAddedToCartIsCorrect() {
        web.selectFirstFromList("ColorDropdownList");
        web.selectFirstFromList("SizeDropdownList");
        web.clickButton("AddToBasketButton");


        String selectedSize = web.getElementText("SizeDDLtextContainer");
        String selectedPrice = web.getElementText("PriceText");

        web.clickElement("CartPanelLink");

        String cartSize = web.getElementText("ProductSizeOnMiniCart_SP");
        String cartPrice = web.getElementText("ProductPriceOnMiniCart_SP");

        return !web.getElementText("ProductAmountOnMiniCart_SP").equals(null)
                && selectedSize.equals(cartSize)
                && selectedPrice.equals(cartPrice)
                && !web.getElementText("ProductNameOnMiniCart_SP").equals(null);
    }

    public void checkSizeDropdownlist() {
        if (isFirstSizeElementSelected()){
            log.info("First element of size DDL successfully selected");
        } else {
            log.error("is first element of size DDL selected - " + isFirstSizeElementSelected());
            Assert.fail("is first element of size DDL selected - " + isFirstSizeElementSelected());
        }
    }

    private boolean isFirstSizeElementSelected() {
        web.selectFirstFromList("SizeDropdownList");
        return web.getElementText("SizeDDLtextContainer").equals(web.getElementText("FirstElementOfSizeDDL"));
    }


    public void checkProductImage () {
        if(isImagePresent()
                && isImageViewable()
                && isNavigationArrowsPresent()
                && isImageChangeableViaNavigationArrows()){
            log.info("Image displaying is correct");
        } else {
            log.error("Image is present -" + isImagePresent() +
                    "Image is viewable -" + isImageViewable()+
                    "Navigation arrows present -" + isNavigationArrowsPresent() +
                    "Image is changeable via navigation arrows" + isImageChangeableViaNavigationArrows());
            Assert.fail("Image is present -" + isImagePresent() +
                    "Image is viewable -" + isImageViewable()+
                    "Navigation arrows present -" + isNavigationArrowsPresent() +
                    "Image is changeable via navigation arrows" + isImageChangeableViaNavigationArrows());
        }
    }

    private boolean isImagePresent() {
        return web.isElementPresent("MainImageContainer_SP");
    }
    private boolean isImageViewable() {
        web.clickElement("MainImageContainer_SP");
        log.info("Image is clicked");
        boolean presence = web.isElementPresent("ImageViewModeCloseButton_SP");
        web.clickButton("ImageViewModeCloseButton_SP");
        log.info("Close button for view mode of product is clicked");
        return presence;
    }

    private boolean isNavigationArrowsPresent() {
        return web.isElementPresent("ForwardArrowButtonForImage_SP") &
                web.isElementPresent("BackwardArrowButtonForImage_SP");
    }

    private boolean isImageChangeableViaNavigationArrows() {
        String val1 = web.getElement("MainImageContainer_SP").getAttribute("src");
        log.info("Receive src attribute from displayed image");
        web.clickButton("ForwardArrowButtonForImage_SP");
        String val2 = web.getElement("MainImageContainer_SP").getAttribute("src");
        log.info("Receive src attribute from displayed image after navigation arrow button clicked");
        return !val1.equals(val2);
    }

}
