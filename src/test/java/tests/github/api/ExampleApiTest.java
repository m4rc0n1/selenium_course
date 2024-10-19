package tests.github.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExampleApiTest {

    @Test
    public void getUserInfo(){
        RestAssured.baseURI = "https://api.github.com/";

        /*
        1. given
        2. when
        3. then
         */
        Response response = RestAssured
                .given()
                .when()
                .get("/users/octocat")
                .then()
                .extract()
                .response();
        Assert.assertEquals(response.statusCode(),200);
    }
}
