package com.tmsca.sagarwal.tmscaapp;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tmsca.sagarwal.tmscaapp.Fragments.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Button button;
    ImageView imageView;
    private static final int RC_SIGN_IN = 0;
    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private ProgressDialog mProgressDialog;
    FragmentManager fm;
    private InterstitialAd mInterstitialAd;

    // onCreate method (Does exactly what it sounds like in a programmer mindset)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Firebase Setup
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        String uid = mAuth.getCurrentUser().getUid();
        myRef = mFirebaseDatabase.getReference().child("Users").child(uid);

        //Navigation Drawer stuff
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        Score scoreClass = new Score();

        fm = getSupportFragmentManager();

        //AdMob stuff
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd = new InterstitialAd(MainActivity.this);
        //ca-app-pub-1595886826370726/4903130403
        mInterstitialAd.setAdUnitId("ca-app-pub-1595886826370726/4903130403");
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                displayInterstitalAd();
            }
        });



// ..

        fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();


    }

    private void displayInterstitalAd() {
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }


    public void setActionBarTitle(String title) {
        getActionBar().setTitle(title);
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }






    public void signOut() {
        FirebaseAuth.getInstance().signOut();
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



    //Navigation Item Selected


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // This is where we assign functions for the navigation items.
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fm2 = getSupportFragmentManager().beginTransaction();
        if (id == R.id.signout) {
            signOut();
            Intent i = new Intent(this, StartActivity.class);
            startActivity(i);
        } else if (id == R.id.science) {

            fm.beginTransaction().replace(R.id.content_frame, new ScienceFragment()).commit();

        } else if (id == R.id.math) {
            fm.beginTransaction().replace(R.id.content_frame, new MathFragment()).commit();
        } else if (id == R.id.notes) {
            Intent i = new Intent(this, NotesActivity.class);
            startActivity(i);
        } else if (id == R.id.other) {
            fm.beginTransaction().replace(R.id.content_frame, new OtherFragment()).commit();

        } else if (id == R.id.home) {
            fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        }else if(id == R.id.resources){
            fm.beginTransaction().replace(R.id.content_frame, new ResourcesFragment()).commit();
        }else if(id == R.id.credits){
            fm.beginTransaction().replace(R.id.content_frame, new CreditsFragment()).commit();
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

