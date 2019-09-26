package com.sainath.examen.ui.register;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterInteractorImpl implements RegisterContract.RegisterIntractor {
    private RegisterContract.onRegistrationListener mOnRegistrationListener;

    public RegisterInteractorImpl(RegisterContract.onRegistrationListener mOnRegistrationListener) {
        this.mOnRegistrationListener = mOnRegistrationListener;
    }

    @Override
    public void performFirebaseRegistration(Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            mOnRegistrationListener.onFailure(task.getException().getMessage());
                        }else {
                            mOnRegistrationListener.onSuccess(task.getResult().getUser());
                        }
                    }
                });
    }
}
