package com.sainath.examen.ui.login;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class LoginInContract {

    interface LoginInView{
        void showProgress();
        void hideProgress();
        void startHomeActivity();
        void showFirebaseAuthenticationFailedMessage();
    }

    interface LoginPresenter{
        void logInWithFirebase(GoogleSignInAccount account);

    }

}
