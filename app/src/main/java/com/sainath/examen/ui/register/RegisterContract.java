package com.sainath.examen.ui.register;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public interface RegisterContract {
    interface RegisterView{
        void showProgress();
        void hideProgress();
        void setRegistrationSuccess(FirebaseUser firebaseUser);
        void onRegistrationFailure(String message);
    }
    interface RegisterPresenter{
        void register(Activity activity, String email, String password);
    }
    interface RegisterIntractor{
        void performFirebaseRegistration(Activity activity,String email,String password);
    }

    interface onRegistrationListener{
        void onSuccess(FirebaseUser firebaseUser);
        void onFailure(String message);
    }
}
