package com.tarkalabs.twittersample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tarkalabs.twittersample.R;
import com.tarkalabs.twittersample.utils.PreferencesManager;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_button)
    TwitterLoginButton login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        login_button.setCallback(mTwitterLoginCallback);
    }

    private Callback<TwitterSession> mTwitterLoginCallback = new Callback<TwitterSession>() {
        @Override
        public void success(Result<TwitterSession> result) {
            TwitterCore.getInstance().getApiClient(result.data).getAccountService().verifyCredentials(true,false,true).enqueue(new Callback<User>() {
                @Override
                public void success(Result<User> result) {
                    PreferencesManager.getInstance().setUserScreenName(result.data.screenName);

                    // Do something with result, which provides a TwitterSession for making API calls
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();

                }

                @Override
                public void failure(TwitterException exception) {

                }
            });

        }

        @Override
        public void failure(TwitterException exception) {
            // Do something on failure
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        login_button.onActivityResult(requestCode, resultCode, data);
    }
}
