package com.tarkalabs.twittersample;

import android.app.Application;

import com.tarkalabs.twittersample.utils.TwitterManager;

/**
 * Created by admin on 1/4/2018.
 * Description :
 */

public class App extends Application {
    private static App mInstance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        TwitterManager.getInstance().initialize(this);
    }

    public static App getInstance(){
        return mInstance;
    }
}
