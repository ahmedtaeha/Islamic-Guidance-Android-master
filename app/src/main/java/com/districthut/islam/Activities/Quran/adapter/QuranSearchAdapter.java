package com.districthut.islam.Activities.Quran.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.districthut.islam.Activities.Quran.QuranSearchActivity;
import com.districthut.islam.Activities.Quran.model.QuranSearchModel;
import com.mirfatif.noorulhuda.R;
import com.mirfatif.noorulhuda.databinding.QuranSearchTranslationsDialogBinding;
import com.mirfatif.noorulhuda.databinding.SampleRowSurahSearchBinding;
import com.xeoh.android.texthighlighter.TextHighlighter;

import java.util.ArrayList;

/**
 * Created by Sadmansamee on 7/19/15.
 */
public class QuranSearchAdapter extends RecyclerView.Adapter<QuranSearchAdapter.SurahViewHolder> {


    private ArrayList<QuranSearchModel> surahArrayList;
    private Context context;
    boolean SEARCH;
    Typeface arabic_font;

    private static final int DEFAULT_BG_COLOR = Color.YELLOW;
    private static final int DEFAULT_FG_COLOR = Color.RED;

    private TextHighlighter textHighlighter = new TextHighlighter()
            .setBackgroundColor(DEFAULT_BG_COLOR)
            .setForegroundColor(DEFAULT_FG_COLOR);

    private TextHighlighter.Matcher matcher = TextHighlighter.CASE_INSENSITIVE_MATCHER;


    public QuranSearchAdapter(Context context, ArrayList<QuranSearchModel> surahArrayList) {
        this.surahArrayList = surahArrayList;
        this.context = context;
    }

    @Override
    public SurahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_row_surah_search, parent, false);
        SurahViewHolder viewHolder = new SurahViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SurahViewHolder holder, int position) {

        QuranSearchModel quran = surahArrayList.get(position);

        holder.binding.arabicTextViewSurahdetail.setText(quran.getText());

//        textHighlighter.addTarget(holder.binding.arabicTextViewSurahdetail);
//
//        textHighlighter.highlight(query, matcher);



        holder.binding.searchDetail.setText(quran.getVerse_key());

        if(quran.getTranslations() != null) {
            if(quran.getTranslations().length() > 0) {
                holder.binding.translationsFoundLbl.setVisibility(View.VISIBLE);
                holder.binding.translationsFoundLbl.setText(String.format("%d Translations Available", quran.getTranslations().length()));
            } else {
                holder.binding.translationsFoundLbl.setVisibility(View.GONE);
            }
        } else {
            holder.binding.translationsFoundLbl.setVisibility(View.GONE);
        }

        holder.binding.translationsFoundLbl.setOnClickListener(c-> {
            QuranSearchTranslationsDialogBinding searchDialogBinding = QuranSearchTranslationsDialogBinding.inflate(((QuranSearchActivity)context).getLayoutInflater());
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setView(searchDialogBinding.getRoot())
                    .create();
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            QuranSearchTranslationsAdapter adapter = new QuranSearchTranslationsAdapter(context, quran.getTranslations());
            searchDialogBinding.recyclerView.setAdapter(adapter);

            dialog.show();
        });


        if (position % 2 == 1) {
            holder.binding.rowSurahDetail.setBackgroundColor(ContextCompat.getColor(context, R.color.skin_light));

        } else {
            holder.binding.rowSurahDetail.setBackgroundColor(ContextCompat.getColor(context, R.color.skin_dark));
        }
        /*holder.row_surahDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.row_surahDetail);
                //inflating menu from xml resource
                popup.inflate(R.menu.surahdetailmenu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_drawer_item1:
                                //handle menu1 click
                                break;
                            case R.id.navigation_drawer_item2:
                                //handle menu2 click
                                break;
                            case R.id.navigation_drawer_item3:
                                //handle menu3 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });*/
    }

    public Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public Bitmap addTranparencyToBitmap(Bitmap originalBitmap, int alpha) {
        Bitmap newBitmap = Bitmap.createBitmap(originalBitmap.getWidth(), originalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Paint alphaPaint = new Paint();
        alphaPaint.setAlpha(alpha);
        canvas.drawBitmap(originalBitmap, 0, 0, alphaPaint);
        return newBitmap;
    }

    public String convertToArabic(int value)
    {
        String newValue = (((((((((((value+"")
                .replaceAll("1", "١")).replaceAll("2", "٢"))
                .replaceAll("3", "٣")).replaceAll("4", "٤"))
                .replaceAll("5", "٥")).replaceAll("6", "٦"))
                .replaceAll("7", "٧")).replaceAll("8", "٨"))
                .replaceAll("9", "٩")).replaceAll("0", "٠"));
        return newValue;
    }


    @Override
    public long getItemId(int position) {
        //  Surah surah = surahArrayList.get(position);

        //return surahArrayList.get(position).getId();
        return position;
    }

    public Object getItem(int position) {
        return surahArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return surahArrayList.size();
    }




    public class SurahViewHolder extends RecyclerView.ViewHolder
    {
        SampleRowSurahSearchBinding binding;
        public SurahViewHolder(View view) {
            super(view);
            binding = SampleRowSurahSearchBinding.bind(view);
        }
    }

}

