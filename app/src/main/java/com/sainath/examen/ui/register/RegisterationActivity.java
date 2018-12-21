package com.sainath.examen.ui.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.sainath.examen.HomeActivity;
import com.sainath.examen.R;
import com.sainath.examen.ui.login.LoginActivity;

public class RegisterationActivity extends AppCompatActivity implements RegistrationContract.RegistrationView, View.OnClickListener {
    private EditText etUserName, etPassword;
    private Button btnRegister;
    private RegisterationPresenterImpl presenter;
    private ProgressDialog mPrgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        initView();
    }

    private void initView() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        presenter = new RegisterationPresenterImpl(this);
        mPrgressDialog = new ProgressDialog(this);
        mPrgressDialog.setMessage("please wait Registering.....");
    }

    @Override
    public void setRegistrationSuccess(FirebaseUser firebaseUser) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void setRegistrationFailure(String message) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                checkRegistrationDetails();
                break;


        }
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
