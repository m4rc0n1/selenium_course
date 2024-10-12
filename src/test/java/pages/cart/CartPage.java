package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    String cartItemXpath = "//tbody//tr";
    String deleteAllItemBtnsXpath = "//tbody//tr//td[6]";
    public String cartIsEmptyMessageXpath = "//b[text()='Cart is empty!']";

    public WebElement getCartItem(WebDriver driver,String productNumber){
        return driver.findElement(By.xpath(cartItemXpath + "["+productNumber+"]"));
    }

    public void deleteAllCartItem(WebDriver driver){
        List<WebElement> products = driver.findElements(By.xpath(deleteAllItemBtnsXpath));
        for(WebElement product:products){
            product.click();
        }
    }

}
