package com.districthut.islam.Activities.Quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.districthut.islam.Activities.Quran.adapter.QuranSearchAdapter;
import com.districthut.islam.Activities.Quran.model.QuranSearchModel;
import com.districthut.islam.Adapter.SearchAdapter;
import com.districthut.islam.Models.SearchModel;
import com.mirfatif.noorulhuda.databinding.ActivityQuranSearchBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuranSearchActivity extends AppCompatActivity {

    ActivityQuranSearchBinding binding;
    String query = null;
    ArrayList<QuranSearchModel> quranSearchModels;
    int totalPages = 0;
    int currentPage = 1;
    QuranSearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuranSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        query = getIntent().getStringExtra("query");

        binding.queryTitle.setText(query);

        setSupportActionBar(binding.topToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        quranSearchModels = new ArrayList<>();


        adapter = new QuranSearchAdapter(this, quranSearchModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.showShimmer();

        searchInQuran(query, currentPage);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == quranSearchModels.size() - 1){
                    //bottom of list!
                    currentPage++;
                    searchInQuran(query,currentPage);
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    void searchInQuran(String query, int page) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("https://api.quran.com/api/v4/search?size=20&page=%d&language=en&q=%s",page, query);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);
                        binding.recyclerView.hideShimmer();

                        if(object.has("search")) {
                            JSONObject searchObject = object.getJSONObject("search");
                            binding.totalResults.setText(String.format("%d results found.", searchObject.getInt("total_results")));
                            totalPages = searchObject.getInt("total_pages");

                            if (searchObject.has("results")) {
                                JSONArray results = searchObject.getJSONArray("results");

                                for (int i = 0; i < results.length(); i++) {
                                    JSONObject resultObj = results.getJSONObject(i);
                                    QuranSearchModel model = new QuranSearchModel(
                                            resultObj.getString("verse_key"),
                                            resultObj.getString("text"),
                                            resultObj.getString("highlighted"),
                                            resultObj.getInt("verse_id"),
                                            resultObj.getJSONArray("translations")
                                    );
                                    quranSearchModels.add(model);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }
                    } catch (JSONException error) {
                        Log.e("err123", error.getLocalizedMessage() + "");
                    } finally {

                    }
                },
                error -> {
                    Log.e("err123434", error.getMessage() + "");
                });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(stringRequest);
    }
}