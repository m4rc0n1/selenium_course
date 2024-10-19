package tests.github.api;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.api.models.RepoModel;
import utils.ConfigReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import static org.hamcrest.Matchers.*;

public class DataDrivenGithubTests {
    ConfigReader config = new ConfigReader();


    @DataProvider(name = "repoDataProvider")
    public Object[][] repoData(){
        return new Object[][]{
                {"test-repo-1","Repo 1 yaradildi",false},
                {"test-repo-2","Repo 2 yaradildi",false}
        };
    }
    @Test(dataProvider = "repoDataProvider")
    public void createNewRepoDataProvider(String repoName,String repoDescription, boolean isRepoPrivate){
        String token = config.getProperty("githubPTA");
        RestAssured.baseURI = config.getProperty("githubApiUrl");
        RepoModel repo = new RepoModel(repoName,repoDescription,isRepoPrivate);
        Gson gson = new Gson();
        String jsonPayload = gson.toJson(repo);

        RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when().post("/user/repos")
                .then()
                .statusCode(201)
                .body("name",equalTo(repoName))
                .body("description",equalTo(repoDescription))
                .body("private",equalTo(isRepoPrivate));
    }

    @Test
    public void createNewRepoFromCSV() throws IOException {
        String token = config.getProperty("githubPTA");
        RestAssured.baseURI = config.getProperty("githubApiUrl");

        FileReader fileReader = new FileReader("files/repo_data.csv");
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());
        Iterator<CSVRecord> csvIterator = csvParser.iterator();

        while(csvIterator.hasNext()){
            CSVRecord record = csvIterator.next();

//            datani cixar
            String repoName = record.get("name");
            String repoDescription = record.get("description");
            boolean isPrivate = Boolean.parseBoolean(record.get("private"));

            RepoModel repo = new RepoModel(repoName,repoDescription,isPrivate);
            Gson gson= new Gson();
            String jsonPayload = gson.toJson(repo);
            RestAssured.given()
                    .auth().oauth2(token)
                    .contentType(ContentType.JSON)
                    .body(jsonPayload)
                    .when().post("/user/repos")
                    .then()
                    .statusCode(201)
                    .body("name",equalTo(repoName))
                    .body("description",equalTo(repoDescription))
                    .body("private",equalTo(isPrivate));
        }
    }
}
