package tests.automationExercise.integration;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.home.HomePage;
import pages.loginSignup.LoginSignUpPage;

import java.time.Duration;
import java.util.HashMap;

public class RegisterNewUserTest {

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
    public void registerNewUser(){
        HomePage homePage = new HomePage();
        LoginSignUpPage loginSignUpPage = new LoginSignUpPage();
        Faker faker = new Faker();
//        Steps
//        1. Click Signup / Login link
             homePage.navigateToPage(driver,"LoginRegister");
//        2. Enter username
             String username = faker.name().username();
             driver.findElement(By.xpath(loginSignUpPage.nameInputXpath)).sendKeys(username);
//        3. Enter email
             String email = faker.internet().emailAddress();
             driver.findElement(By.xpath(loginSignUpPage.emailSignUpXpath)).sendKeys(email);
//        4. Click Signup button
             driver.findElement(By.xpath(loginSignUpPage.signUpBtnXpath)).click();
//        5. Fill new user Data
             HashMap<String,String> newUser = loginSignUpPage.registerNewUser(driver,faker,email);
//        6. Click Create Account button
             driver.findElement(By.xpath(loginSignUpPage.createAccountBtnXpath)).click();
//        7. Login with new user to verify user is registered
             homePage.navigateToPage(driver,"LoginRegister");
//           loginSignUpPage.login(driver, newUser.get("email"),newUser.get("password"));
            String expectedUsernameText = username;
            String actualUsernameText = driver.findElement(By.xpath(homePage.usernameXpath(username))).getText();
            Assert.assertEquals(actualUsernameText,expectedUsernameText);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
