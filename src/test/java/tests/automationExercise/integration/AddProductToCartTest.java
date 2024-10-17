package tests.automationExercise.integration;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.setup.BaseTest;

public class AddProductToCartTest extends BaseTest {
    @Test(groups = {"regression"})
    @Description("Add product to cart without log in")
    public void addToCart(){
       WebElement loginBoxEl = driver.findElement(By.xpath(homePage.navigationLinks.get("LoginRegister")));
       Assert.assertTrue(loginBoxEl.isDisplayed());
       homePage.addProductToCart(driver,"1");
       String productNameText = driver.findElement(By.xpath("(//div[@class='productinfo text-center'])[1]//p")).getText();
       driver.findElement(By.xpath(homePage.continueShoppingBtnXpath)).click();
       homePage.navigateToPage(driver,"Cart");
       String firstCartItemText = cartPage.getCartItem(driver,"1").getText();
       Assert.assertTrue(firstCartItemText.contains(productNameText));
    }

    @Test(groups = {"regression"})
    @Description("Add product to cart without log in")
    public void addToCartWithUser(){
        WebElement loginBoxEl = driver.findElement(By.xpath(homePage.navigationLinks.get("LoginRegister")));
        Assert.assertTrue(loginBoxEl.isDisplayed());
        loginBoxEl.click();

        loginSignUpPage.login(driver, config.getProperty("emailPos"), config.getProperty("passwordPos"));

        homePage.addProductToCart(driver,"1");
        driver.findElement(By.xpath(homePage.continueShoppingBtnXpath)).click();
        String productNameText = driver.findElement(By.xpath("(//div[@class='productinfo text-center'])[1]//p")).getText();

        homePage.navigateToPage(driver,"Cart");
        String firstCartItemText = cartPage.getCartItem(driver,"1").getText();
        Assert.assertTrue(firstCartItemText.contains(productNameText));
    }

    @Override
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        cartPage.deleteAllCartItem(driver);
        driver.quit();
    }
}
