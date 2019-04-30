package com.sainath.examen.ui.user_account.signin.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.sainath.examen.ui.user_account.signin.register.SignUpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements SignInContract.SignInView, GoogleApiClient.OnConnectionFailedListener {

    @Bind(R.id.signInButton)
    SignInButton signInButton;
    private static final String TAG = SignInActivity.class.getSimpleName();
    @Bind(R.id.etEmail)
    EditText etEmail;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    @Bind(R.id.btLogin)
    Button btLogin;
    @Bind(R.id.tvRegister)
    TextView tvRegister;
    @Bind(R.id.llLinerabtn)
    RelativeLayout llLinerabtn;
    @Bind(R.id.linear_layout)
    LinearLayout Layoutlinear;
    private GoogleApiClient mGoogleApiClient;
    private int SIGN_IN_REQUEST_CODE = 888;
    private SignInPresenter signInPresenter;
    String personName;
    String personEmail;
    String personId;
    Uri personPhoto;
    private User user;
    SharedPrefsHelper sharedPrefsHelper;
    DatabaseReference RootRef;
    String parentDbName = "Create Account";
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        sharedPrefsHelper = new SharedPrefsHelper(this);
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

    //firebase login code
    private void initView() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in...");

    }

    // google + sign in code
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
    void signIn(View view) {
        showProgressBar(true);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN_REQUEST_CODE);

    }

    private void showProgressBar(boolean show) {
     /*   sign_in_progress_bar.setVisibility(show ? View.VISIBLE : View.GONE);
        signInButton.setVisibility(show ? View.GONE : View.VISIBLE);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                signInPresenter.logInWithFirebase(result.getSignInAccount());
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

    @Override
    public void startHomeActivity() {
        Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);
        Toast.makeText(getApplicationContext(), " Sign In Successfully", Toast.LENGTH_SHORT).show();
        startActivity(homeIntent);
        showProgressBar(false);
    }

    @Override
    public void showFirebaseAuthenticationFailedMessage() {
        Toast.makeText(SignInActivity.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();

        showProgressBar(false);
    }


    @OnClick({R.id.btLogin, R.id.tvRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                //TODO: add click handling
                break;
            case R.id.tvRegister:
                sendToRegister();
                break;
        }
    }

    private void sendToRegister() {
        Intent regIntent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(regIntent);
    }
}
