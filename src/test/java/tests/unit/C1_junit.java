package tests.unit;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C1_junit {
    //Annotation
    @Test
    public void test01(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        WebElement searchBox = driver.findElement(By.name("q"));

        //Assertion tutushdurma actualResult vs ExpectedResult
        //assertTrue = expectedResult ->true
        Assertions.assertTrue(searchBox.isDisplayed());
//        Assert.assertEquals(true,searchBox.isDisplayed());
        driver.quit();
    }
}
