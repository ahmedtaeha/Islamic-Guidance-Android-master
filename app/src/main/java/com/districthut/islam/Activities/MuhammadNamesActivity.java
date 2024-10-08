package com.districthut.islam.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.districthut.islam.Adapter.NamesAdapter;
import com.districthut.islam.Models.NamesModel;
import com.districthut.islam.Utils.AppManager;
import com.mirfatif.noorulhuda.R;
import com.districthut.islam.Utils.DatabaseHelper;
import com.mirfatif.noorulhuda.databinding.ActivityMuhammadNamesBinding;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MuhammadNamesActivity extends AppCompatActivity {

    ActivityMuhammadNamesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppManager.checkTheme(this);
        super.onCreate(savedInstanceState);
        binding = ActivityMuhammadNamesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Muhammad Names");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<NamesModel> kalimas = databaseHelper.getMuhammadNames();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        NamesAdapter adapter = new NamesAdapter(kalimas,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}