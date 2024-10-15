package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {
    private static WebDriver driver;

    public static void setDriver(WebDriver driver){
        TestListener.driver = driver;
    }
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        // Take a screenshot, log details, etc.
        if(driver !=null){
            takeScreenShot(result.getName());
        }else{
            System.out.println("WebDriver is null,can not take screenshot");
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Finished: " + context.getName());
    }

    private void takeScreenShot(String testName){
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try{
            FileUtils.copyFile(screenshot,new File("screenshots/" + testName + ".png"));
            System.out.println("Screenshot saved for test " + testName);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Failed to save screenshot for test " + testName);
        }
    }
}
