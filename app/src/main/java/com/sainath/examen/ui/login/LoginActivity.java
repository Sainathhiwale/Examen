package com.sainath.examen.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.sainath.examen.HomeActivity;
import com.sainath.examen.R;
import com.sainath.examen.app.AppController;
import com.sainath.examen.data.DataManager;
import com.sainath.examen.data.prefs.SharedPrefsHelper;
import com.sainath.examen.ui.login.google_login.GoogleSignIn;
import com.sainath.examen.ui.login.google_login.GoogleSignInPresenter;
import com.sainath.examen.ui.login.google_login.LoginViews;
import com.sainath.examen.ui.login.google_login.UserInfo;
import com.sainath.examen.ui.register.RegisterationActivity;
import com.sainath.examen.ui.register.RegisterationPresenterImpl;
import com.sainath.examen.utils.AppConstants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView ,LoginViews {
    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.btLogin)
    Button btLogin;
    private SignInButton signInButton;
    private GoogleSignInPresenter signInGooglePresenter;
    private UserInfo userModelSingelton;
    private LoginPresenterImpl presenter;
    private ProgressDialog mProgressDialog;
    private DataManager dataManager;
    private String getUserName;
    private SharedPrefsHelper sharedPrefsHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        userModelSingelton = UserInfo.getInstance();
        userModelSingelton.readCash(this);
        switch (userModelSingelton.getLogin_status()){
            case AppConstants.LOGIN_IN:
                startProfileActivity();
                break;
            case AppConstants.IN_GALLERY:
                goToGallery ();
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sharedPrefsHelper = new SharedPrefsHelper(this);
        dataManager = ((AppController) getApplication()).getDataManager();
        if (dataManager.getLoggedInMode()){
            getUserName = dataManager.getUserName();
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            intent.putExtra(AppConstants.USERNAME,getUserName);
            startActivity(intent);
            finish();
        }else {
            presenter = new LoginPresenterImpl(this);
        }
        initView();

    }

    private void initView() {
        presenter = new LoginPresenterImpl(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in...");
    }

    @OnClick(R.id.btLogin)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                checkLoginDetails();
                break;


        }
    }

    private void checkLoginDetails() {
        if (!TextUtils.isEmpty(etUserName.getText().toString())&&!TextUtils.isEmpty(etPassword.getText().toString())){
            initLogin(etUserName.getText().toString().trim(),etPassword.getText().toString().trim());
        }else {
            if (TextUtils.isEmpty(etUserName.getText().toString().trim())){
                etUserName.setError("please enter user name");
            }if (TextUtils.isEmpty(etPassword.getText().toString().trim())){
                etPassword.setError("please enter password");
            }
        }
    }

    private void initLogin(String email, String password) {
        presenter.requestLogin(this,email,password);
        Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }


    @Override
    public void onSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(),"Successfully Logged In",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

// google login

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        signInGooglePresenter.onActivityResult(LoginActivity.this, requestCode, resultCode, data);
    }

    @Override
    public void specifyGoogleSignIn(GoogleSignInOptions gso) {
        signInButton.setScopes(gso.getScopeArray());
    }

    @Override
    public void startProfileActivity() {
        Intent goToProfile = new Intent(LoginActivity.this, HomeActivity.class);
               /* if (tokenToFaceBook != null)
                 goToGallery.putExtra("token_face_book", tokenToFaceBook);*/
        startActivity(goToProfile);
        finish();
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void goToGallery() {

    }

    @Override
    public void showToast(String mssg) {

    }

    @Override
    public void callFromVKSignIn(int requestCode, int resultCode, Intent data) {

    }
    @OnClick(R.id.signInButton)
    public void onViewClick(View view){
        signInGooglePresenter.signIn(LoginActivity.this);
        signInGooglePresenter = new GoogleSignIn(this);
        signInGooglePresenter.createGoogleClient(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signInGooglePresenter.onDestroy();
    }
}
