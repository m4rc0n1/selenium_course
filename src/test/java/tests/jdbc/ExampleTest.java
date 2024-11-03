package tests.jdbc;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

import java.sql.*;

public class ExampleTest {
    @Test
    public void testConnection(){
        ConfigReader config = new ConfigReader();
        String jdbcUrl= config.getProperty("jdbcURL")+"testDB";
        String username = config.getProperty("jdbcUsername");
        String password = config.getProperty("jdbcPassword");
        //Crete new table
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("+
                "id INT AUTO_INCREMENT PRIMARY KEY,"+
                "name VARCHAR(100) NOT NULL," +
                "email VARCHAR(100) NOT NULL,"+
                "age INT"+
                ")";
        try{
            Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
            Statement statement = connection.createStatement();
            statement.execute(createTableQuery);
            System.out.println("Table 'users' ugurla yaradildi ve ya artiq movcuddur");
            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println("Error creating table" + e.getMessage());
        }
    }

    @Test
    public void insertDataPreparedStatement(){
        ConfigReader config = new ConfigReader();
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        String jdbcUrl= config.getProperty("jdbcURL")+"testDB";
        String username = config.getProperty("jdbcUsername");
        String password = config.getProperty("jdbcPassword");
        String insertDataQuery = "INSERT INTO users(name,email,age) VALUES (?,?,?)";
        String selectQuery = "SELECT * FROM users WHERE email = ?";
        String userName = faker.name().username();
        String userEmail = faker.internet().emailAddress();
        String userAge = "28";
        try{
            Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,userEmail);
            preparedStatement.setString(3,userAge);

            int rowsInserted = preparedStatement.executeUpdate();
            Assert.assertEquals(rowsInserted,1,"User should be inserted successfully");

            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1,userEmail);

            ResultSet resultSet = selectStatement.executeQuery();

            Assert.assertTrue(resultSet.next());

            String retrievedName = resultSet.getString("name");
            String retrievedEmail = resultSet.getString("email");
            String retrievedAge = resultSet.getString("age");

            softAssert.assertEquals(retrievedEmail,userEmail,"Emailler uygun olmalidir");
            System.out.println("Assert 1 finished");
            softAssert.assertEquals(retrievedName,userName +"hj","Usernameler uygun olmalidir");
            System.out.println("Assert 2 finished");
            softAssert.assertEquals(retrievedAge,userAge,"Yashlar uygun olmalidir");
            System.out.println("Assert 3 finished");
            softAssert.assertAll();
            resultSet.close();
            selectStatement.close();
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println("Error during inserting data " + e.getMessage());
        }
    }
}
