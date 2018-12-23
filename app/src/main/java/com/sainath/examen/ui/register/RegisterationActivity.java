package com.sainath.examen.ui.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.sainath.examen.HomeActivity;
import com.sainath.examen.R;
import com.sainath.examen.app.AppController;
import com.sainath.examen.data.DataManager;
import com.sainath.examen.data.prefs.SharedPrefsHelper;
import com.sainath.examen.utils.AppConstants;
import com.sainath.examen.utils.NetworkUtils;
import com.sainath.examen.utils.Validation;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterationActivity extends AppCompatActivity implements RegistrationContract.RegistrationView {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        ButterKnife.bind(this);
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

        initView();
    }

    private void initView() {
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
        //sharedPrefsHelper.putUserName(firebaseUser.getEmail());
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
}
