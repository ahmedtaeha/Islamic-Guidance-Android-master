package com.districthut.islam.Activities.Quran.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mirfatif.noorulhuda.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestMainActivity extends AppCompatActivity {
    


    private RecyclerView rv_after;

    private List<String> one = new ArrayList<>();
    private List<String> two = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        one.add("Hello, how is work?");
        two.add("Hello, everything is fine, thank you for asking.");
        one.add("?");
        two.add("How are you today?");
        one.add("I not feeling well!");
        two.add("Are you sick, you seem extremely tired?");
        one.add("I've got flu");
        two.add("Go home and please stay there until you feel better. " +
                "You don't want to spread your infection!" + " Go home and please stay there until you feel better. " +
                "You don't want to spread your infection!");
        

        rv_after = (RecyclerView) findViewById(R.id.rv_after);
        rv_after.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerA = new LinearLayoutManager(TestMainActivity.this);
        rv_after.setLayoutManager(linearLayoutManagerA);


        CustomRecyclerViewAdapter customRecyclerViewAdapterA = new CustomRecyclerViewAdapter(TestMainActivity.this, false, one, two);
        rv_after.setAdapter(customRecyclerViewAdapterA);
    }

    private class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomViewHolder> {

        private final Context context;
        private List<String> fixed;
        private List<String> variable;
        private final boolean before;

        public CustomRecyclerViewAdapter(Context context, boolean before,
                                         List<String> fixed, List<String> variable) {
            super();
            this.context = context;
            this.before = before;
            this.fixed = fixed;
            this.variable = variable;
        }

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View layout = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
            return new CustomViewHolder(layout);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            if(!before) { // after
                holder.textViewInADifferentWay.setText(fixed.get(position), variable.get(position));
            }else{ // before
                TextView tv_fixed = (TextView) holder.text_view_in_a_different_way.findViewById(R.id.tv_fixed);
                TextView tv_variable = (TextView) holder.text_view_in_a_different_way.findViewById(R.id.tv_variable);
                tv_fixed.setText(fixed.get(position));
                tv_variable.setText(variable.get(position));
            }

        }

        @Override
        public int getItemCount() {
            return fixed.size();
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            private TextViewInADifferentWay textViewInADifferentWay;
            private final View text_view_in_a_different_way;


            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                text_view_in_a_different_way = (View)
                        itemView.findViewById(R.id.text_view_in_a_different_way);
                if(!before) { // after
                    textViewInADifferentWay = new TextViewInADifferentWay(context,
                            text_view_in_a_different_way);
                }

            }
        }

        public void changeText(List<String> fixed, List<String> variable) {
            this.fixed = fixed;
            this.variable = variable;
            this.notifyDataSetChanged();
        }
    }

}

