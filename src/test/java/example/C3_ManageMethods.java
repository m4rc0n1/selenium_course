package example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C3_ManageMethods {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://amazon.com");
        driver.manage().window().maximize();
        driver.manage().window().getSize();
        driver.manage().window().setSize(new Dimension(1024,768));
        driver.manage().window().getPosition();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
}
