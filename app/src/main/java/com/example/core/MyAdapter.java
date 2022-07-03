package com.example.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<event> list;


    public MyAdapter(Context context, ArrayList<event> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.events_row,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

      event even=list.get(position);
      holder.topic.setText(even.getTopic());
        holder.person.setText(even.getPerson());
        holder.date.setText(even.getDate());
        holder.time.setText(even.getTime());
        holder.duration.setText(even.getDuration());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView topic,person,date,time,duration;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            topic= itemView.findViewById(R.id.topicH);
            person = itemView.findViewById(R.id.rpH);
            date = itemView.findViewById(R.id.dateH);
            time= itemView.findViewById(R.id.timeH);
            duration = itemView.findViewById(R.id.durH);
        }
    }

}