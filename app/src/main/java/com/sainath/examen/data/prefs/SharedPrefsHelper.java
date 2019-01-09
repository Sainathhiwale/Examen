package com.sainath.examen.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.sainath.examen.utils.AppConstants;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefsHelper {

    private static final String MY_PREFS = "EXAMEN_PREFS";
    private static final String EMAIL = "EMAIL";
    private static final String USERCODE = "USERCODE";
    public static final String USERNAME = "USERNAME";
    private static final String IS_LOG_IN = "IS_LOGGED_IN";
    public static final String USERID_TOKEN ="userIdToken";
    public static final String USER_NAME = "userName";
    public static final String USER_EMAIL ="userEmail";
    public static final String USER_IMAGEPROFILE ="userImageProfile";

    private SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }


    public boolean getLoggedInMode() {
        return mSharedPreferences.getBoolean(IS_LOG_IN, false);
    }

    public void setLoggedInMode(boolean loggedIn) {
        mSharedPreferences.edit().putBoolean(IS_LOG_IN, loggedIn).apply();
    }


    public void putUserName(String userName) {
        mSharedPreferences.edit().putString(USERNAME, userName).apply();
    }

    public String getUserName() {
        return  mSharedPreferences.getString(USERNAME, AppConstants.EMPTY);
    }

    public void setStringPrefs(String key,String value){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public String getStringPrefs(String key){
        return mSharedPreferences.getString(key,null);
    }

    public void setImagePrefs(String key,String uriString){
        SharedPreferences.Editor prefEditor = mSharedPreferences.edit();
        prefEditor.putString("key", uriString);
        prefEditor.apply();
    }
    public String getImagePrefs(String key){
        return mSharedPreferences.getString(key,null);
    }

}
