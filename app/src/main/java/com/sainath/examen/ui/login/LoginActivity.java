package com.sainath.examen.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sainath.examen.HomeActivity;
import com.sainath.examen.R;
import com.sainath.examen.ui.register.RegisterActivity;
import com.sainath.examen.utils.NetworkUtils;
import com.sainath.examen.utils.Validation;

import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView{
    private static final String TAG = "LoginActivity";
    @Bind(R.id.tvAppTitle)
    TextView tvAppTitle;
    @Bind(R.id.etUserEmail)
    EditText etUserEmail;
    @Bind(R.id.etUserPassword)
    EditText etUserPassword;
    @Bind(R.id.tvForgotPass)
    TextView tvForgotPass;
    @Bind(R.id.btnLogin)
    Button btnLogin;
    @Bind(R.id.actvRegisterLink)
    AppCompatTextView actvRegisterLink;
    @Bind(R.id.linear_layout)
    LinearLayout linearLayout;
    private ProgressDialog mProgressDialog;
    private LoginPresenterImpl presenter;
    String personName;
    String personEmail;
    String personId;
    Uri personPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        presenter = new LoginPresenterImpl(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in...");
        Objects.requireNonNull(((AppCompatActivity) LoginActivity.this).getSupportActionBar()).setTitle("Sign In");

    }

    @OnClick({R.id.btnLogin, R.id.actvRegisterLink})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (checkValidation()){
                  if (NetworkUtils.isNetworkAvailable(this)){
                      loginUser();
                      /*Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                      intent.putExtra("Name",etUserEmail.getText().toString().trim());
                      startActivity(intent);*/
                  }else {
                      Snackbar snackbar = Snackbar.make(linearLayout,"No internet connection!",Snackbar.LENGTH_SHORT);
                      snackbar.show();
                  }
                }else {
                    Snackbar snackbar = Snackbar.make(linearLayout,"Please enter those field!",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                break;
            case R.id.actvRegisterLink:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void loginUser() {
        presenter.login(this,etUserEmail.getText().toString().trim(),etUserPassword.getText().toString().trim());
    }

    private boolean checkValidation() {
        boolean valid = true;
        if (!Validation.hasText(etUserEmail)) valid = false;
        if (!Validation.hasText(etUserPassword)) valid = false;
        if (!Validation.isEmailAddress(etUserEmail,true)) valid = false;
        return valid;
    }

    @Override
    public void onLoginSuccess(String message) {
        String msg = "success";
        mProgressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Successfully Logged In",Toast.LENGTH_LONG).show();
          if (msg.equals("success")){
              Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
              startActivity(intent);
          }
    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Snackbar snackbar = Snackbar.make(linearLayout,""+message,Snackbar.LENGTH_SHORT);
        snackbar.show();
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}