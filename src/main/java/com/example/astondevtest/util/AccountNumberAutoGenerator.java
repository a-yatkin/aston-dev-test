package com.example.astondevtest.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AccountNumberAutoGenerator {

    private static final Random random = new Random();

    public static String generate() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            builder.append(random.nextInt(9));
        }
        return builder.toString();
    }
}
