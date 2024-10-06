package example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C2_Locators {
    public static void main(String[] args) {
        String searchBoxId = "//input[@placeholder='Search Amazon']";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://amazon.com");
//        WebElement searchBoxEl = driver.findElement(By.cssSelector(searchBoxId));
//        WebElement searchBoxEl = driver.findElement(By.id(searchBoxId));
//        WebElement searchBoxEl = driver.findElement(By.className(searchBoxId));
//          WebElement searchBoxEl = driver.findElement(By.xpath(searchBoxId));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        WebElement dismissEl = driver.findElement(By.xpath("//input[@data-action-type='DISMISS']"));
        dismissEl.click();
        WebElement skipEl = driver.findElement(By.linkText("Registry"));
        skipEl.click();


//        searchBoxEl.sendKeys("laptop", Keys.ENTER);

    }
}
