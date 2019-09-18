package com.tourney;

public class Print {

    private static final String NO_TAG = "notag";

    public static void print(String tag, String str) {
        System.out.println(tag + " : " + str);
    }

    public static void print(String str) {
        print(NO_TAG, str);
    }
}
