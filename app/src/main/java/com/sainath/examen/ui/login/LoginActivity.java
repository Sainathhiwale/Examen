package com.sainath.examen.ui.login;

import android.app.ProgressDialog;
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

import com.sainath.examen.HomeActivity;
import com.sainath.examen.R;
import com.sainath.examen.app.AppController;
import com.sainath.examen.data.DataManager;
import com.sainath.examen.data.prefs.SharedPrefsHelper;
import com.sainath.examen.ui.register.RegisterationActivity;
import com.sainath.examen.ui.register.RegisterationPresenterImpl;
import com.sainath.examen.utils.AppConstants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView{
    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.btLogin)
    Button btLogin;
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
}
