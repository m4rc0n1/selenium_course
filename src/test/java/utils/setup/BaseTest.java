package utils.setup;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.cart.CartPage;
import pages.home.HomePage;
import pages.loginSignup.LoginSignUpPage;
import pages.loginSignup.signUp.SignUpPage;
import utils.ChromeDriverConfig;
import utils.ConfigReader;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage = new HomePage();
    protected LoginSignUpPage loginSignUpPage = new LoginSignUpPage();
    protected SignUpPage signUpPage = new SignUpPage();
    protected Faker faker = new Faker();
    protected ConfigReader config= new ConfigReader();
    protected CartPage cartPage = new CartPage();
    protected WebDriverWait wait;
    protected String downloadPath =  System.getProperty("user.dir")+ "\\downloads\\";
    protected Actions actions;
    @BeforeMethod(alwaysRun = true)
    public void setup(){
        System.out.println("Setting up new browser environment");
        if(config.getProperty("browser").equals("chrome")){
            driver = ChromeDriverConfig.getConfiguratedChromeDriver(downloadPath);
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(config.getProperty("url"));
        driver.findElement(By.xpath("//p[text()='Consent']")).click();
        actions = new Actions(driver);
        File downloadDir = new File(downloadPath);
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
            System.out.println("Download directory created: " + downloadPath);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        System.out.println("Setting down test");
        driver.quit();
    }
}
