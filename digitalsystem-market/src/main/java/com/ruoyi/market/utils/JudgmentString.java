package com.ruoyi.market.utils;

import java.util.regex.Pattern;

public class JudgmentString {
    // 判断字符串是否包含中文字符
    public static boolean containsChinese(String str) {
        // 中文字符的正则表达式范围：[\u4e00-\u9fa5]
        String regex = ".*[\\u4e00-\\u9fa5].*";
        return Pattern.matches(regex, str);
    }

    // 判断字符串是否为空
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
