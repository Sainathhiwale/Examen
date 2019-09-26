package com.sainath.examen.ui.login;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public interface LoginContract {
    interface LoginView{
        void onLoginSuccess( String message);
        void onLoginFailure(String message);

    }
    interface LoginPresenter{
        void login(Activity activity, String email, String password);
    }
    interface  LoginIntractor{
        void performFirebaseLogin(Activity activity,String email,String password);
    }
    interface onLoginFinishedListener{
        void onSuccess(String message);
        void onFailure(String message);
    }

}
