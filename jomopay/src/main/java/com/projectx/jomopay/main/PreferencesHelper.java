package com.projectx.jomopay.main;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hussamhyari on 12/7/15.
 */
public class PreferencesHelper {

    private SharedPreferences prefs;

    private static final String FILE_NAME = "com.example.weather.prefs";

    public static final String KEY_Temp = "TempreturePref";

    public PreferencesHelper(Context context) {
        prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void save(String key, String value) {
        prefs.edit().putString(key, value).commit();
    }

    public String loadString(String key, String defValue) {
        return prefs.getString(key, defValue);
    }

    public void saveInt(String key, int value){
        prefs.edit().putInt(key, value).commit();
    }
    public int loadInt(String key, int defValue) {
        return prefs.getInt(key,defValue);
    }

    public void saveBool(String key, boolean value){
        prefs.edit().putBoolean(key, value).commit();
    }
    public boolean loadBool(String key, boolean defValue) {
        return prefs.getBoolean(key,defValue);
    }

}
