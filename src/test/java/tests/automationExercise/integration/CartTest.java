package tests.automationExercise.integration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.setup.BaseTest;
import java.time.Duration;

public class CartTest extends BaseTest {
    @Test(groups = {"regression"})
    public void emptyCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        Steps
//        1. User adds product(s)
        homePage.addProductToCart(driver,"7");
        driver.findElement(By.xpath(homePage.continueShoppingBtnXpath)).click();
        homePage.addProductToCart(driver,"6");
//        2. User navigates to cart
        homePage.navigateToPage(driver,"Cart");
//        3. User deletes product(s)
        cartPage.deleteAllCartItem(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath((cartPage.cartIsEmptyMessageXpath))));
//        4. Verify Cart is empty! message is shown
        String actualResultText = driver.findElement(By.xpath(cartPage.cartIsEmptyMessageXpath)).getText();
        String expectedResultText = "Cart is empty!";
        Assert.assertEquals(actualResultText,expectedResultText);
    }
}
