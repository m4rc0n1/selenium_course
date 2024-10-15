package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader(){
        try{
            FileInputStream fileInputStream= new FileInputStream("src/test/resources/configuration.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        }catch(IOException err){
            err.printStackTrace();
        }
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public String getUrl(){
        return properties.getProperty("url");
    }
}
