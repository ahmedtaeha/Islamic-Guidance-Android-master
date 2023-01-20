package com.districthut.islam.Activities.Quran.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.districthut.islam.Activities.Quran.model.QuranSearchModel;
import com.mirfatif.noorulhuda.R;
import com.mirfatif.noorulhuda.databinding.SampleRowSurahSearchBinding;
import com.mirfatif.noorulhuda.databinding.SampleRowSurahSearchTrnaslationBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sadmansamee on 7/19/15.
 */
public class QuranSearchTranslationsAdapter extends RecyclerView.Adapter<QuranSearchTranslationsAdapter.SurahViewHolder> {


    private JSONArray translations;
    private Context context;


    public QuranSearchTranslationsAdapter(Context context, JSONArray translations) {
        this.translations = translations;
        this.context = context;
    }

    @Override
    public SurahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_row_surah_search_trnaslation, parent, false);
        SurahViewHolder viewHolder = new SurahViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SurahViewHolder holder, int position) {

        try {
            JSONObject translation = translations.getJSONObject(position);
            holder.binding.translation.setText(Html.fromHtml(translation.getString("text")));
            holder.binding.translator.setText(translation.getString("name"));
        } catch (Exception ex) { }

        if (position % 2 == 1) {
            holder.binding.rowSurahDetail.setBackgroundColor(ContextCompat.getColor(context, R.color.skin_light));

        } else {
            holder.binding.rowSurahDetail.setBackgroundColor(ContextCompat.getColor(context, R.color.skin_dark));
        }

    }


    @Override
    public long getItemId(int position) {
        //  Surah surah = surahArrayList.get(position);

        //return surahArrayList.get(position).getId();
        return position;
    }

    public Object getItem(int position) {
        try {
            return translations.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return translations.length();
    }




    public class SurahViewHolder extends RecyclerView.ViewHolder
    {
        SampleRowSurahSearchTrnaslationBinding binding;
        public SurahViewHolder(View view) {
            super(view);
            binding = SampleRowSurahSearchTrnaslationBinding.bind(view);
        }
    }

}

