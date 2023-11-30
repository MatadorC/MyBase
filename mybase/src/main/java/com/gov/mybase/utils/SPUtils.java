package com.gov.mybase.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * <pre>
 *     bookMark: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/08/02
 *     desc  : SP相关工具类
 * </pre>
 */
public class SPUtils {

    private static WeakHashMap<String, SPUtils> sSPMap = new WeakHashMap<>();
    private SharedPreferences sp;

    /**
     * 获取SP实例
     *
     * @return {@link SPUtils}
     */
    public static SPUtils getInstance() {
        return getInstance("");
    }

    /**
     * 获取SP实例
     *
     * @param spName sp名
     * @return {@link SPUtils}
     */
    public static SPUtils getInstance(String spName) {
        if (isSpace(spName)) spName = "govPrint";
        WeakReference<SPUtils> sp = new WeakReference<>(sSPMap.get(spName));
        if (sp.get() == null) {
            sp = new WeakReference<>(new SPUtils(spName));
            sSPMap.put(spName, sp.get());
        }
        return sp.get();
    }

    private SPUtils(String spName) {
        sp = AppUtils.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    /**
     * SP中写入String
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull String key, @NonNull String value) {
        sp.edit().putString(key, value).apply();
    }

    /**
     * SP中读取String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code ""}
     */
    public String getString(@NonNull String key) {
        String string = "";
        try {
            string = getString(key, "");
        } catch (Exception e) {
            remove(key);
            LogUtils.e("get String | " + key + " | error " + e);
        }
        return string;
    }

    /**
     * SP中读取String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public String getString(@NonNull String key, @NonNull String defaultValue) {
        try {
            defaultValue = sp.getString(key, defaultValue);
        } catch (Exception e) {
            remove(key);
            LogUtils.e("get String | " + key + " | error " + e);
        }
        return defaultValue;
    }

    /**
     * SP中写入int
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    /**
     * SP中读取int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public int getInt(@NonNull String key) {
        int anInt = -1;
        try {
            anInt = getInt(key, -1);
        } catch (Exception e) {
            remove(key);
            LogUtils.e("get Int | " + key + " | error " + e);
        }
        return anInt;
    }

    /**
     * SP中读取int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public int getInt(@NonNull String key, int defaultValue) {
        try {
            defaultValue = sp.getInt(key, defaultValue);
        } catch (Exception e) {
            remove(key);
            LogUtils.e("get Int | " + key + " | error " + e);
        }
        return defaultValue;
    }

    /**
     * SP中写入long
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    /**
     * SP中读取long
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public long getLong(@NonNull String key) {
        long aLong = -1L;
        try {
            aLong = getLong(key, -1L);
        } catch (Exception e) {
            remove(key);
            LogUtils.e("get Long | " + key + " | error " + e);
        }
        return aLong;
    }

    /**
     * SP中读取long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public long getLong(@NonNull String key, long defaultValue) {
        try {
            defaultValue = sp.getLong(key, defaultValue);
        } catch (Exception e) {
            remove(key);
            LogUtils.e("get Long | " + key + " | error " + e);
        }
        return defaultValue;
    }

    /**
     * SP中写入float
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    /**
     * SP中读取float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public float getFloat(@NonNull String key) {
        float aFloat = -1f;
        try {
            aFloat = getFloat(key, -1f);
        } catch (Exception e) {
            remove(key);
            LogUtils.e("get Float | " + key + " | error " + e);
        }
        return aFloat;
    }

    /**
     * SP中读取float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public float getFloat(@NonNull String key, float defaultValue) {
        try {
            defaultValue = sp.getFloat(key, defaultValue);
        } catch (Exception e) {
            remove(key);
            LogUtils.e("get Float | " + key + " | error " + e);
        }
        return defaultValue;
    }

    /**
     * SP中写入boolean
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * SP中读取boolean
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public boolean getBoolean(@NonNull String key) {
        boolean aBoolean = false;
        try {
            aBoolean = getBoolean(key, false);
        } catch (Exception e) {
            remove(key);
            LogUtils.e("get Boolean | " + key + " | error " + e);
        }
        return aBoolean;
    }

    /**
     * SP中读取boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        try {
            defaultValue = sp.getBoolean(key, defaultValue);
        } catch (Exception e) {
            remove(key);
            LogUtils.e("get Boolean  | " + key + " | error " + e);
        }
        return defaultValue;
    }

    /**
     * SP中写入String集合
     *
     * @param key    键
     * @param values 值
     */
    public void put(@NonNull String key, @NonNull Set<String> values) {
        sp.edit().putStringSet(key, values).apply();
    }

    /**
     * SP中读取StringSet
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code Collections.<String>emptySet()}
     */
    public Set<String> getStringSet(@NonNull String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * SP中读取StringSet
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public Set<String> getStringSet(@NonNull String key, @NonNull Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }

    /**
     * SP中获取所有键值对
     *
     * @return Map对象
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * SP中是否存在该key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean contains(@NonNull String key) {
        return sp.contains(key);
    }

    /**
     * SP中移除该key
     *
     * @param key 键
     */
    public void remove(@NonNull String key) {
        sp.edit().remove(key).apply();
    }

    /**
     * SP中清除所有数据
     */
    public void clear() {
        sp.edit().clear().apply();
    }

    private static boolean isSpace(String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}