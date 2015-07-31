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

import java.io.InputStream;

/**
 * Created by thiago on 30/07/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
public class PropertyReaderTest {

    PropertyReader propertyReader;

    @Before
    public void setup() throws Exception {

        Context context = mock(Context.class);
        AssetManager assetManager = mock(AssetManager.class);
        InputStream inputStream = IOUtils.toInputStream("secretData=XYZ");

        when(context.getAssets()).thenReturn(assetManager);
        when(assetManager.open(any(String.class))).thenReturn(inputStream);

        propertyReader = new PropertyReader(context);
    }

    @Test
    public void testGetSecretData() {
        String secretData = propertyReader.getProperty("secretData");
        assertEquals("XYZ",secretData);
    }


}
