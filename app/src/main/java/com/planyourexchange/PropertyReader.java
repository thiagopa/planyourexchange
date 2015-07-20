package com.planyourexchange;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Thiago Pagonha
 * @version Jul/2015
 * Simple config loader
 */
public class PropertyReader {

    private Properties properties;

    private static final String SECRET_PROPERTIES = "secret.properties";

    public PropertyReader(Context context) {
        properties = new Properties();
        loadProperties(context, SECRET_PROPERTIES);
    }

    private void loadProperties(Context context, String file) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(file);
            properties.load(inputStream);

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

    public String getProperty(String property) {
        return properties.getProperty(property);
    }
}