package com.districthut.islam.Activities.Hajj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;

import com.districthut.islam.Utils.AppManager;
import com.mirfatif.noorulhuda.R;
import com.mirfatif.noorulhuda.databinding.ActivityHajjDetailBinding;

public class HajjDetailActivity extends AppCompatActivity {

    ActivityHajjDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AppManager.checkTheme(this);
        super.onCreate(savedInstanceState);
        binding = ActivityHajjDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        String title = getIntent().getStringExtra("title");
        String detail = getIntent().getStringExtra("detail");
        String image = getIntent().getStringExtra("image");

        getSupportActionBar().setTitle(title);

        binding.web.loadData(detail, "text/html; charset=utf-8", "UTF-8");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}