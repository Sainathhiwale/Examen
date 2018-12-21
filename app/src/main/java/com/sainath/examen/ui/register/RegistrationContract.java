package com.sainath.examen.ui.register;

import android.app.Activity;

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

}
