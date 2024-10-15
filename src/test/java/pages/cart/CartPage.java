package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    String cartItemXpath = "//tbody//tr";
    String deleteAllItemBtnsXpath = "//tbody//tr//td[6]";
    String totalProductPriceXpath = "//p[@class='cart_total_price']";
    public String cartIsEmptyMessageXpath = "//b[text()='Cart is empty!']";
    public String proceedToCheckoutXpath = "//a[text()='Proceed To Checkout']";
    public String placeOrderXpath = "//a[text()='Place Order']";
    public String downloadInvoiceBtnXpath = "//a[text()='Download Invoice']";
    public WebElement getCartItem(WebDriver driver,String productNumber){
        return driver.findElement(By.xpath(cartItemXpath + "["+productNumber+"]"));
    }

    public String totalProductCost(WebDriver driver){
        List <WebElement> productsCost = driver.findElements(By.xpath(totalProductPriceXpath));
        String totalCost = productsCost.get(productsCost.size()-1).getText().replaceAll("[^\\d]","");
        return totalCost;
    }

    public void deleteAllCartItem(WebDriver driver){
        List<WebElement> products = driver.findElements(By.xpath(deleteAllItemBtnsXpath));
        for(WebElement product:products){
            product.click();
        }
    }

}
