package com.sainath.examen.ui.login;

import android.app.Activity;
import android.net.Uri;

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

    interface LoginGPresenter{
        void handleGLoginRequest();
        void requestGLogin(Activity activity,String email, String displayName, Uri photoUrl);
        void handleGLoginFailure(int statusCode, String message);
    }

   /* interface LoginGInteactor{
        void performGoogleLogin(Activity activity,String email, String displayName, Uri photoUrl);
    }*/
}
