package com.urlshortener.util;

public class Base62Encoder {

    private static final String BASE62 =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // convert number to base62 string
    public static String encode(long value) {

        StringBuilder shortUrl = new StringBuilder();

        while(value > 0) {
            shortUrl.append(BASE62.charAt((int)(value % 62)));
            value = value / 62;
        }

        return shortUrl.reverse().toString();
    }
}