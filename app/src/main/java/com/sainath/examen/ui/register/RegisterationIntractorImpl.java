package com.sainath.examen.ui.register;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterationIntractorImpl implements RegistrationContract.RegisterIntractor {
    private static final String TAG = RegistrationContract.class.getSimpleName();
    private RegistrationContract.onRegistrationListener onRegistrationListener;

    public RegisterationIntractorImpl(RegistrationContract.onRegistrationListener onRegistrationListener) {
        this.onRegistrationListener = onRegistrationListener;
    }

    @Override
    public void performFirebaseRegistration(Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            onRegistrationListener.onSuccess(task.getResult().getUser());
                        } else {
                            onRegistrationListener.onFailure(task.getException().getMessage());
                        }
                    }
                });

    }
}
