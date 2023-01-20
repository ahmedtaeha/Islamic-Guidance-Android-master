package com.districthut.islam.Activities;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.mirfatif.noorulhuda.R;
import com.districthut.islam.Utils.AppManager;
import com.mirfatif.noorulhuda.databinding.ActivitySplashBinding;
import com.mirfatif.noorulhuda.db.DbBuilder;
import com.mirfatif.noorulhuda.ui.base.BaseActivity;
import com.mirfatif.noorulhuda.ui.dialog.AlertDialogFragment;
import com.mirfatif.noorulhuda.util.Utils;

public class SplashActivity extends BaseActivity {

    private static int SPLASH_TIME_OUT = 0;
    ActivitySplashBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

//        startSplashTimer();
            startSplashTimer();
//        if(AppManager.getValue("readingDataStatus").equals("OK")) {
//            startSplashTimer();
//        } else {
//            binding.firstStart.setVisibility(View.VISIBLE);
//            buildDbAndRefreshUi();
//        }

    }

    private void buildDbAndRefreshUi() {
        AlertDialogFragment dialog = showDbBuildDialog();
        //Utils.showThirdPartyCredits(this, false);
        Utils.runBg(
                () -> {
                    if (DbBuilder.buildDb(DbBuilder.MAIN_DB)) {
                        startSplashTimer();
                        AppManager.saveValue("readingDataStatus", "OK");
                    }
                    Utils.runUi(this, dialog::dismissIt);
                });
    }

    private AlertDialogFragment showDbBuildDialog() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).setTitle(R.string.creating_database).setView(R.layout.dialog_progress);
        AlertDialogFragment dialog = AlertDialogFragment.show(this, builder.create(), "BUILD_DATABASE");
        dialog.setCancelable(false);
        return dialog;
    }

    void startSplashTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = null;
                i = new Intent(SplashActivity.this, HomeActivity.class);
//                if(SharedPreferencesManager.getValue("selectedLanguage").equals("")) {
//                    i = new Intent(SplashActivity.this, LanguageSelectionActivity.class);
//                } else {
//                    i = new Intent(SplashActivity.this, MainActivity.class);
//                }
                startActivity(i);
                finishAffinity();
            }
        }, SPLASH_TIME_OUT);
    }
}
