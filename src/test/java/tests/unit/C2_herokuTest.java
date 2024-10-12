package tests.unit;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.params.ParameterizedTest;
import utils.ScreenshotOnFailure;


import java.time.Duration;

@ExtendWith(ScreenshotOnFailure.class)
public class C2_herokuTest {
    String url = "https://the-internet.herokuapp.com/";
    WebDriver driver;

    @BeforeAll
    public static void initialize(){
        System.out.println("Heroku tests have been started");

    }

    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        ScreenshotOnFailure.setDriver(driver);
    }

    @Test
    @Description("This test verify that checkbox is Selected")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Heroku App")
    @Feature("Checkbox")
    @Story("Istifadeci verilmish checkboxu isarelemelidir")
    public void unitTests(){
        driver.get(url);
        WebElement checkBoxLink = driver.findElement(By.linkText("Checkboxes"));
        checkBoxLink.click();
        //Manually screenshot
//        Screenshot.takeScreenshot(driver, "Checkbox Page");

        WebElement checkBoxN2 = driver.findElement(By.xpath("//input[@type='checkbox'][2]"));
        Assertions.assertTrue(checkBoxN2.isSelected());
        Assertions.assertFalse(checkBoxN2.isEnabled());
//        Assertions.fail();
        //Manually screenshot
//        Screenshot.takeScreenshot(driver, "Checkbox 2 is Selected");
    }

    @ParameterizedTest
    @Description("Parametrlesdirilmis test")
    @Severity(SeverityLevel.MINOR)
    @ValueSource(strings = {"https://google.com","https://amazon.com"})
    public void parametrizedTest(String url){
        driver.get(url);
        driver.getTitle();
    }


    @AfterAll
    public static void tearDownAll(){
        System.out.println("Heroku test have been finished");
    }
}
