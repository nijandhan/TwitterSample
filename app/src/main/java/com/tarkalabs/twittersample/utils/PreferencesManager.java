package com.tarkalabs.twittersample.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.tarkalabs.twittersample.App;

/**
 * Created by admin on 1/5/2018.
 * Description :
 */

public class PreferencesManager {
    private static final String SHARED_PREFERENCES = "shared_preferences";
    private static final String SCREEN_NAME = "shared_preferences";
    private static PreferencesManager mInstance;
    private SharedPreferences mSharedPreferences;

    private PreferencesManager(){
        mSharedPreferences = App.getInstance().getSharedPreferences(
                SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static PreferencesManager getInstance(){
        if(mInstance == null) mInstance = new PreferencesManager();
        return mInstance;
    }

    public void setUserScreenName(String screenName){
        SharedPreferences.Editor e = mSharedPreferences.edit();
        e.putString(SCREEN_NAME, screenName);
        e.commit();
    }

    public String getUserScreenName(){
        return mSharedPreferences.getString(SCREEN_NAME,null);
    }
}
