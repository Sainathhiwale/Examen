package com.sainath.examen.ui.login;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginIntractorImpl implements LoginContract.LoginInteactor {
    private LoginContract.onLoginListener onLoginListener;
    private static final String TAG = LoginContract.class.getSimpleName();

    public LoginIntractorImpl(LoginContract.onLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
    }

    @Override
    public void performFirebaseLogin(Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            onLoginListener.onSuccess(task.getResult().toString());
                        } else {
                            onLoginListener.onFailure(Objects.requireNonNull(task.getException()).toString());
                        }
                    }
                });
    }
}
