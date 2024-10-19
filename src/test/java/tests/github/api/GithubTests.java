package tests.github.api;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import pages.api.models.RepoModel;
import utils.ConfigReader;

import static org.hamcrest.Matchers.*;

public class GithubTests {
    ConfigReader config = new ConfigReader();
    String token = config.getProperty("githubPTA");
    @Test
    public void createNewRepo(){
        RestAssured.baseURI = config.getProperty("githubApiUrl");
        RepoModel repo = new RepoModel("test-repo-1", "Bu repo automated test vasitesile yaradildi",false);
        //Gson
        //Json
//        Converting Java object to JSON/XML format -> serialization
//        opposite (tersi) -> de-serialization
        Gson gson = new Gson();
        String jsonPayload = gson.toJson(repo);

        //POST
       RestAssured
                .given()
                        .auth().oauth2(token)
                        .contentType(ContentType.JSON)
                        .body(jsonPayload)
                .when().post("/user/repos")
                .then()
                        .statusCode(201)
                        .body("name",equalTo("test-repo-1"))
                        .body("description", equalTo("Bu repo automated test vasitesile yaradildi"))
                        .body("private",equalTo(false));
    }

    @Test(priority = 1)
    public void updateRepo(){
        RestAssured.baseURI = config.getProperty("githubApiUrl");
        RepoModel repo = new RepoModel(
                "test-repo-1-updated",
                "Test repo1 yenilendi",
                false);
        Gson gson = new Gson();
        String jsonPayload = gson.toJson(repo);

        RestAssured
        .given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(jsonPayload)
        .when()
                .patch("/repos/m4rc0n1/test-repo-1")
        .then()
                .statusCode(200)
                .body("description",equalTo("Test repo1 yenilendi"))
                .body("name",equalTo("test-repo-1-updated"))
                .body("private",equalTo(false));
    }

    @Test(priority = 2)
    public void deleteRepo(){
        RestAssured.baseURI = config.getProperty("githubApiUrl");

        RestAssured
        .given()
                .auth().oauth2(token)
        .when()
                .delete("/repos/m4rc0n1/test-repo-1-updated")
        .then()
                .statusCode(204);
    }
}
