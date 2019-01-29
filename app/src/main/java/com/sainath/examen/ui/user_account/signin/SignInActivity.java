package com.sainath.examen.ui.user_account.signin;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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
import com.sainath.examen.data.model.user.UserInfo;
import com.sainath.examen.data.prefs.AppPreferences;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements SignInContract.SignInView, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = SignInActivity.class.getSimpleName();

    private GoogleApiClient mGoogleApiClient;

    private int SIGN_IN_REQUEST_CODE = 888;
    private SignInPresenter signInPresenter;
    private UserInfo userModelSingleton;

    @Bind(R.id.sign_in_progress_bar)
    ProgressBar sign_in_progress_bar;
    @Bind(R.id.signInButton)
    SignInButton signInButton;
    String personName;
    String personEmail;
    String personId;
    Uri personPhoto;
    private User user;
    AppPreferences appPreferences;
    DatabaseReference RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        appPreferences = new AppPreferences(this);
        RootRef = FirebaseDatabase.getInstance().getReference();
        user = new User();
        signInPresenter = new SignInPresenter(this);
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
        signInPresenter.setAuthListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        signInPresenter.removeAuthListener();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "onConntectionFailed " + connectionResult.getErrorMessage());
    }

    @OnClick(R.id.signInButton)
    void signIn(View view){
        showProgressBar(true);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN_REQUEST_CODE);
    }
    private void showProgressBar(boolean show) {
        sign_in_progress_bar.setVisibility(show ? View.VISIBLE : View.GONE);
        signInButton.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                signInPresenter.logInWithFirebase(result.getSignInAccount());
                handleSignInResult(result);
            }
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()){

            GoogleSignInAccount acct = result.getSignInAccount();
             personName = acct.getDisplayName();
             personEmail = acct.getEmail();
             personId = acct.getId();
             personPhoto = acct.getPhotoUrl();
             appPreferences.putUserName(personName);
             appPreferences.putUserEmail(personEmail);
             appPreferences.putUserPhoto(personPhoto);
             appPreferences.putUserId(personId);
            String id = RootRef.push().getKey();
            if (id!=null){
                User users = new User(id,personEmail,personName);
                RootRef.child("Users").setValue(users);
            }
        }
    }

    @Override
    public void startHomeActivity() {
        Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);
        startActivity(homeIntent);
        showProgressBar(false);
    }

    @Override
    public void showFirebaseAuthenticationFailedMessage() {
        Toast.makeText(SignInActivity.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();

        showProgressBar(false);
    }
}
