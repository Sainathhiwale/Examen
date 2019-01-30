package com.sainath.examen.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.sainath.examen.R;
import com.sainath.examen.ui.user_account.signin.SignInActivity;


import butterknife.Bind;
import butterknife.ButterKnife;
//
public class SplashActivity extends AppCompatActivity implements SplashContract.SplashView {
    @Bind(R.id.ivLogo)
    ImageView ivLogo;
    private SplashPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        presenter = new SplashPresenterImpl(this);
        presenter.decideNextActivity();
    }



    @Override
    public void openLoginActivity() {
        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(() -> {
            Intent homeIntent = new Intent(SplashActivity.this, SignInActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(homeIntent);
        }, 1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null)
            presenter.onDestroy();
    }
}
