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
// @RunWith(RobolectricGradleTestRunner.class)
// @Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
@RunWith(MockitoJUnitRunner.class)
public class PropertyReaderTest {

    @Mock AssetManager assetManager;
    @Mock Context context;
    @Mock SensitiveDataUtils sensitiveDataUtils;

    @Before
    public void setup() throws IOException {
        when(context.getAssets()).thenReturn(assetManager);
    }

    @Test
    public void testGetSecretData() throws Exception {
        InputStream inputStream = IOUtils.toInputStream("ASDFG=1QAZ2WSX3EDC");
        when(assetManager.open(any(String.class))).thenReturn(inputStream);
        PropertyReader propertyReader = new PropertyReader(context,sensitiveDataUtils);

        when(sensitiveDataUtils.hashKey("secretData")).thenReturn("ASDFG");
        when(sensitiveDataUtils.decrypt("1QAZ2WSX3EDC")).thenReturn("XYZ");

        String secretData = propertyReader.getProperty("secretData");
        assertEquals("XYZ",secretData);
    }

    @Test(expected = IOException.class)
    public void testError() throws Exception {
        when(assetManager.open(any(String.class))).thenThrow(new IOException("No file found"));
        PropertyReader propertyReader = new PropertyReader(context,sensitiveDataUtils);

        propertyReader.getProperty("anything");
    }

}
