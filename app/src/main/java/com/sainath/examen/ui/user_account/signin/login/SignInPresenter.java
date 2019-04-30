package com.sainath.examen.ui.user_account.signin.login;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInPresenter implements SignInContract.SignInPresenter{
    private static final String TAG = SignInActivity.class.getSimpleName();

    private  SignInContract.SignInView mView;

    private FirebaseAuth mFirebaseAuth;

    private  Context mContext;

    private FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user!=null){
                Log.d(TAG, "User is Signed In");
                mView.startHomeActivity();
            }else {
                Log.d(TAG, "User is Signed Out");

            }
        }
    };

    public SignInPresenter(Context context) {
        mView = (SignInContract.SignInView) context;
        mContext = context;
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    public void setAuthListener() {
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    public void removeAuthListener() {
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }
    @Override
    public void logInWithFirebase(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            mView.showFirebaseAuthenticationFailedMessage();

                        } else {
                            mView.startHomeActivity();
                        }
                    }
                });
    }
}