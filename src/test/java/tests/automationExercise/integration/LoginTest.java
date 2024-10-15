package tests.automationExercise.integration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.setup.BaseTest;

public class LoginTest extends BaseTest {
    @DataProvider(name = "loginData")
    public Object[][] getData(){
        return new Object[][]{
                {"testercourse1@gmail.com","Tester123!",false},
                {config.getProperty("emailPos"),config.getProperty("passwordPos"),true}
        };
    }

    @Test(dataProvider = "loginData", groups = {"smoke"})
    public void loginTest(String email, String password,boolean isPositiveTest){
        homePage.navigateToPage(driver,"LoginRegister");
        loginSignUpPage.login(driver,email,password);
        if(isPositiveTest){
            Assert.assertTrue(driver.findElement(By.xpath(homePage.logoutBtnXpath)).isDisplayed());
        }else{
            Assert.assertTrue(driver.findElement(By.xpath(loginSignUpPage.incorrectDataXpath)).isDisplayed());
        }
    }

    @Test(groups = {"skippedTests"})
    public void failedTest(){
        Assert.assertTrue(false);
    }
   }
