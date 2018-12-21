package com.sainath.examen.ui.splash;

public class SplashPresenterImpl implements SplashContract.SplashPresenter{
    private SplashContract.SplashView splashView;

    SplashPresenterImpl(SplashContract.SplashView splashView){
        this.splashView = splashView;
    }


    @Override
    public void onDestroy() {
        splashView = null;
    }

    @Override
    public void decideNextActivity() {
        splashView.openLoginActivity();
    }


}
