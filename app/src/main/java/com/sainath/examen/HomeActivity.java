package com.sainath.examen;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.sainath.examen.app.AppController;
import com.sainath.examen.data.prefs.DataManager;
import com.sainath.examen.data.prefs.SharedPrefsHelper;
import com.sainath.examen.ui.tutorial.android_tut.AndroidTutFragment;
import com.sainath.examen.ui.feedback.FeedBackFragment;
import com.sainath.examen.ui.home.HomeFragment;
import com.sainath.examen.ui.tutorial.java_tut.JavaTutFragment;
import com.sainath.examen.ui.tutorial.python_tut.PythonTutFragment;
import com.sainath.examen.ui.tutorial.react_tut.ReactTutFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView ivNavHeader;
    private TextView tvUserName, tvUserEmail;
    private DataManager dataManager;
    private SharedPrefsHelper sharedPrefsHelper;
    private long mBackPressed;
    private static final int TIME_INTERVAL = 3000;
    private View navHeader;
    private String getUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getUserName = getIntent().getStringExtra("name");

       /* dataManager = ((AppController) getApplication()).getDataManager();
        dataManager.setLoggedIn();*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Default Fragment


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);
        sharedPrefsHelper = new SharedPrefsHelper(this);
        initView();



    }


    private void initView() {
        HomeFragment homefragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction homeFragmentTransaction
                = getSupportFragmentManager().beginTransaction();
        homeFragmentTransaction.replace(R.id.main_container, homefragment);
        homeFragmentTransaction.commit();

        ivNavHeader = (ImageView) navHeader.findViewById(R.id.ivNavHeader);
        tvUserName = (TextView) navHeader.findViewById(R.id.tvUserName);
        tvUserEmail = (TextView) navHeader.findViewById(R.id.tvUserEmail);
        tvUserEmail.setText(getUserName);
       /* tvUserName.setText(sharedPrefsHelper.getStringPrefs(SharedPrefsHelper.KEY_USER_NAME));
        tvUserEmail.setText(sharedPrefsHelper.getStringPrefs(SharedPrefsHelper.KEY_USER_EMAIL));
        String photoUrl = sharedPrefsHelper.getStringPrefs(SharedPrefsHelper.KEY_USER_PHOTO);
        Glide.with(getApplicationContext())
                .load(photoUrl)
                .into(ivNavHeader);*/


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                android.support.v7.app.AlertDialog alert = builder.create();
                alert.show();
            } else {
                Toast.makeText(getBaseContext(), "Press again to exit", Toast.LENGTH_SHORT).show();

            }
            mBackPressed = System.currentTimeMillis();
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
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_pythontut) {
            PythonTutFragment pythonTutFragment = new PythonTutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,pythonTutFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_reacttut) {
            ReactTutFragment reactTutFragment = new ReactTutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,reactTutFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_javatut) {
            JavaTutFragment javaTutFragment = new JavaTutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,javaTutFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_feedback) {
            FeedBackFragment feedBackFragment = new FeedBackFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,feedBackFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else if (id == R.id.nav_logout){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
