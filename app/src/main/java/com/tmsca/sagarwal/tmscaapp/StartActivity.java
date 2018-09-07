package com.tmsca.sagarwal.tmscaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.tmsca.sagarwal.tmscaapp.user.LoginActivity;
import com.tmsca.sagarwal.tmscaapp.user.RegisterActivity;

public class StartActivity extends AppCompatActivity{
    private Button btnReg, btnLog;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnReg = (Button) findViewById(R.id.start_reg_btn);
        btnLog = (Button) findViewById(R.id.start_log_btn);

        fAuth = FirebaseAuth.getInstance();

        updateUI();

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        if(fAuth.getCurrentUser() != null){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }

    }

    private void register(){
        Intent regIntent = new Intent(StartActivity.this, RegisterActivity.class);
        startActivity(regIntent);
        finish();
    }

    private void login(){
        Intent logIntent = new Intent(StartActivity.this, LoginActivity.class);
        startActivity(logIntent);
        finish();
    }

    private void updateUI(){
        if (fAuth.getCurrentUser() != null){
            Log.i("StartActivity", "fAuth != null");
            Intent startIntent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(startIntent);
            finish();
        } else {

            Log.i("StartActivity", "fAuth == null");
        }
    }
}
