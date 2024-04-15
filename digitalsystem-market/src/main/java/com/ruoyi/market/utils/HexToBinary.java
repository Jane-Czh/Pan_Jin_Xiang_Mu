package com.ruoyi.market.utils;

public class HexToBinary {
//    public static void main(String[] args) {
//        String hexInput = "08"; // 你的十六进制输入字符串
//
//        // 调用方法进行转换
//        String binaryOutput = hexToBinary(hexInput);
//
//        // 打印结果
//        System.out.println("十六进制输入: " + hexInput);
//        System.out.println("二进制输出: " + binaryOutput);
//    }

    // 将十六进制字符串转换为二进制字符串的方法
    public static String hexToBinary(String hexInput) {
        // 使用BigInteger将十六进制字符串转换为十进制
        String decimalValue = new java.math.BigInteger(hexInput, 16).toString(2);

        // 使用printf将结果格式化为四位数的字符串
        return String.format("%04d", Integer.parseInt(decimalValue));
    }
}
