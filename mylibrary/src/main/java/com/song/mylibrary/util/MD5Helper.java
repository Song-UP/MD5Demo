package com.song.mylibrary.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/3/30.
 */

public class MD5Helper {


    /**
     * MD5加密字符串
     */

    public static String MD5(String str) {
        String MD5 = "";
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = str.getBytes();
            byte[] digest = md5.digest(bytes);
            for (int i = 0; i < digest.length; i++) {
                //摘要字节数组中各个字节的"十六进制"形式.
                int j = digest[i];
                j = j & 0x000000ff;
                String s1 = Integer.toHexString(j);

                if (s1.length() == 1) {
                    s1 = "0" + s1;
                }
                MD5 += s1;
            }
            return MD5;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }

//
//    public static String MD5(String inStr) {
//        MessageDigest md5 = null;
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            e.printStackTrace();
//            return "";
//        }
//        char[] charArray = inStr.toCharArray();
//        byte[] byteArray = new byte[charArray.length];
//
//        for (int i = 0; i < charArray.length; i++)
//            byteArray[i] = (byte) charArray[i];
//
//        byte[] md5Bytes = md5.digest(byteArray);
//
//        StringBuilder hexValue = new StringBuilder();
//
//        for (int i = 0; i < md5Bytes.length; i++) {
//            int val = ((int) md5Bytes[i]) & 0xff;
//            if (val < 16)
//                hexValue.append("0");
//            hexValue.append(Integer.toHexString(val));
//        }
//
//        return hexValue.toString();
//    }


    /**
     * MD5加密文件
     * @param file
     */
    public static void MD5(File file){
        try {
            md5Stream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     *  md5 流的加密
     * @param is
     * @return
     */
    public static String md5Stream(InputStream is){
        String md5 = "";
        try {
            byte[] bytes = new byte[4096];
            MessageDigest digest = MessageDigest.getInstance("MD5");
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                digest.update(bytes, 0, len);
            }
//            BigInteger bigInt = new BigInteger(1, md5Array);
//            不能使用这个方法， 因为new BigInteger 里 有 this.mag = stripLeadingZeroBytes(magnitude); 这个方法是将首位的0去除，这个会导致加密的结果少位数
            md5 = new BigInteger(1, digest.digest()).toString(); //1代表负数
            while (md5.length() < 32){
                md5 = "0"+md5;    //保证返回的字符串是32位的
            }
            return md5;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return md5;

    }

//    public static String getMD5(File file) throws Exception {
//        String MD5 = "";
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        FileInputStream fis = new FileInputStream(file);
//        byte[] bytes = new byte[1024 * 5];
//        int len = -1;
//        while ((len=fis.read(bytes))!=-1) {
//            //一部分一部分更新
//            md5.update(bytes, 0, len);
//        }
//        byte[] digest = md5.digest();
//        for (int i = 0; i <digest.length; i++) {
//            int n = digest[i] & 0x000000ff;
//            String s = Integer.toHexString(n);
//            MD5 += s;
//        }
//        return MD5;
//    }



}
