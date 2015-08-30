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

import android.util.Base64;

import com.tozny.crypto.android.AesCbcWithIntegrity;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Thiago Pagonha
 * @version 30/08/15.
 */
public class SensitiveDataUtils {

    private final AesCbcWithIntegrity.SecretKeys keys;

    public SensitiveDataUtils(String password, String salt) throws GeneralSecurityException {
        keys =  AesCbcWithIntegrity.generateKeyFromPassword(password,salt.getBytes());
    }

    public String encrypt(final String key) throws UnsupportedEncodingException, GeneralSecurityException {
        return AesCbcWithIntegrity.encrypt(key, keys).toString();
    }

    public String decrypt(final String key) throws UnsupportedEncodingException, GeneralSecurityException {
        AesCbcWithIntegrity.CipherTextIvMac cipherTextIvMac = new AesCbcWithIntegrity.CipherTextIvMac(key);
        return AesCbcWithIntegrity.decryptString(cipherTextIvMac, keys);
    }

    public String hashKey(final String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = key.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);

        return Base64.encodeToString(digest.digest(),AesCbcWithIntegrity.BASE64_FLAGS);
    }
}
