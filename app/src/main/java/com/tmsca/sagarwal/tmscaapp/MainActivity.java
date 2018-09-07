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
import com.tmsca.sagarwal.tmscaapp.Fragments.OtherFragment;
import com.tmsca.sagarwal.tmscaapp.Fragments.HomeFragment;
import com.tmsca.sagarwal.tmscaapp.Fragments.MathFragment;
import com.tmsca.sagarwal.tmscaapp.Fragments.ScienceFragment;

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
// ..

        fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();







    }


    public void setActionBarTitle(String title){
        getActionBar().setTitle(title);
    }





    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
                showProgressDialog();
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("AUTH", "Google sign in failed", e);
                // ...
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("AUTH", "signInResult:failed code=" + e.getStatusCode());
        }
    }
    // firebaseNumberWithGoogle function ( When the dude clicks the button initiate the sign in stuff)
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("AUTH", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("AUTH", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println(user.getUid() + "Hi Shobhit");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("AUTH", "signInWithCredential:failure", task.getException());

                        }

                        // ...
                    }
                });
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
        }else if (id == R.id.notes){
            Intent i = new Intent(this, NotesActivity.class);
            startActivity(i);
        }else if(id == R.id.about){
            fm.beginTransaction().replace(R.id.content_frame, new OtherFragment()).commit();

        }else if(id == R.id.home){
            fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();
    }

    private void showProgressDialog(){
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Logging in, please wait...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }
    private void hideProgressDialog(){
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.hide();
        }
    }



}
