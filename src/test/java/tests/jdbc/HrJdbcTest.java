package tests.jdbc;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.sql.*;

public class HrJdbcTest {

    @Test
    public void checkTableExists(){
        ConfigReader config = new ConfigReader();
        String jdbcUrl= config.getProperty("jdbcURL")+"testDB";
        String username = config.getProperty("jdbcUsername");
        String password = config.getProperty("jdbcPassword");
        try{
            Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
            Statement statement = connection.createStatement();
            String checkTableExistsQuery="SELECT TABLE_NAME FROM information_schema.TABLES "+
                    "WHERE TABLE_SCHEMA = 'hr' AND TABLE_NAME = 'employees'";
            ResultSet resultSet = statement.executeQuery(checkTableExistsQuery);
            if(resultSet.next()){
                String tableName = resultSet.getString("TABLE_NAME");
                Assert.assertEquals(tableName,"employees","Table adi 'employees' olmalidir.");
            }else{
                Assert.fail("Table 'employees' movcud deyil");
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println("Error creating table" + e.getMessage());
        }
    }
}
