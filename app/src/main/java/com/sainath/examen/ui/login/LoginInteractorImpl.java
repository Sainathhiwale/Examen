package com.sainath.examen.ui.login;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginInteractorImpl implements LoginContract.LoginIntractor {
     private LoginContract.onLoginFinishedListener loginFinishedListener;

    public LoginInteractorImpl(LoginContract.onLoginFinishedListener loginFinishedListener) {
        this.loginFinishedListener = loginFinishedListener;
    }

    @Override
    public void performFirebaseLogin(Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            loginFinishedListener.onSuccess(task.getResult().toString());
                        }else {
                            loginFinishedListener.onFailure(task.getException().toString());
                        }
                    }
                });
    }
}
