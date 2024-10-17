package utils.setup;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(String browser){
        System.out.println("Setting up new browser environment");
//        if(config.getProperty("browser").equals("chrome")){
//            driver = ChromeDriverConfig.getConfiguratedChromeDriver(downloadPath);
//        }
        switch(browser.toLowerCase()){
            case "chrome":
                driver = ChromeDriverConfig.getConfiguratedChromeDriver(downloadPath);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported " + browser);
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
