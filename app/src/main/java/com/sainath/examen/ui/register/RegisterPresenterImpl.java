package com.sainath.examen.ui.register;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public class RegisterPresenterImpl implements RegisterContract.RegisterPresenter, RegisterContract.onRegistrationListener {
    private RegisterContract.RegisterView registerView;
    private RegisterInteractorImpl registerIntractor;

    public RegisterPresenterImpl(RegisterContract.RegisterView registerView) {
        this.registerView = registerView;
        registerIntractor = new RegisterInteractorImpl(this);
    }

    @Override
    public void register(Activity activity, String email, String password) {

        registerView.showProgress();
        registerIntractor.performFirebaseRegistration(activity, email, password);
    }


    @Override
    public void onSuccess(FirebaseUser firebaseUser) {
        registerView.setRegistrationSuccess(firebaseUser);
        registerView.hideProgress();

    }

    @Override
    public void onFailure(String message) {
        registerView.onRegistrationFailure(message);
        registerView.hideProgress();
    }

}
