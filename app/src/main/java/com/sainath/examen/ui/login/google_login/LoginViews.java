package com.sainath.examen.ui.login.google_login;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public interface LoginViews {
    void specifyGoogleSignIn(GoogleSignInOptions gso);
    void startProfileActivity();
    Context getContext();
    void goToGallery ();
    void showToast(String mssg);
    void callFromVKSignIn(int requestCode, int resultCode, Intent data);

}
