package tests.automationExercise.integration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.setup.BaseTest;
import java.util.HashMap;

public class RegisterNewUserTest extends BaseTest {
    @Test(groups = {"regression"})
    public void registerNewUser(){
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
             HashMap<String,String> newUser = signUpPage.registerNewUser(driver,faker,email);
//        6. Click Create Account button
             driver.findElement(By.xpath(signUpPage.createAccountBtnXpath)).click();
//        7. Login with new user to verify user is registered
             homePage.navigateToPage(driver,"LoginRegister");
//           loginSignUpPage.login(driver, newUser.get("email"),newUser.get("password"));
            String expectedUsernameText = username;
            String actualUsernameText = driver.findElement(By.xpath(homePage.usernameXpath(username))).getText();
            Assert.assertEquals(actualUsernameText,expectedUsernameText);
    }
}
