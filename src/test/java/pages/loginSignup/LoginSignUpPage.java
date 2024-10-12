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
    String emailXpath="(//input[@name='email'])[1]";
    String passwordXpath = "(//input[@name='password'])[1]";
    String loginBtnXpath = "//button[text()='Login']";
    String genderRadioXpath = "(//input[@type='radio'])[1]";
    String passwordRegXpath = "//input[@type='password']";
    String selectOptionXpath = "//option[2]";
    String checkBoxXpath = "//input[@type='checkbox']";
    public String createAccountBtnXpath = dataQaXpath("button","create-account");
    String dataQaXpath(String tagName,String dataQaName){
        return "(//"+tagName + "[@data-qa='"+ dataQaName + "'])";
    }

    public void login(WebDriver driver, String email, String password){
        driver.findElement(By.xpath(emailXpath)).sendKeys(email);
        driver.findElement(By.xpath(passwordXpath)).sendKeys(password);
        driver.findElement(By.xpath(loginBtnXpath)).click();
    }

    public HashMap<String,String> registerNewUser(WebDriver driver, Faker faker,String email){
        String password = faker.internet().password();
        Actions actions = new Actions(driver);

        WebElement genderRadioEl = driver.findElement(By.xpath(genderRadioXpath));
        genderRadioEl.click();

        WebElement passwordRegEl = driver.findElement(By.xpath(passwordRegXpath));
        passwordRegEl.sendKeys(password);

        WebElement dayDropdownEl = driver.findElement(By.xpath(dataQaXpath("select", "days")));
        Select daySelect = new Select(dayDropdownEl);
        daySelect.selectByIndex(2);

        WebElement monthDropdownEl = driver.findElement(By.xpath(dataQaXpath("select", "months")));
        Select monthSelect = new Select(monthDropdownEl);
        monthSelect.selectByIndex(2);

        WebElement yearDropdownEl = driver.findElement(By.xpath(dataQaXpath("select", "years")));
        Select yearSelect = new Select(yearDropdownEl);
        yearSelect.selectByIndex(2);

        driver.findElement(By.xpath(checkBoxXpath)).click();
        WebElement firstNameEl = driver.findElement(By.xpath(dataQaXpath("input","first_name")));
        firstNameEl.sendKeys(faker.name().firstName());
        WebElement lastName = driver.findElement(By.xpath(dataQaXpath("input","last_name")));
        lastName.sendKeys(faker.name().lastName());
        WebElement address = driver.findElement(By.xpath(dataQaXpath("input","address")));
        address.sendKeys(faker.address().country());
        WebElement countryInputEl = driver.findElement(By.xpath(dataQaXpath("select","country")));
        Select countrySelect = new Select(countryInputEl);
        countrySelect.selectByIndex(2);

        actions.moveToElement(countryInputEl).perform();
        actions
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().state())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().city())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().zipCode())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.phoneNumber().cellPhone())
                .perform();
        HashMap<String,String> userData = new HashMap<>();
        userData.put("email",email);
        userData.put("password",password);
        return userData;
    }
}
