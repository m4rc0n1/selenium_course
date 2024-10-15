package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Map;

public class HomePage {
    public String continueShoppingBtnXpath = "//button[text()='Continue Shopping']";
    public String logoutBtnXpath = "//a[text()=' Logout']";
    public String usernameXpath(String username){
        return "//b[text()='"+username+"']";
    }

    public Map<String,String> navigationLinks = Map.of(
            "Home","//a[text()=' Home']",
            "Cart","//a[text()=' Cart']",
            "LoginRegister","//a[text()=' Signup / Login']");
    public void addProductToCart(WebDriver driver,String productNumber){
        WebElement productEl = driver.findElement(By.xpath("(//a[@data-product-id='"+productNumber+"'])[1]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", productEl);
        productEl.click();
    }

    public void navigateToPage(WebDriver driver,String navigateLink){
        driver.findElement(By.xpath(navigationLinks.get(navigateLink))).click();
    }
}
