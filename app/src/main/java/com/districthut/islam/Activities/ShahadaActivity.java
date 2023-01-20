package com.districthut.islam.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.districthut.islam.Utils.AppManager;
import com.mirfatif.noorulhuda.R;
import com.mirfatif.noorulhuda.databinding.ActivityShahadaBinding;

public class ShahadaActivity extends AppCompatActivity {
    ActivityShahadaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AppManager.checkTheme(this);


        super.onCreate(savedInstanceState);
        binding = ActivityShahadaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}