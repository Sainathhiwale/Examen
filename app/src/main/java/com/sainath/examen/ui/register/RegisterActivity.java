package com.sainath.examen.ui.register;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.sainath.examen.R;
import com.sainath.examen.ui.login.LoginActivity;
import com.sainath.examen.utils.AppConstants;
import com.sainath.examen.utils.CommonUtils;
import com.sainath.examen.utils.NetworkUtils;
import com.sainath.examen.utils.Validation;

import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.RegisterView{
    private static final String TAG = "RegisterActivity";
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etEmail)
    EditText etEmail;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.btSignUp)
    Button btSignUp;
    @Bind(R.id.actvSignInLink)
    AppCompatTextView actvSignInLink;
    @Bind(R.id.linear_layout)
    LinearLayout linearLayout;
    private RegisterPresenterImpl presenter;
    private  ProgressDialog mPrgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter = new RegisterPresenterImpl(this);
        initView();
        initData();
    }

    private void initView() {
        Objects.requireNonNull(((AppCompatActivity) RegisterActivity.this).getSupportActionBar()).setTitle("Sign Up");
        mPrgressDialog = new ProgressDialog(this);
        mPrgressDialog.setMessage("Please wait, Adding profile to database.");
    }

    private void initData() {

    }

    @OnClick({R.id.btSignUp, R.id.actvSignInLink})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btSignUp:
                if (checkValidation()){
                     if (NetworkUtils.isNetworkAvailable(RegisterActivity.this)){
                        saveUser();
                     }else {
                         Snackbar snackbar = Snackbar.make(linearLayout,"No internet connection!",Snackbar.LENGTH_SHORT);
                         snackbar.show();
                     }
                }else {
                    Snackbar snackbar = Snackbar.make(linearLayout,"Please enter those field!",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    //Toast.makeText(this, "Please enter those field!", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.actvSignInLink:
                break;
        }
    }

    private void saveUser() {
        mPrgressDialog.show();
        presenter.register(this,etEmail.getText().toString().trim(),etPassword.getText().toString().trim());
    }


    private boolean checkValidation() {
        boolean valid = true;
        if (!Validation.hasText(etName)) valid = false;
        if (!Validation.hasText(etEmail)) valid = false;
        if (!Validation.isEmailAddress(etEmail,true)) valid = false;
        if (!Validation.isValidPassword(etPassword)) valid = false;
        return valid;
    }

    @Override
    public void showProgress() {
       // CommonUtils.startProgressBarDialog(RegisterActivity.this,"Validating please wait...");
    }

    @Override
    public void hideProgress() {
       // CommonUtils.stopProgressBarDialog();
    }

    @Override
    public void setRegistrationSuccess(FirebaseUser firebaseUser) {
        mPrgressDialog.dismiss();
          Toast.makeText(getApplicationContext(), "Successfully Registered" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistrationFailure(String message) {
      Snackbar snackbar = Snackbar.make(linearLayout,"Error"+message,Snackbar.LENGTH_SHORT);
      snackbar.show();
    }
}
