package com.sainath.examen.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.sainath.examen.utils.AppConstants;

public class AppPreferences {

    // shared preferences keys
    public static final String KEY_USER_NAME = "KEY_USER_NAME";
    public static final String KEY_USER_EMAIL = "KEY_USER_EMAIL";
    public static final String KEY_USER_PHOTO = "KEY_USER_PHOTO";
    public static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String IS_LOG_IN = "IS_LOGGED_IN";


    private static final String PREFERENCES_NAME = "EXAMEN_USER";
    private SharedPreferences mSharedPreferences;

    public AppPreferences(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
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
        mSharedPreferences.edit().putString(KEY_USER_NAME, userName).apply();
    }

    public String getUserName() {
        return mSharedPreferences.getString(KEY_USER_NAME, AppConstants.EMPTY2);
    }
    public void putUserEmail(String userEmail) {
        mSharedPreferences.edit().putString(KEY_USER_EMAIL, userEmail).apply();
    }

    public String getUserEmail() {
        return  mSharedPreferences.getString(KEY_USER_EMAIL, AppConstants.EMPTY2);
    }
    public void putUserPhoto(Uri userPhoto) {
        mSharedPreferences.edit().putString(KEY_USER_PHOTO, String.valueOf(userPhoto)).apply();
    }

    public String getUserPhoto() {
        return  mSharedPreferences.getString(KEY_USER_PHOTO, AppConstants.EMPTY2);
    }

    public void putUserId(String userId) {
        mSharedPreferences.edit().putString(KEY_USER_ID, userId).apply();
    }

    public String getUserId(){
        return mSharedPreferences.getString(KEY_USER_ID, AppConstants.EMPTY2);
    }
    public void setStringPrefs(String key,String value){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getStringPrefs(String key){
        return mSharedPreferences.getString(key,null);
    }

}
