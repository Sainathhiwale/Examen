package com.sainath.examen.ui.login;

import android.app.Activity;



public class LoginPresenterImpl implements LoginContract.LoginPresenter,LoginContract.onLoginFinishedListener {

    private LoginContract.LoginView loginView;
    private LoginContract.LoginIntractor mLoginIntractor;

    public LoginPresenterImpl(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        mLoginIntractor = new LoginInteractorImpl(this);
    }

    @Override
    public void login(Activity activity, String email, String password) {
        mLoginIntractor.performFirebaseLogin(activity,email,password);

    }

    @Override
    public void onSuccess(String message) {
      loginView.onLoginSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        loginView.onLoginFailure(message);
    }
}
