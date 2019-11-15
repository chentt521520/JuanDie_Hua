package com.example.juandie_hua.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;


/**
 * <p>ShardPreference的工具类</p>
 *
 * @author renjy 2018/3/14.
 */
public class SharedPreferenceUtils {
    /**
     * 获取ShardPreference中对应键的值
     *
     * @param context 上下文
     * @param key     键
     * @param type    返回结果类型
     * @return 键对应的值
     */
    public static Object getPreference(Context context, String key, String type) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        if (type.equals("S")) {
            return sharedPref.getString(key, "");
        }
        if (type.equals("F")) {
            return sharedPref.getFloat(key, 0.0F);
        }
        if (type.equals("I")) {
            return sharedPref.getInt(key, 0);
        }
        if (type.equals("L")) {
            return sharedPref.getLong(key, 0L);
        }
        if (type.equals("B")) {
            return sharedPref.getBoolean(key, false);
        }
        return null;
    }

    /**
     * 存储在指定文件
     * @param context 上下文
     * @param diyPrefs 文件名
     * @param key key
     * @param type type
     * @return value
     */
    public static Object getPreference(Context context, String diyPrefs, String key, String type) {
        SharedPreferences settings = context.getSharedPreferences(diyPrefs, 0);
        if (type.equals("S")) {
            return settings.getString(key, "");
        }
        if (type.equals("F")) {
            return settings.getFloat(key, 0.0F);
        }
        if (type.equals("I")) {
            return settings.getInt(key, 0);
        }
        if (type.equals("L")) {
            return settings.getLong(key, 0L);
        }
        if (type.equals("B")) {
            return settings.getBoolean(key, false);
        }
        return null;
    }

    /**
     * 保存到SharedPreference中
     *
     * @param context 上下文
     * @param key     保存的键
     * @param value   保存的值
     * @param type    保存的类型
     */
    public static void setPreference(Context context, String key, Object value, String type) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (type.equals("S")) {
            if (key.equals("SearchTag")) {
                value = searchTag(context, key, (String) value);
            }
            editor.putString(key, (String) value);
        }
        if (type.equals("B")) {
            editor.putBoolean(key, (Boolean) value);
        }
        if (type.equals("L")) {
            editor.putLong(key, (Long) value);
        }
        if (type.equals("I")) {
            editor.putInt(key, (Integer) value);
        }
        editor.apply();
    }

    public static void setPreference(Context context, String diyPrefs, String key, Object value, String type) {
        SharedPreferences settings = context.getSharedPreferences(diyPrefs, 0);
        SharedPreferences.Editor editor = settings.edit();
        if (type.equals("S")) {
            editor.putString(key, (String) value);
        }
        if (type.equals("I")) {
            editor.putInt(key, (Integer) value);
        }
        if (type.equals("B")) {
            editor.putBoolean(key, (Boolean) value);
        }
        if (type.equals("L")) {
            editor.putLong(key, (Long) value);
        }
        editor.apply();
    }

    private static String searchTag(Context context, String key, String value) {
        StringBuilder builder = new StringBuilder();
        String searchTag = (String) getPreference(context, key, "S");
        if (!TextUtils.isEmpty(searchTag) && !searchTag.contains(value)) {
            builder.append(searchTag).append(",");
        }

        if (!TextUtils.isEmpty(value)) {
            builder.append(value);
        }
        return builder.toString();
    }
}
