package com.sainath.examen.ui.login.google_login;

import android.content.Intent;

import com.sainath.examen.ui.login.LoginActivity;
import com.sainath.examen.ui.register.RegisterationActivity;

public interface GoogleSignInPresenter {
    void createGoogleClient (LoginActivity loginView);
    void onStart();
    void signIn(LoginActivity loginView);
    void onActivityResult (LoginActivity loginView,int requestCode, int resultCode, Intent data);
    void onStop ();
    void onDestroy();
}
