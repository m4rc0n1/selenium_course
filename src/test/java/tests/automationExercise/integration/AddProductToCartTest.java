package tests.automationExercise.integration;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.cart.CartPage;
import pages.home.HomePage;
import pages.loginSignup.LoginSignUpPage;


import java.time.Duration;

public class AddProductToCartTest {
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://automationexercise.com/");
        driver.findElement(By.xpath("//p[text()='Consent']")).click();
    }

    @Test
    @Description("Add product to cart without log in")
    public void addToCart(){
       HomePage home = new HomePage();
       CartPage cart = new CartPage();

       WebElement loginBoxEl = driver.findElement(By.xpath(home.navigationLinks.get("LoginRegister")));
       Assert.assertTrue(loginBoxEl.isDisplayed());
       home.addProductToCart(driver,"1");
       String productNameText = driver.findElement(By.xpath("(//div[@class='productinfo text-center'])[1]//p")).getText();

       home.navigateToPage(driver,"Cart");
       String firstCartItemText = cart.getCartItem(driver,"1").getText();
       Assert.assertTrue(firstCartItemText.contains(productNameText));
    }

    @Test
    @Description("Add product to cart without log in")
    public void addToCartWithUser(){
        HomePage home = new HomePage();
        CartPage cart = new CartPage();
        LoginSignUpPage loginSignUp = new LoginSignUpPage();

        WebElement loginBoxEl = driver.findElement(By.xpath(home.navigationLinks.get("LoginRegister")));
        Assert.assertTrue(loginBoxEl.isDisplayed());
        loginBoxEl.click();

        loginSignUp.login(driver,"testercourse@gmail.com","Test123!");

        home.addProductToCart(driver,"(//a[@data-product-id='1'])[1]");
        String productNameText = driver.findElement(By.xpath("(//div[@class='productinfo text-center'])[1]//p")).getText();

        home.navigateToPage(driver,"Cart");
        String firstCartItemText = cart.getCartItem(driver,"1").getText();
        Assert.assertTrue(firstCartItemText.contains(productNameText));
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
