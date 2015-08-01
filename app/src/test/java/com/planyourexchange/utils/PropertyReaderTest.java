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
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by thiago on 30/07/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
public class PropertyReaderTest {

    PropertyReader propertyReader;
    AssetManager assetManager;

    @Before
    public void setup() throws IOException {

        Context context = mock(Context.class);
        AssetManager assetManager = mock(AssetManager.class);

        when(context.getAssets()).thenReturn(assetManager);

        propertyReader = new PropertyReader(context);
    }

    @Test
    public void testGetSecretData() throws Exception {
        InputStream inputStream = IOUtils.toInputStream("secretData=XYZ");
        when(assetManager.open(any(String.class))).thenReturn(inputStream);

        String secretData = propertyReader.getProperty("secretData");
        assertEquals("XYZ",secretData);
    }

    @Test(expected = IOException.class)
    public void testError() throws Exception {
        when(assetManager.open(any(String.class))).thenThrow(new IOException("No file found"));

        propertyReader.getProperty("anything");
    }

}
