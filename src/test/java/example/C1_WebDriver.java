package example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class C1_WebDriver {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://amazon.com");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Amazon.com. Spend less. Smile more.";

//        if(actualTitle.equals(expectedTitle)){
//            System.out.println("Test passed");
//        }else{
//            System.out.println("Test failed");
//        }

        driver.getCurrentUrl(); //https://www.amazon.com/
        driver.getPageSource();
        driver.getWindowHandle();
        driver.getWindowHandles();
        driver.navigate().to("https://amazon.com"); // get le ferq geri qayitmaq olar
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.close(); // 1 tab bagliyir
        driver.quit(); //butun tablari ve ya browseri


    }
}
