package utils.setup;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;

public class GroupTestSetup {
    @BeforeGroups(groups = {"sanity"})
    public void sanityGroupBefore(){
        System.out.println("Running setup for sanity group.");
    }
    @BeforeGroups(groups = {"regression"})
    public void setupForRegressionBefore() {
        System.out.println("Running setup for regression group.");
    }
    @BeforeGroups(groups = {"smoke"})
    public void setupForSmokeBefore() {
        System.out.println("Running setup for smoke group.");
    }
    @BeforeGroups(groups = {"skippedTests"})
    public void setupForSkippedTestBefore() {
        System.out.println("Running setup for skippedTests group.");
    }

    @AfterGroups(groups = {"sanity"})
    public void sanityGroupAfter(){
        System.out.println("Running tearDown for sanity group.");
    }
    @AfterGroups(groups = {"regression"})
    public void setupForRegressionAfter() {
        System.out.println("Running tearDown for regression group.");
    }
    @AfterGroups(groups = {"smoke"})
    public void setupForSmokeAfter() {
        System.out.println("Running tearDown for smoke group.");
    }
    @AfterGroups(groups = {"skippedTests"})
    public void setupForSkippedTestAfter() {
        System.out.println("Running tearDown for skippedTests group.");
    }
}
