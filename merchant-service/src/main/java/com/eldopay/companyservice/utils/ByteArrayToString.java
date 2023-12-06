package com.eldopay.companyservice.utils;

import java.util.Base64;

public class ByteArrayToString {

//    public static void main(String[] args) {
//        // Assuming you have a byte array named encryptedByteArray
//        byte[] encryptedByteArray = { /* Your encrypted byte array here */ };
//
//        // Encode the byte array to a Base64 encoded string
//        String base64EncodedString = encodeToBase64(encryptedByteArray);
//
//        // Display the result
//        System.out.println("Base64 Encoded String: " + base64EncodedString);
//    }

    public static String encodeToBase64(byte[] byteArray) {
        // Use the static encodeToString method of the Base64 class
        return Base64.getEncoder().encodeToString(byteArray);
    }
}