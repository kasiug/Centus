package com.kgozdz.centus.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptography {

    public static String getPasswordHash(String password) throws NoSuchAlgorithmException {
        MessageDigest msg = MessageDigest.getInstance("SHA-256");
        byte[] hashArray = msg.digest(password.getBytes(StandardCharsets.UTF_8));

        // convert bytes to hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte hash : hashArray) {
            sb.append(Integer.toString((hash & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
