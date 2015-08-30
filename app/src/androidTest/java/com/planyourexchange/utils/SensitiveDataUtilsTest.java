/*
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

package com.planyourexchange.utils;

import android.test.AndroidTestCase;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Thiago Pagonha
 * @version 30/08/15.
 */
public class SensitiveDataUtilsTest extends AndroidTestCase {

    SensitiveDataUtils sensitiveDataUtils;

    public void setUp() throws Exception {
        super.setUp();
        sensitiveDataUtils = new SensitiveDataUtils("My Secret Password", "My Secret Salt");
    }

    public void testHashKey() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hash = sensitiveDataUtils.hashKey("AddUnitId");
        assertEquals("a6vGOdFW8SSyU8ijshm7tY+FlH9pFZA2Z43stzKFkAk=",hash);
    }

    public void testEncryptAndDecrypt() throws  Exception {
        String encrypted = sensitiveDataUtils.encrypt("Password");
        String decrypted = sensitiveDataUtils.decrypt(encrypted);

        assertEquals("Password", decrypted);
    }

    public void testDecryptStatic() throws UnsupportedEncodingException, GeneralSecurityException {
        String decrypted = sensitiveDataUtils.decrypt("1oNLQmiADwqpwZqffi9SvA==:3Kkb2prcFsffQ6fgpCPJfY0MLqdze1er+sLHluiS0/c=:qsltPflBq21b4XCmAjmrvA==");
        assertEquals("test",decrypted);
    }

    public void testEncryptGenerate() throws UnsupportedEncodingException, GeneralSecurityException {
        Map<String,String> map = new HashMap<>();

        map.put("AdUnitId","?");
        map.put("TestDeviceId","?");
        map.put("service.url","?");
        map.put("service.userName","?");
        map.put("service.password","?");
        map.put("AnalyticsId", "?");
        map.put("test","test");

        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String,String> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append(" = ")
                    .append(sensitiveDataUtils.hashKey(entry.getKey()))
                    .append("\n")
                    .append(entry.getValue())
                    .append(" = ")
                    .append(sensitiveDataUtils.encrypt(entry.getValue()))
                    .append("\n");
        }

        fail(stringBuilder.toString());
    }
}
