package com.planyourexchange.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * Copyright (C) 2015, Thiago Pagonha,
 * Plan Your Exchange, easy exchange to fit your budget
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
public class PropertyReader {

    private final Properties properties = new Properties();

    private static final String SECRET_PROPERTIES = "secret.properties";
    private static final String SECRET_PASSWORD = "secret_password.properties";

    private final SensitiveDataUtils sensitiveDataUtils;

    public PropertyReader(Context context) throws IOException, GeneralSecurityException {
        final Properties secretPasswordProperties = new Properties();
        loadProperties(context,SECRET_PASSWORD,secretPasswordProperties);

        sensitiveDataUtils = new SensitiveDataUtils(secretPasswordProperties.getProperty("PASSWORD"),
                secretPasswordProperties.getProperty("SALT"));

        loadProperties(context, SECRET_PROPERTIES, properties);
    }

    private void loadProperties(Context context, String file,Properties properties) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(file);
        properties.load(inputStream);
    }

    public String getProperty(String property) throws UnsupportedEncodingException, GeneralSecurityException {
        String hashedKey = sensitiveDataUtils.hashKey(property);
        String value = properties.getProperty(hashedKey);

        return sensitiveDataUtils.decrypt(value);
    }
}