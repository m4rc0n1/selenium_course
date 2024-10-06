package utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

public class ScreenshotOnFailure implements TestWatcher {
    private static WebDriver driver;

    public static void setDriver(WebDriver driver){
        ScreenshotOnFailure.driver = driver;
    }
    @Override
    public void testFailed(ExtensionContext context, Throwable cause){
        Screenshot.takeScreenshot(driver, "Failure Screenshot");
        TestWatcher.super.testFailed(context,cause);
    }

    @Override
    public void testSuccessful(ExtensionContext context){
        Screenshot.takeScreenshot(driver,"Successfully passed");
        TestWatcher.super.testSuccessful(context);
    }
}
