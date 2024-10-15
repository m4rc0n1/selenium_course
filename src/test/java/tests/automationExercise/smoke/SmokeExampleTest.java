package tests.automationExercise.smoke;

import org.testng.annotations.Test;

public class SmokeExampleTest {
    @Test(groups = {"smoke"})
    public void smokeExample(){
        System.out.println("Smoke test has been runned");
    }
}
