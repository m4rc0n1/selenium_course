package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.*;

public class ChromeDriverConfig {
    public static WebDriver getConfiguratedChromeDriver(String downloadFilePath){
        Map<String,Object> prefs = new HashMap<>();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        prefs.put("download.default_directory", downloadFilePath);
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver= new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
