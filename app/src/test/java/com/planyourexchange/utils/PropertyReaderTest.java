package com.planyourexchange.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.planyourexchange.BuildConfig;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by thiago on 30/07/15.
 */
// @RunWith(RobolectricGradleTestRunner.class)
// @Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
@RunWith(MockitoJUnitRunner.class)
public class PropertyReaderTest {

    @Mock AssetManager assetManager;
    @Mock Context context;

    @Before
    public void setup() throws IOException {
        when(context.getAssets()).thenReturn(assetManager);
    }

    @Test
    public void testGetSecretData() throws Exception {
        InputStream inputStream = IOUtils.toInputStream("secretData=XYZ");
        when(assetManager.open(any(String.class))).thenReturn(inputStream);
        PropertyReader propertyReader = new PropertyReader(context);

        String secretData = propertyReader.getProperty("secretData");
        assertEquals("XYZ",secretData);
    }

    @Test(expected = IOException.class)
    public void testError() throws Exception {
        when(assetManager.open(any(String.class))).thenThrow(new IOException("No file found"));
        PropertyReader propertyReader = new PropertyReader(context);

        propertyReader.getProperty("anything");
    }

}
