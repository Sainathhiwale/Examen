package com.sainath.examen.ui.register;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;
import com.sainath.examen.data.DataHandler;

public class RegisterationPresenterImpl implements RegistrationContract.RegistrationPresenter, RegistrationContract.onRegistrationListener {
    private RegistrationContract.RegistrationView registrationView;
    private RegisterationIntractorImpl registerIntractor;


    private DataHandler mDataHandler;

     RegisterationPresenterImpl(RegistrationContract.RegistrationView registrationView) {
        this.registrationView = registrationView;
        this.registerIntractor = new RegisterationIntractorImpl(this);
    }


    @Override
    public void onDestroy() {
        registrationView = null;
    }

    @Override
    public void requestRegister(Activity activity, String email, String password) {
        if (registrationView != null) {
            registerIntractor.performFirebaseRegistration(activity, email, password);
        }
    }

    @Override
    public void onSuccess(FirebaseUser firebaseUser) {
        registrationView.setRegistrationSuccess(firebaseUser);
    }



    @Override
    public void onFailure(String message) {
        registrationView.setRegistrationFailure(message);
    }


}
