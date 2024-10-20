package tests.jdbc;

import org.testng.annotations.Test;
import java.sql.*;

public class ExampleTest {

    @Test
    public void testConnection(){
        String jdbcUrl= "jdbc:mysql://127.0.0.1:3306/testDB";
        String username = "root";
        String password = "root";
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
        }catch(SQLException e){
            System.out.println("Error creating table" + e.getMessage());
        }

    }
}
