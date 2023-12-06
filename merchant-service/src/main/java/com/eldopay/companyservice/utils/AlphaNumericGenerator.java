package com.eldopay.companyservice.utils;

import java.security.SecureRandom;

public class AlphaNumericGenerator {
    private static final String ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int STRING_LENGTH = 6;

//    public static void main(String[] args) {
//        String generatedString = generateAlphanumericString();
//        System.out.println("Generated Alphanumeric String: " + generatedString);
//    }

    public static String generateAlphanumericString() {
        StringBuilder stringBuilder = new StringBuilder(STRING_LENGTH);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < STRING_LENGTH; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC_CHARS.length());
            char randomChar = ALPHANUMERIC_CHARS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}
