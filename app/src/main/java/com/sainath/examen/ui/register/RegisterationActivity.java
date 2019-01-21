package com.sainath.examen.ui.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sainath.examen.HomeActivity;
import com.sainath.examen.R;
import com.sainath.examen.app.AppController;
import com.sainath.examen.data.DataManager;
import com.sainath.examen.data.model.user.User;
import com.sainath.examen.data.prefs.SharedPrefsHelper;
import com.sainath.examen.utils.AppConstants;
import com.sainath.examen.utils.NetworkUtils;
import com.sainath.examen.utils.Validation;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterationActivity extends AppCompatActivity implements RegistrationContract.RegistrationView, GoogleApiClient.OnConnectionFailedListener {
    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.etPassword)
    EditText etPassword;
    //private EditText etUserName, etPassword;
    @Bind(R.id.btnRegister)
    Button btnRegister;
    @Bind(R.id.scrollview)
    ScrollView scrollview;
    private RegisterationPresenterImpl presenter;
    private ProgressDialog mPrgressDialog;
    private DataManager dataManager;
    private String getUserName;

    private SharedPrefsHelper sharedPrefsHelper;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private Bundle extras;
    private static final int RC_SIGN_IN = 1;
    String name, email;
    String idToken;
    private Uri userImgProfile;
    private static final String TAG = "RegisterationActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //our database reference object
    private DatabaseReference databaseReference;
    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        ButterKnife.bind(this);

        //getting the reference of artists node
        databaseReference = FirebaseDatabase.getInstance().getReference("USER");
        sharedPrefsHelper = new SharedPrefsHelper(this);

        dataManager = ((AppController) getApplication()).getDataManager();
        if (dataManager.getLoggedInMode()) {
            getUserName = dataManager.getUserName();
            Intent intent = new Intent(RegisterationActivity.this, HomeActivity.class);
            intent.putExtra(AppConstants.USERNAME, getUserName);
            startActivity(intent);
            finish();
        } else {
            presenter = new RegisterationPresenterImpl(this);
        }
        firebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        //this is where we start the Auth state Listener to listen for whether the user is signed in or not
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Get signedIn user
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //if user is signed in, we call a helper method to save the user details to Firebase
                if (user != null) {
                    // UserProfile is signed in
                    // you could place other firebase code
                    //logic to save the user details to Firebase
                    String id = databaseReference.push().getKey();
                    if (id!=null){
                        User users = new User(id,user.getEmail(),user.getDisplayName());
                        databaseReference.child(id).setValue(users);
                        Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    }


                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        initView();
        // initializing the listeners
        initListeners();

    }

    private void initListeners() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);
            }
        });
    }

    private void initView() {
        signInButton = findViewById(R.id.signInButton);
        presenter = new RegisterationPresenterImpl(this);
        mPrgressDialog = new ProgressDialog(this);
        mPrgressDialog.setMessage("please wait Registering.....");
    }

    @Override
    public void setRegistrationSuccess(FirebaseUser firebaseUser) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra(AppConstants.USERNAME, firebaseUser.getEmail());
        intent.putExtra(AppConstants.DISPLAYNAME,firebaseUser.getDisplayName());
        //getting a unique id using push().getKey() method
        //it will create a unique id and we will use it as the Primary Key for our User
        String id = databaseReference.push().getKey();
        User user = new User(id,etUserName.getText().toString(),firebaseUser.getDisplayName());
        //Saving the Artist
        databaseReference.child(id).setValue(user);

        startActivity(intent);
        finish();

    }

    @Override
    public void setRegistrationFailure(String message) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.btnRegister)
    public void onClick() {
        if (checkValidation()) {
            if (NetworkUtils.isNetworkAvailable(RegisterationActivity.this)) {
                checkRegistrationDetails();
            } else {
                Snackbar snackbar = Snackbar
                        .make(scrollview, "No internet connection!", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();
            }


        }
    }

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(etUserName)) ret = false;
        if (!Validation.hasText(etPassword)) ret = false;
        return ret;
    }

    private void checkRegistrationDetails() {
        if (!TextUtils.isEmpty(etUserName.getText().toString()) && !TextUtils.isEmpty(etPassword.getText().toString())) {
            initRegister(etUserName.getText().toString().trim(), etPassword.getText().toString().trim());

        } else {
            if (TextUtils.isEmpty(etUserName.getText().toString())) {
                etUserName.setError("please enter the user name");
            }
            if (TextUtils.isEmpty(etPassword.getText().toString())) {
                etPassword.setError("please enter the password");
            }
        }

    }

    private void initRegister(String email, String password) {
        mPrgressDialog.dismiss();
        presenter.requestRegister(this, email, password);


    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            idToken = account.getIdToken();
            name = account.getDisplayName();
            email = account.getEmail();
            userImgProfile = account.getPhotoUrl();
            // you can store user data to SharedPreference
            sharedPrefsHelper.setStringPrefs(SharedPrefsHelper.USERID_TOKEN, idToken);
            sharedPrefsHelper.setStringPrefs(SharedPrefsHelper.USER_NAME, name);
            sharedPrefsHelper.setStringPrefs(SharedPrefsHelper.USER_EMAIL, email);
            sharedPrefsHelper.setStringPrefs(SharedPrefsHelper.USER_IMAGEPROFILE, String.valueOf(userImgProfile));
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            firebaseAuthWithGoogle(credential);
        }
    }

    private void firebaseAuthWithGoogle(AuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterationActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            gotoProfile();
                        } else {
                            Log.w(TAG, "signInWithCredential" + task.getException().getMessage());
                            task.getException().printStackTrace();
                            Toast.makeText(RegisterationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void gotoProfile() {
        Intent intent = new Intent(RegisterationActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authStateListener != null) {
            FirebaseAuth.getInstance().signOut();
        }
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}

/*Add the following code in MainActivity.java class. The functionality of classes and methods used in MainActivity.java class are given below:

The new FirebaseAuth.AuthStateListener(){} is used as an authentication listener to check whether the user is sign-in or not. If the users successfully sign-in we place other firebase logic.
The new GoogleSignInOptions.Builder() method configure the Google Sign-in to get the user data using the requestEmail option.
Clicking on Sign-In button, it calls Auth.GoogleSignInApi.getSignInIntent(googleApiClient) and starting the intent with startActivityForResult().
The onActivityResult() we get the result of Google Sign-in request.
If handleSignInResult(result) returns true, we get the Google Authentication using idToken.
Calling firebaseAuthWithGoogle(credential) for making Firebase Authentication with Google.*/