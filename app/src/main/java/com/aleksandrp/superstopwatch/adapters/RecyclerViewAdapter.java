package com.aleksandrp.superstopwatch.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandrp.mystopwatch.R;
import com.aleksandrp.superstopwatch.entity.Time;

import java.util.List;

/**
 * Created by Aleksandr on 08.10.2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TimeViewHolder> {

    private List<Time> times;

    public RecyclerViewAdapter(List<Time> times) {
        this.times = times;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView timeTitle;
        TextView timeValue;
        ImageView timeIcon;

        TimeViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            timeTitle = (TextView) itemView.findViewById(R.id.time_title);
            timeValue = (TextView) itemView.findViewById(R.id.time_value);
            timeIcon = (ImageView) itemView.findViewById(R.id.time_photo);
        }
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        TimeViewHolder timeViewHolder = new TimeViewHolder(view);
        return timeViewHolder;
    }

    @Override
    public void onBindViewHolder(TimeViewHolder holder, int position) {
        holder.timeTitle.setText(times.get(position).getTitle());
        holder.timeValue.setText(times.get(position).getValue());
        holder.timeIcon.setImageResource(times.get(position).getIconId());
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
