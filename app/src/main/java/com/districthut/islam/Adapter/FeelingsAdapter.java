package com.districthut.islam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.districthut.islam.Models.Feeling;
import com.mirfatif.noorulhuda.R;
import com.districthut.islam.Utils.OnRecyclerViewItemClickListener;
import com.mirfatif.noorulhuda.databinding.ItemFeelingBinding;

import java.util.ArrayList;

public class FeelingsAdapter extends RecyclerView.Adapter<FeelingsAdapter.FeelingsViewHolder> {

    Context context;
    ArrayList<Feeling> feelings;
    OnRecyclerViewItemClickListener listener;

    public FeelingsAdapter(Context context, ArrayList<Feeling> feelings) {
        this.context = context;
        this.feelings = feelings;
    }

    @NonNull
    @Override
    public FeelingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feeling, parent,false);
        return new FeelingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeelingsViewHolder holder, final int position) {
        Feeling feeling = feelings.get(position);
        holder.binding.image.setImageResource(feeling.getImage());
        holder.binding.feeling.setText(feeling.getFeeling());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecyclerViewItemClicked(position, view.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return feelings.size();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener)
    {
        this.listener = listener;
    }

    public class FeelingsViewHolder extends RecyclerView.ViewHolder {

        ItemFeelingBinding binding;

        public FeelingsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemFeelingBinding.bind(itemView);
        }
    }
}
