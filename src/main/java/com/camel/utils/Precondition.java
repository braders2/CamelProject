package com.camel.utils;

public class Precondition {

    public static boolean isInteger(String value) {
        return value.matches("^-?\\d+$");
    }

}
