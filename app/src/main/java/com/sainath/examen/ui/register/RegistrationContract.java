package com.sainath.examen.ui.register;

import android.app.Activity;
import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;

public interface RegistrationContract {

    interface RegistrationView {
        void setRegistrationSuccess(FirebaseUser firebaseUser);
        void setRegistrationFailure(String message);
    }

    interface RegistrationPresenter {
        void onDestroy();
        void requestRegister(Activity activity, String email, String password);
    }

    interface RegisterIntractor {
        void performFirebaseRegistration(Activity activity, String email, String password);

    }

    interface onRegistrationListener {
        void onSuccess(FirebaseUser firebaseUser);
        void onFailure(String message);
    }

    interface RegistrationGView {
        void setfirebaseAuthWithGoogle(GoogleSignInAccount account);

        void setloginFailure(int statusCode, String message);

        void startSignIn();

        void navigateToProfile();

        void showProgress();

        void hideProgress();
    }

    interface RegistrationGPresenter {
        /*void start(@Nullable Bundle extras);
        void destroy();
        void handleGLoginRequest();*/
        void requestGLogin(String email, String displayName, Uri photoUrl);
        void handleGLoginFailure(int statusCode, String message);
    }

    interface RegisterGoogleInteractor {
        void firebaseAuthWithGoogle(GoogleSignInAccount account);
    }

    interface onRegistrationGListener{
        void onSuccess(GoogleSignInAccount account);
        void onFailure(String message);
    }

}
