package tests.automationExercise.integration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart.CartPage;
import pages.home.HomePage;

import java.time.Duration;

public class CartTest {
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
    public void emptyCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        HomePage homePage = new HomePage();
        CartPage cartPage = new CartPage();
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
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
