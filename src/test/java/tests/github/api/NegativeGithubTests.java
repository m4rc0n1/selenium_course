package tests.github.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import utils.ConfigReader;
import static org.hamcrest.Matchers.*;
public class NegativeGithubTests {
    ConfigReader config = new ConfigReader();


    @Test
    public void invalidTokenTest(){
        String invalidToken = "invalidToken123";
        RestAssured.baseURI = config.getProperty("githubApiUrl");
        RestAssured.given().auth().oauth2(invalidToken)
                .when()
                .get("/user")
                .then()
                .statusCode(401)
                .body("message", equalTo("Bad credentials"));
    }

    @Test
    public void deleteNonExistingRepo(){
        String token = config.getProperty("githubPTA");
        RestAssured.baseURI=config.getProperty("githubApiUrl");
         RestAssured
                .given().auth().oauth2(token)
                .when()
                    .delete("/repos/m4rc0n1/non-existing-repo")
                .then()
                    .statusCode(401)
                 .body("message",equalTo("Bad credentials"));
    }
}
