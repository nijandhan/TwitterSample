package com.tarkalabs.twittersample.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tarkalabs.twittersample.R;
import com.tarkalabs.twittersample.utils.TwitterManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        openScreenBasedOnTwitterSession();
    }

    private void openScreenBasedOnTwitterSession(){
        if(TwitterManager.getInstance().isActiveSession())
            launchHomeScreen();
        else launchLoginScreen();
    }

    private void launchLoginScreen(){
        Intent loginIntent = new Intent(this,LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private void launchHomeScreen(){
        Intent loginIntent = new Intent(this,HomeActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
