package com.sainath.examen.ui.user_account.signin.login;

import android.app.Activity;

public class LoginPresenterImpl implements LoginContract.LoginPresenter,LoginContract.onLoginListener {
    private LoginIntractorImpl loginInteactor;
    private LoginContract.LoginView loginView;

    public LoginPresenterImpl( LoginContract.LoginView loginView) {
        this.loginView = loginView;
        this.loginInteactor = new LoginIntractorImpl(this);
    }

    @Override
    public void requestLogin(Activity activity, String email, String password) {
        loginInteactor.performFirebaseLogin(activity,email,password);
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
