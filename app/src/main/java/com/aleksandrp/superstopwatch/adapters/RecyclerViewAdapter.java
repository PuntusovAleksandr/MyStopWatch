package com.aleksandrp.superstopwatch.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandrp.mystopwatch.R;
import com.aleksandrp.superstopwatch.db.entity.TimeFix;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Aleksandr on 08.10.2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TimeViewHolder> {

    private List<TimeFix> times;

    public RecyclerViewAdapter(List<TimeFix> times) {
        this.times = times;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView tv_timeTitle;
        TextView tv_timeDate;
        TextView tv_timeLong;
        ImageView timeIcon;

        TimeViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv);
            tv_timeTitle = (TextView) itemView.findViewById(R.id.time_title);
            tv_timeDate = (TextView) itemView.findViewById(R.id.time_date);
            tv_timeLong = (TextView) itemView.findViewById(R.id.time_value);
            timeIcon = (ImageView) itemView.findViewById(R.id.time_photo);
        }
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeViewHolder holder, int position) {
        String timeDate = new SimpleDateFormat("dd.mm.yyyy HH:mm:ss").format(times.get(position).getDate())+"";
        String timeLong = new SimpleDateFormat("hh:mm:ss").format(times.get(position).getTimeLong())+"";
        holder.tv_timeTitle.setText(times.get(position).getTitle());
        holder.tv_timeDate.setText(timeDate);
        holder.tv_timeLong.setText(timeLong);
        holder.timeIcon.setImageResource(R.drawable.ic_launcher);
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