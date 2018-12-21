package com.sainath.examen.ui.splash;

public interface SplashContract {

    interface SplashPresenter{
        void onDestroy();
        void decideNextActivity();
    }

    interface SplashView {
        void openLoginActivity();
    }

}
