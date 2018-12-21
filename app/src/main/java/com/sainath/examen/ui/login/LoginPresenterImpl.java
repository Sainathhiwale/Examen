package com.sainath.examen.ui.login;

import android.app.Activity;

public class LoginPresenterImpl implements LoginContract.LoginPresenter, LoginContract.onLoginListener {
    private LoginContract.LoginView loginView;
    private LoginIntractorImpl loginIntractor;

    LoginPresenterImpl(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        this.loginIntractor = new LoginIntractorImpl(this);
    }



    @Override
    public void requestLogin(Activity activity, String email, String password) {
        loginIntractor.performFirebaseLogin(activity, email, password);
    }

    @Override
    public void onSuccess(String message) {
        if (loginView != null) {
            loginView.onSuccess(message);
        }
    }

    @Override
    public void onFailure(String message) {
        if (loginView != null) {
            loginView.onFailure(message);
        }
    }
}