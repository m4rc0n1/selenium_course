package tests.automationExercise.e2e;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.WaitFileReader;
import utils.setup.BaseTest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PurchaseProductTest extends BaseTest {

    @Test(groups = {"regression"})
    public void purchaseProduct(){
//        1. Register new user
        homePage.navigateToPage(driver,"LoginRegister");
        String username = faker.name().username();
        driver.findElement(By.xpath(loginSignUpPage.nameInputXpath)).sendKeys(username);
        String email = faker.internet().emailAddress();
        driver.findElement(By.xpath(loginSignUpPage.emailSignUpXpath)).sendKeys(email);
        driver.findElement(By.xpath(loginSignUpPage.signUpBtnXpath)).click();
        HashMap<String,String> newUser = signUpPage.registerNewUser(driver,faker,email);
        driver.findElement(By.xpath(signUpPage.createAccountBtnXpath)).click();
        homePage.navigateToPage(driver,"Home");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath((homePage.logoutBtnXpath))));
        homePage.addProductToCart(driver,"3");
        driver.findElement(By.xpath(homePage.continueShoppingBtnXpath)).click();
        homePage.addProductToCart(driver,"4");
        homePage.navigateToPage(driver,"Cart");
        driver.findElement(By.xpath(cartPage.proceedToCheckoutXpath)).click();
        WebElement placeOrderEl = driver.findElement(By.xpath(cartPage.placeOrderXpath));
        actions.moveToElement(placeOrderEl).perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", placeOrderEl);
        String expectedInvoiceText = "Hi "+newUser.get("full_name")+", Your total purchase amount is "+cartPage.totalProductCost(driver)+". Thank you";
        placeOrderEl.click();
        driver.findElement(By.xpath(signUpPage.dataQaXpath("input","name-on-card"))).sendKeys(faker.name().firstName()+ faker.name().lastName());
        actions
                .sendKeys(Keys.TAB)
                .sendKeys(faker.finance().creditCard())
                .sendKeys(Keys.TAB)
                .sendKeys("123")
                .sendKeys(Keys.TAB)
                .sendKeys("11")
                .sendKeys(Keys.TAB)
                .sendKeys("30").perform();
        driver.findElement(By.xpath(signUpPage.dataQaXpath("button","pay-button"))).click();
        driver.findElement(By.xpath(cartPage.downloadInvoiceBtnXpath)).click();
        File downloadedFile = new File(downloadPath + "invoice.txt");
        WaitFileReader.waitForFileToBeAvailable(downloadedFile,15);
        String firstLineText = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(downloadedFile))){
            firstLineText = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error during reading invoice.txt");
        }

        Assert.assertEquals(firstLineText,expectedInvoiceText);
    }

    @Override
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        File downloadDir = new File(downloadPath);
        if(downloadDir.exists()){
            for(File file: downloadDir.listFiles()){
                file.delete();
            }
            downloadDir.delete();
        }
    }
}
