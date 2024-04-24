package com.heli.supply.utils;

public class StringUtils {
    public static String getPrefix(String str) {
        int index = str.indexOf("-");
        return index != -1 ? str.substring(0, index) : str;
    }
}
