package com.example.juandie_hua.utils;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {

    public static int getLength(String s) {
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 1;
            } else {
                // 其他字符长度为0.5
                valueLength += 0.5;
            }
        }
        //进位取整
        return (int)Math.ceil(valueLength);
    }

    public static boolean listIsEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断是否为520
     * @return
     */
    public static boolean is520Day() {
        Calendar c = Calendar.getInstance();
        int mouth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int day = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
        int year = c.get(Calendar.YEAR);//
        return mouth == 5 && (10 <= day && day <= 21);
    }

    public static String getopenid() {
        String str = "";
        for (int i = 0; i < 20; i++) {
            str = str + (char) (Math.random() * 26 + 'a');
        }
        return md5(str + String.valueOf(System.currentTimeMillis() / 1000));
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 手机正则
     */

    public static boolean isMatchered(CharSequence input) {
        String PHONE_PATTERN = "^(1)\\d{10}$";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
