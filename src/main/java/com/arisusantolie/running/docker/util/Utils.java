package com.arisusantolie.running.docker.util;

import java.util.Random;

public class Utils {

    public static String generateRandomName(){
        // Set the length of the random string
        int length = 6;

        // Create a random object
        Random random = new Random();

        // Generate a random string of alphabets
        String randomString = "";
        for (int i = 0; i < length; i++) {
            char c = (char)(random.nextInt(26) + 'a');
            randomString += c;
        }

        // Print the random string
        return randomString;
    }
}
