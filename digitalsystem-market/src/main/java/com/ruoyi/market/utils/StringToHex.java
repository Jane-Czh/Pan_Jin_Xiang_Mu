package com.ruoyi.market.utils;

public class StringToHex {

    public static String stringToHexString(String s) {

        if (s.equals("")){
            return "";
        }
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            //大写的String字母   toUpperCase();
            String s4 = Integer.toHexString(ch).toUpperCase();

            str = str + s4;
        }
        return str;
    }
}
