package com.sainath.examen.data;

import android.content.SharedPreferences;

import com.sainath.examen.data.prefs.SharedPrefsHelper;

public class DataManager {
    private SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(SharedPrefsHelper mSharedPrefsHelper) {
        this.mSharedPrefsHelper = mSharedPrefsHelper;
    }
    public void clear(){
        mSharedPrefsHelper.clear();
    }

    public void setUserName(String userName){
        mSharedPrefsHelper.putUserName(userName);
    }
    public String getUserName(){
        return mSharedPrefsHelper.getUserName();
    }
    public void setDisplayName (String displayName){
        mSharedPrefsHelper.putDisplayName(displayName);
    }
    public String getDisplayName(){
        return mSharedPrefsHelper.getDisplayName();
    }
    public void setLoggedIn(){
        mSharedPrefsHelper.setLoggedInMode(true);
    }
    public boolean getLoggedInMode(){
        return mSharedPrefsHelper.getLoggedInMode();
    }
}
