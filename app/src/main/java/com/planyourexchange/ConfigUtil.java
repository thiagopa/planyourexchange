package com.planyourexchange;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Thiago Pagonha
 * @version Jul/2015
 * Simple config loader
 */
public final class ConfigUtil {

    private static Properties properties;

    private ConfigUtil(){
    }

    public static final String SECRET_PROPERTIES = "src/main/java/secret.properties";

    static {
        InputStream inputStream = null;
        properties = new Properties();
        try {
            inputStream = new FileInputStream(SECRET_PROPERTIES);
            properties.load(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }

}