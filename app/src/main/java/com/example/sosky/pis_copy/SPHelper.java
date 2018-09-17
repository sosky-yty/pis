package com.example.sosky.pis_copy;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SPHelper {
    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    public SPHelper(Context context, String key_name) {
        sharedPreferences = context.getSharedPreferences(key_name, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void put(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    public Object getSharedpreference(String key, Object dafaultObject) {
        if (dafaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) dafaultObject);
        } else if (dafaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) dafaultObject);
        } else if (dafaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) dafaultObject);
        } else if (dafaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) dafaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 某个key是否纯在
     *
     * @param key
     * @return
     */
    public Boolean contain(String key) {
        return sharedPreferences.contains(key);
    }

    public Map<String, ?> getAlL() {
        return sharedPreferences.getAll();
    }
}
