package pages.loginSignup;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;

public class LoginSignUpPage {
    public String nameInputXpath = "//input[@data-qa='signup-name']";
    public String emailSignUpXpath ="//input[@data-qa='signup-email']";
    public String signUpBtnXpath = "//button[text()='Signup']";
    public String incorrectDataXpath = "//p[text()='Your email or password is incorrect!']";
    String emailXpath="(//input[@name='email'])[1]";
    String passwordXpath = "(//input[@name='password'])[1]";
    String loginBtnXpath = "//button[text()='Login']";


    public void login(WebDriver driver, String email, String password){
        driver.findElement(By.xpath(emailXpath)).sendKeys(email);
        driver.findElement(By.xpath(passwordXpath)).sendKeys(password);
        driver.findElement(By.xpath(loginBtnXpath)).click();
    }


}
