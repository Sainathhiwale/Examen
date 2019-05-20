package com.sainath.examen.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sainath.examen.HomeActivity;
import com.sainath.examen.R;
import com.sainath.examen.data.model.user.User;
import com.sainath.examen.data.prefs.SharedPrefsHelper;
import com.sainath.examen.utils.CommonUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity  implements LoginInContract.LoginInView, GoogleApiClient.OnConnectionFailedListener {
    @Bind(R.id.signInButton)
    SignInButton signInButton;
    private static final String TAG = LoginActivity.class.getSimpleName();
    @Bind(R.id.linear_layout)
    LinearLayout Layoutlinear;
    private GoogleApiClient mGoogleApiClient;
    private int SIGN_IN_REQUEST_CODE = 888;
    private SharedPrefsHelper sharedPrefsHelper;
    private DatabaseReference RootRef;
    private User user;
    private LoginInPresenter loginInPresenter;
    String parentDbName = "Create Account";
    private ProgressDialog mProgressDialog;

    String personName;
    String personEmail;
    String personId;
    Uri personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPrefsHelper = new SharedPrefsHelper(this);
        RootRef = FirebaseDatabase.getInstance().getReference();
        user = new User();
        loginInPresenter = new LoginInPresenter(this);

        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    @Override
    protected void onStart() {
        super.onStart();
        loginInPresenter.setAuthListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginInPresenter.removeAuthListener();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                loginInPresenter.logInWithFirebase(result.getSignInAccount());
                handleSignInResult(result);
            }
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            personName = acct.getDisplayName();
            personEmail = acct.getEmail();
            personId = acct.getId();
            personPhoto = acct.getPhotoUrl();
            sharedPrefsHelper.putUserName(personName);
            sharedPrefsHelper.putUserEmail(personEmail);
            sharedPrefsHelper.putUserPhoto(personPhoto);
            sharedPrefsHelper.putUserId(personId);
            sharedPrefsHelper.getLoggedInMode();
            RootRef.child("Users").child(personId);
            RootRef.child("Users").push();
            user = new User(personId, personEmail, personName);
            RootRef.child("Users").child(personId)
                    .setValue(user);
        }
    }

    @OnClick(R.id.signInButton)
    public void onSignInButtonClicked() {
        //TODO: add click handling
        CommonUtils.startProgressBarDialog(this,"Sign In...Please Wait");
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN_REQUEST_CODE);
    }

    @Override
    public void showProgress() {
        CommonUtils.startProgressBarDialog(this,"Sign In......");
    }

    @Override
    public void hideProgress() {
        CommonUtils.stopProgressBarDialog();
    }

    @Override
    public void startHomeActivity() {
        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
      //  Toast.makeText(getApplicationContext(), " Sign In Successfully", Toast.LENGTH_SHORT).show();
        startActivity(homeIntent);
    }

    @Override
    public void showFirebaseAuthenticationFailedMessage() {
        Toast.makeText(LoginActivity.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
