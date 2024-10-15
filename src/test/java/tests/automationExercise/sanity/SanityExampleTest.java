package tests.automationExercise.sanity;

import org.testng.annotations.Test;

public class SanityExampleTest {
    @Test(groups = {"sanity"})
    public void sanityExample(){
        System.out.println("Sanity test has been runned");
    }
}
