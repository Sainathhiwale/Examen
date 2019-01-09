package com.sainath.examen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sainath.examen.app.AppController;
import com.sainath.examen.data.DataManager;
import com.sainath.examen.data.prefs.SharedPrefsHelper;
import com.sainath.examen.ui.android_tut.AndroidTutFragment;
import com.sainath.examen.ui.feedback.FeedBackFragment;
import com.sainath.examen.ui.home.HomeFragment;
import com.sainath.examen.ui.java_tut.JavaTutFragment;
import com.sainath.examen.ui.login.LoginActivity;
import com.sainath.examen.ui.python_tut.PythonTutFragment;
import com.sainath.examen.ui.react_tut.ReactTutFragment;
import com.sainath.examen.ui.register.RegisterationActivity;
import com.sainath.examen.utils.AppConstants;

import java.io.FileNotFoundException;

import butterknife.Bind;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView ivNavHeader;
    private TextView tvUserName,tvUserEmail;
    private DataManager dataManager;
    private SharedPrefsHelper sharedPrefsHelper;
    private View navHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataManager = ((AppController) getApplication()).getDataManager();
        dataManager.setLoggedIn();
        String getUserName = getIntent().getStringExtra(AppConstants.USERNAME);
        dataManager.setUserName(getUserName);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Default Fragment
        HomeFragment homefragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction homeFragmentTransaction
                = getSupportFragmentManager().beginTransaction();
        homeFragmentTransaction.replace(R.id.main_container,homefragment);
        homeFragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationMenuView navMenuView = (NavigationMenuView)navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);
        sharedPrefsHelper = new SharedPrefsHelper(this);
        initView();
        showPrefsToViews();

    }

    private void initView() {
        ivNavHeader = (ImageView)navHeader.findViewById(R.id.ivNavHeader);
        tvUserName = (TextView)navHeader.findViewById(R.id.tvUserName);
        tvUserEmail = (TextView)navHeader.findViewById(R.id.tvUserEmail);
    }

    private void showPrefsToViews() {
        if (sharedPrefsHelper!=null) {
            tvUserName.setText(sharedPrefsHelper.getStringPrefs(SharedPrefsHelper.USER_NAME));
            tvUserEmail.setText(sharedPrefsHelper.getStringPrefs(SharedPrefsHelper.USER_EMAIL));
            String url = sharedPrefsHelper.getStringPrefs(SharedPrefsHelper.USER_IMAGEPROFILE);
            Glide.with(getApplicationContext())
                    .load(url)
                    .into(ivNavHeader);
        }else {
                tvUserEmail.setText(sharedPrefsHelper.getStringPrefs(SharedPrefsHelper.USERNAME));
                String url = sharedPrefsHelper.getStringPrefs(SharedPrefsHelper.USER_IMAGEPROFILE);
                Glide.with(getApplicationContext())
                        .load(url)
                        .into(ivNavHeader);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_androidtut) {
            AndroidTutFragment androidTutFragment = new AndroidTutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,androidTutFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_pythontut) {
            PythonTutFragment pythonTutFragment = new PythonTutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,pythonTutFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_reacttut) {
            ReactTutFragment reactTutFragment = new ReactTutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,reactTutFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_javatut) {
            JavaTutFragment javaTutFragment = new JavaTutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,javaTutFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_feedback) {
            FeedBackFragment feedBackFragment = new FeedBackFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,feedBackFragment);
            fragmentTransaction.commit();
        }else if (id == R.id.nav_logout){
            dataManager.clear();
            Intent intent = new Intent(HomeActivity.this, RegisterationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
