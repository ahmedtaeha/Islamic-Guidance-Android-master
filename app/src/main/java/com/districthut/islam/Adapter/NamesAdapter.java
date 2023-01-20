package com.districthut.islam.Adapter;

import static com.gpfreetech.neumorphism.Neumorphism.CIRCULAR_SHAPE;

import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.districthut.islam.Models.NamesModel;
import com.gpfreetech.neumorphism.Neumorphism;
import com.mirfatif.noorulhuda.R;
import com.mirfatif.noorulhuda.databinding.RowNamesBinding;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sadmansamee on 7/19/15.
 */
public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.NamesViewHolder> {

    private ArrayList<NamesModel> namesModels;
    private Context context;
    Typeface arabic_font;
    Typeface urdu_font;

    public NamesAdapter(ArrayList<NamesModel> namesModels, Context context) {
        arabic_font = Typeface.createFromAsset(context.getAssets(),  "fonts/PDMS_Saleem_QuranFont-signed.ttf");
        urdu_font = Typeface.createFromAsset(context.getAssets(),  "fonts/JameelNooriNastaleeq.ttf");
        this.namesModels = namesModels;
        this.context = context;
    }

    @Override
    public NamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_names, parent, false);
        NamesViewHolder viewHolder = new NamesViewHolder(view);


        return viewHolder;
    }



    @Override
    public void onBindViewHolder(NamesViewHolder holder, int position) {

        final NamesModel name = namesModels.get(position);




        holder.binding.name.setText(name.getName().trim());
        holder.binding.transliteration.setText(name.getTransliteration().trim());
        holder.binding.engMeaning.setText(name.getEnglish_meaning().trim());
        holder.binding.counterOfAllahNames.setText((position + 1) + ".");


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.expandableLayout.toggle();
            }
        };

        holder.binding.name.setOnClickListener(clickListener);
        holder.binding.transliteration.setOnClickListener(clickListener);
        holder.binding.engMeaning.setOnClickListener(clickListener);
        holder.binding.counterOfAllahNames.setOnClickListener(clickListener);
        holder.itemView.setOnClickListener(clickListener);


        //holder.textView.setText(namesModels.get(position));
        holder.binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = String.format(name.getName() + "\n" + name.getTransliteration() + "\n" + name.getUrdu_meaning() + "\n" + name.getEnglish_meaning());
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
                //context.startActivity(Intent.createChooser(sharingIntent,"Share using"));
            }
        });
    }

    public int getColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(130), rnd.nextInt(70), rnd.nextInt(100));
        return color;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {

        return namesModels.get(position);
    }

    @Override
    public int getItemCount() {
        return namesModels.size();
    }

    public class NamesViewHolder extends RecyclerView.ViewHolder
    {
        RowNamesBinding binding;

        public NamesViewHolder(View view) {
            super(view);
            binding = RowNamesBinding.bind(view);
            binding.name.setTypeface(arabic_font);
            binding.urduMeaning.setTypeface(urdu_font);
        }



    }
}

