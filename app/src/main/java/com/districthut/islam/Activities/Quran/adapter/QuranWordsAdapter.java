package com.districthut.islam.Activities.Quran.adapter;

import android.content.Context;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.brilliancesoft.tajweedparser.TajweedHelper;
import com.mirfatif.noorulhuda.R;
import com.mirfatif.noorulhuda.databinding.WordSampleLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class QuranWordsAdapter extends RecyclerView.Adapter<QuranWordsAdapter.WordViewHolder> {


    Context context;
    List<String> arrayList;


    public QuranWordsAdapter(Context context, List<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.word_sample_layout, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {

        StringBuilder builder = new StringBuilder();
        builder.append(TajweedHelper.INSTANCE.getStyledAyah(arrayList.get(position)));
        holder.binding.tvTitle.setText(builder);
//        holder.binding.tvTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!SharedPreferencesManager.getValue("role").equals("user") || SharedPreferencesManager.getValue("role").equals("") )
//                    return;
//
//                View popupView = LayoutInflater.from(context).inflate(R.layout.correction_popup_sample, null);
//
//                Balloon balloon = new Balloon.Builder(context)
//                        .setArrowSize(10)
//                        .setArrowOrientation(ArrowOrientation.TOP)
//                        .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
//                        .setArrowPosition(0.5f)
//                        .setWidth(BalloonSizeSpec.WRAP)
//                        .setHeight(BalloonSizeSpec.WRAP)
//                        .setCornerRadius(4f)
////                        .setElevation(10)
//                        .setBackgroundColor(context.getResources().getColor(R.color.gray))
//                        .setArrowColorMatchBalloon(true)
//                        .setLayout(popupView)
//                        .setIsVisibleOverlay(true)
//                        .setOverlayColor(context.getResources().getColor(R.color.overlay_color))
//                        .setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE)
//                        .setDismissWhenOverlayClicked(false)
//                        .setOverlayPadding(1f)
//                        .setOverlayShape(new BalloonOverlayRoundRect(12f,12f))
//                        .setOnBalloonClickListener(new OnBalloonClickListener() {
//                            @Override
//                            public void onBalloonClick(@NotNull View view) {
//
//                            }
//                        })
//                        .setBalloonAnimation(BalloonAnimation.FADE)
//                        .build();
//
//                balloon.showAlignTop(v,0,60);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {

        WordSampleLayoutBinding binding;

        public WordViewHolder(View itemView) {
            super(itemView);
            binding = WordSampleLayoutBinding.bind(itemView);
        }
    }
}
