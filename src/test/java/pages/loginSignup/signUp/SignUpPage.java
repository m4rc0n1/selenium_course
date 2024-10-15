package pages.loginSignup.signUp;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;

public class SignUpPage {
    String genderRadioXpath = "(//input[@type='radio'])[1]";
    String passwordRegXpath = "//input[@type='password']";
    String checkBoxXpath = "//input[@type='checkbox']";
    public String createAccountBtnXpath = dataQaXpath("button","create-account");
    public String dataQaXpath(String tagName,String dataQaName){
        return "(//"+tagName + "[@data-qa='"+ dataQaName + "'])";
    }

    public HashMap<String,String> registerNewUser(WebDriver driver, Faker faker, String email){
        String password = faker.internet().password();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
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
        firstNameEl.sendKeys(firstName);
        WebElement lastNameEl = driver.findElement(By.xpath(dataQaXpath("input","last_name")));
        lastNameEl.sendKeys(lastName);
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
        userData.put("full_name",firstName + " " + lastName);
        return userData;
    }
}
