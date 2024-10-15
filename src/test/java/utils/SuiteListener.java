package utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener  implements ISuiteListener {

    @Override
    public void onStart(ISuite suite){
        System.out.println("Suite " + suite.getName()+ " started");
    }

    @Override
    public void onFinish(ISuite suite){
        System.out.println("Suite " + suite.getName() + " finished");
        suite.getResults().forEach((testName,result)->{
            System.out.println("Test: " +testName);
            System.out.println("Passed tests: " + result.getTestContext().getPassedTests().size());
            System.out.println("Failed tests: " + result.getTestContext().getFailedTests().size());
            System.out.println("Skipped tests: " + result.getTestContext().getSkippedTests().size());
        });
    }
}
