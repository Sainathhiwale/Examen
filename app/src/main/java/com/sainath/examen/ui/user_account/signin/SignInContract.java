package com.sainath.examen.ui.user_account.signin;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface SignInContract {
    interface SignInView{
        void startHomeActivity();
        void showFirebaseAuthenticationFailedMessage();
    }

    interface SignInPresenter{
        void logInWithFirebase(GoogleSignInAccount account);
    }
}
