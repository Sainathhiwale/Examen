package com.sainath.examen.ui.login;

import android.app.Activity;

public interface LoginContract {
    interface LoginView {
        void onSuccess(String message);
        void onFailure(String message);
    }

    interface LoginPresenter {
        void requestLogin(Activity activity, String email, String password);
    }

    interface LoginInteactor {
        void performFirebaseLogin(Activity activity, String email, String password);

    }

    interface onLoginListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
