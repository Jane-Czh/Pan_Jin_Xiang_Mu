package com.heli.safety.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Object2Byte {

    public static byte[] Long2Byte(long object){
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(0, object);
        return buffer.array();
    }


    public static byte[] Int2Byte(int object){
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(0, object);
        return buffer.array();
    }

    public static byte[] File2Byte(File file){
        byte[] data = null;

        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            data = baos.toByteArray();

            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


    public static byte[] String2Byte(String str){
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return bytes;
    }
    // 对象转字节数组
    public static byte[] Object2Byte(Object obj) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        return bytes;
    }
    // 字节数组转为对象
    public static Object Byte2Object(byte[] bytes) throws Exception {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        final Object object = objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return object;
    }

    //字节数组转为16进制字符串
    public static String bytesToHex(byte[] bytes) {
//        StringBuffer sb = new StringBuffer();
//        for(int i = 0; i < bytes.length; i++) {
//            String hex = Integer.toHexString(bytes[i] & 0xFF);
//            if(hex.length() < 2){
//                sb.append(0);
//            }
//            sb.append(hex);
//        }
//        return sb.toString();

        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }

    //16进制转为字节
    public static byte hexToByte(String inHex){
        return (byte)Integer.parseInt(inHex,16);
    }

    //16进制转为字节数组
    public static byte[] hexToByteArray(String inHex){
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1){
            //奇数
            hexlen++;
            result = new byte[(hexlen/2)];
            inHex="0"+inHex;
        }else {
            //偶数
            result = new byte[(hexlen/2)];
        }
        int j=0;
        for (int i = 0; i < hexlen; i+=2){
            result[j]=hexToByte(inHex.substring(i,i+2));
            j++;
        }
        return result;
    }


}