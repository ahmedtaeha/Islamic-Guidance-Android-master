package com.districthut.islam.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatDelegate;


import com.mirfatif.noorulhuda.R;

/**
 * Created by Devlomi on 01/02/2017.
 */

public class AppManager {

    public static String query = "";

    //this will contains the app preferences
    private static SharedPreferences mSharedPref;
    //this will contains the users settings

    synchronized public static void init(Context context) {
        if (mSharedPref == null)
            mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }



    public static void logOut() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean("isLoggedIn",false);
        editor.putString("user_id","");
        editor.putString("username","");
        editor.putString("name","");
        editor.putString("country","");
        editor.putString("city","");
        editor.putString("email","");
        editor.putString("selectedGender","");
        editor.putString("role","");
        editor.putString("image","");
        editor.apply();
    }



    public static void checkTheme(Activity activity) {
        SharedPreferences defaultPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        boolean isDarkMode =  defaultPreferences.getBoolean("dark_mode",false);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            activity.setTheme(R.style.darkTheme);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            activity.setTheme(R.style.AppTheme);
        }
    }

    public static String getCurrentUserId(){
        return mSharedPref.getString("user_id","");
    }

    public static void saveValue(String key, String value) {
        mSharedPref.edit().putString(key,value).apply();
    }

    public static String getValue(String key) {
        return mSharedPref.getString(key,"");
    }

    public static String getRemainingFormattedTime(long leftTime) {
        long mills = Math.abs(leftTime);
        int Hours = (int) (mills / (1000 * 60 * 60));
        int Mins = (int) (mills / (1000 * 60)) % 60;
        long Secs = (int) (mills / 1000) % 60;
        String diff = "";

        if(Hours > 0) {
            diff = Hours + "hr " + Mins + "min";
        } else {
            diff = Mins + "min";
        }
        return diff;
    }

}
