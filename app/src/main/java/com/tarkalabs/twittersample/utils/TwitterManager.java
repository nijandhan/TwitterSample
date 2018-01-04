package com.tarkalabs.twittersample.utils;

import android.content.Context;
import android.util.Log;

import com.tarkalabs.twittersample.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.models.UserBuilder;

/**
 * Created by admin on 1/4/2018.
 * Description :
 */

public class TwitterManager {
    private static final TwitterManager mInstance = new TwitterManager();
    private TwitterSession mSession;
    private TwitterManager(){

    }

    public void initialize(Context context){
        TwitterConfig config = new TwitterConfig.Builder(context)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(context.getResources().getString(R.string.TWITTER_CONSUMER_KEY), context.getResources().getString(R.string.TWITTER_CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }

    public boolean isActiveSession(){
        try {
            mSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
            TwitterAuthToken authToken = mSession.getAuthToken();
            return authToken != null&&!authToken.isExpired();
        }catch (Exception e){
        }
        return false;
    }

    public static TwitterManager getInstance(){
        return mInstance;
    }
}
