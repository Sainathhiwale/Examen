package com.sainath.examen.app;

import android.app.Application;

import com.sainath.examen.data.DataManager;
import com.sainath.examen.data.prefs.SharedPrefsHelper;

public class AppController extends Application{
    private DataManager dataManager;
    @Override
    public void onCreate() {
        super.onCreate();

        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
        dataManager = new DataManager(sharedPrefsHelper);

    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
