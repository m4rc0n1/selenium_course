package unit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.params.ParameterizedTest;



import java.time.Duration;

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
    }

    @Test
    public void unitTests(){
        driver.get(url);
        WebElement checkBoxLink = driver.findElement(By.linkText("Checkboxes"));
        checkBoxLink.click();

        WebElement checkBoxN2 = driver.findElement(By.xpath("//input[@type='checkbox'][2]"));
        Assertions.assertTrue(checkBoxN2.isSelected());
        Assertions.assertTrue(checkBoxN2.isEnabled());

        driver.quit();
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://google.com","https://amazon.com"})
    public void parametrizedTest(String url){
        driver.get(url);
        driver.getTitle();
    }


    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @AfterAll
    public static void tearDownAll(){
        System.out.println("Heroku test have been finished");
    }
}
