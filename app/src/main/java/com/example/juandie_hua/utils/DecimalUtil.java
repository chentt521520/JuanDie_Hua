package com.example.juandie_hua.utils;

public class DecimalUtil {

    public static double string2Double(String str) {
        return Double.parseDouble(str);
    }

    public static float string2Float(String str) {
        return Float.parseFloat(str);
    }

    public static int string2Int(String str) {
        double value = Double.parseDouble(str);
        return (int) value;
    }

    public static String priceAddDecimal(String number) {
        String value = number;
        if (!number.contains("￥")) {
            value = "￥" + value;
        }
        if (!number.contains(".")) {
            value = value + ".00";
        }
        return value;
    }
}
