package utils;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

public class CustomReporterListener implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDir){
        for(ISuite suite : suites){
            System.out.println("Generating report for suite" + suite.getName());
            suite.getResults().forEach((testName,result)->{
                System.out.println("Test Name " + testName);
                System.out.println("Passed Tests: " + result.getTestContext().getPassedTests().size());
                System.out.println("Failed Tests: " + result.getTestContext().getFailedTests().size());
            });
        }
    }
}
