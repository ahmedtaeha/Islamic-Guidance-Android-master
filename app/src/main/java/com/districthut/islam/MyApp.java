package com.districthut.islam;

import android.app.Application;

import com.districthut.islam.Utils.AppManager;


import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApp extends Application  {

    private static final String ONESIGNAL_APP_ID = "129044a1-17cd-4cff-8009-7925edfccf14";

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .build();

        Realm.setDefaultConfiguration(config);

        AppManager.init(this);

//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();





    }


}
