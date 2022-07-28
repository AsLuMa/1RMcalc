package com.example.a1rmsimple;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder>{

    private final List<String> mWeightList;
    private final List<Integer> mPercentageList;
    private LayoutInflater mInflater;

    public RecycleViewAdapter(Context context, List<String> weightList, List<Integer> mPercentagelist){
        mInflater = LayoutInflater.from(context);
        this.mWeightList = weightList;
        this.mPercentageList = mPercentagelist;
        this.notifyDataSetChanged();
    }

    class RecycleViewHolder extends RecyclerView.ViewHolder {
        public TextView weightTextView;
        public TextView percentageTextView;
        public EditText max_rep;
        private RecycleViewAdapter mAdapter;

        public RecycleViewHolder(View myView, RecycleViewAdapter adapter){
            super(myView);
            weightTextView = myView.findViewById(R.id.rep_weight);
            percentageTextView = myView.findViewById(R.id.rep_percent);
            max_rep = myView.findViewById(R.id.enter_max);
            this.mAdapter = adapter;

        }

    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.grid_layout, parent, false);
        return new RecycleViewHolder(mView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        String wCurrent = mWeightList.get(position);
        int pCurrent = mPercentageList.get(position);
        int deepPink = ContextCompat.getColor(holder.weightTextView.getContext(), R.color.DeepPink);
        int misty = ContextCompat.getColor(holder.weightTextView.getContext(), R.color.MistyRose);
        int hotPink = ContextCompat.getColor(holder.weightTextView.getContext(), R.color.HotPink);
        int crimson = ContextCompat.getColor(holder.weightTextView.getContext(), R.color.Crimson);
        int chocolate = ContextCompat.getColor(holder.weightTextView.getContext(), R.color.Chocolate);
        int blueViolet = ContextCompat.getColor(holder.weightTextView.getContext(), R.color.BlueViolet);

        holder.weightTextView.setText(wCurrent);

        //TODO fix this (the string resource already exists)
        holder.percentageTextView.setText(String.format("%s%%", String.valueOf(pCurrent)));

        //TODO this interacts with the clicks from the edittext. Somehow.
        holder.weightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.weightTextView.getCurrentTextColor() != deepPink){
                    holder.weightTextView.setTextColor(deepPink);
                    holder.weightTextView.setBackgroundColor(Color.WHITE);
                }

                else if (holder.weightTextView.getCurrentTextColor() == deepPink){
                    holder.weightTextView.setTextColor(Color.WHITE);
                    holder.weightTextView.setBackgroundColor(blueViolet);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return mWeightList.size();
    }
}
