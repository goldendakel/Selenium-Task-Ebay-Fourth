package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import commonElements.eBayCommonElements;
import utils.SeleniumHelper;

public class EBayPLP extends SeleniumHelper {
    
    public static void searchMonopoly() {
        eBayCommonElements.getSearchBar().sendKeys("Monopoly");
        eBayCommonElements.getSearchButton().click();
    }

    public static void selectMonopolyProduct() {

        //Verify that the first items has the title: Monopoly
        List<WebElement> monopolyProducts = new ArrayList<>();
        
        WebElement monopolyProductFirst = wait.get().until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@role='heading'])[2]")));
        monopolyProducts.add(monopolyProductFirst);
        WebElement monopolyProductSecond = wait.get().until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@role='heading'])[3]")));
        monopolyProducts.add(monopolyProductSecond);
        WebElement monopolyProductThird = wait.get().until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@role='heading'])[4]")));
        monopolyProducts.add(monopolyProductThird);
        
        for (int i = 0; i < monopolyProducts.size(); i++) {
            WebElement monopolyProduct = monopolyProducts.get(i);
            verifyProductNameAndShipping(monopolyProduct);
            if (i == 0) {
                verifyPriceProduct1(monopolyProduct);
            } else if (i == 1) {
                verifyPriceProduct2(monopolyProduct);
            } else if (i == 2) {
                verifyPriceProduct3(monopolyProduct);
            }
        }
        //Then click on the first item in order to navigate to details page
        monopolyProductFirst.click();
    }

    
    //Verify that there is a shipping to : Bulgaria
    public static void verifyProductNameAndShipping(WebElement monopolyProduct){
        String productName = monopolyProduct.getText();
        if(productName.contains("Monopoly")) {
            logger.info("Product contains 'Monopoly' in its title: {}", productName);
            WebElement productShippingOptionBulgaria = wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='s-zipcode-entry__label'])[2]")));
            String shippingOptionBulgaria = productShippingOptionBulgaria.getText();
            logger.info("Product can be shipped to: {}", shippingOptionBulgaria);
        } else {
            logger.info("Product doesn't contain monopoly in its name: {}", productName);
        }
    }

    //Verify that there is a price of the item
    private static String priceProduct1PLP;

    public static void verifyPriceProduct1(WebElement monopolyProduct) {
        WebElement priceProduct1PlpElement = wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='s-item__price'])[2]")));
        String priceProduct1inDollars = priceProduct1PlpElement.getText().replaceAll("[^\\d.,]", "");
        
        if (priceProduct1PlpElement.isDisplayed()) {
            logger.info("First Product displays with price: ${}", priceProduct1inDollars);
        } else {
            logger.info("Product doesn't have price");
        }
            priceProduct1PLP = priceProduct1inDollars;
    }

    public static String getPriceProduct1() {
        return priceProduct1PLP;
    }

    private static String priceProduct2PLP;

    public static void verifyPriceProduct2(WebElement monopolyProduct){
        WebElement priceProduct2PLPElement = wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='s-item__price'])[3]")));
        String priceProduct2inDollars = priceProduct2PLPElement.getText().replaceAll("[^\\d.,]", "");
        if(priceProduct2PLPElement.isDisplayed()) {
            logger.info("Second Product displays with price: ${}", priceProduct2inDollars);
        } else {
            logger.info("Product doesn't have price");
        }
        priceProduct2PLP = priceProduct2inDollars;

    }
    public static String getPriceProduct2() {
        return priceProduct2PLP;
    }

    private static String priceProduct3PLP;

    public static void verifyPriceProduct3(WebElement monopolyProduct){
        WebElement priceProduct3PLPElement = wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='s-item__price'])[4]")));
        String priceProduct3inDollars = priceProduct3PLPElement.getText().replaceAll("[^\\d.,]", "");
        if(priceProduct3PLPElement.isDisplayed()) {
            logger.info("Third Product displays with price: ${}", priceProduct3inDollars);
        } else {
            logger.info("Product doesn't have price");
        }
        priceProduct3PLP = priceProduct3inDollars;
    }
    public static String getPriceProduct3() {
        return priceProduct3PLP;
    }


  



}
