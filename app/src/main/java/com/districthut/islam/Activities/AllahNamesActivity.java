package com.districthut.islam.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.districthut.islam.Adapter.NamesAdapter;
import com.districthut.islam.Models.NamesModel;
import com.districthut.islam.Utils.AppManager;
import com.mirfatif.noorulhuda.R;
import com.districthut.islam.Utils.DatabaseHelper;
import com.mirfatif.noorulhuda.databinding.ActivityAllahNamesBinding;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllahNamesActivity extends AppCompatActivity {

    ActivityAllahNamesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AppManager.checkTheme(this);
        super.onCreate(savedInstanceState);
        binding = ActivityAllahNamesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Allah Names");
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


        ArrayList<NamesModel> kalimas = databaseHelper.getNames();
//        ArrayList<NamesModel> kalimas = new ArrayList<>();
//        kalimas.add(new NamesModel("اَلرَّحْمٰنُ","Ar-Rahmaan","The one who continously showers all of creation with blessings and prosperity without any disparity. The One who is most kind, loving and merciful.",""));
//        kalimas.add(new NamesModel("اَلرَّحِیْمُ","Ar-Raheem","The one who continously showers all of creation with blessings and prosperity without any disparity. The One who is most kind, loving and merciful.",""));
//        kalimas.add(new NamesModel("اَلْمَلِکُ","Al-Maalik","The one who continously showers all of creation with blessings and prosperity without any disparity. The One who is most kind, loving and merciful.",""));
//        kalimas.add(new NamesModel("اَلْقُدُّوْسُُ","Al-Kudousu","The one who continously showers all of creation with blessings and prosperity without any disparity. The One who is most kind, loving and merciful.",""));
//        kalimas.add(new NamesModel("اَلرَّحْمٰنُ","Ar-Rahmaan","The one who continously showers all of creation with blessings and prosperity without any disparity. The One who is most kind, loving and merciful.",""));
//        kalimas.add(new NamesModel("اَلرَّحْمٰنُ","Ar-Rahmaan","The one who continously showers all of creation with blessings and prosperity without any disparity. The One who is most kind, loving and merciful.",""));
        //RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        binding.recycler.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        NamesAdapter adapter = new NamesAdapter(kalimas,this);
        adapter.setHasStableIds(true);
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
