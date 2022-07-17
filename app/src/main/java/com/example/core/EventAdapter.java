package com.example.core;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private ArrayList<GetEvents> getEventsArrayList;
    private Context context;
    int lastPos=-1;
    private EventClickInterFace eventClickInterFace;



    public EventAdapter(ArrayList<GetEvents> getEventsArrayList, Context context, EventClickInterFace eventClickInterFace) {
        this.getEventsArrayList = getEventsArrayList;
        this.context = context;
        this.eventClickInterFace = eventClickInterFace;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.events_row,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {
            GetEvents getEvents=getEventsArrayList.get(position);

            holder.TopicName.setText(getEvents.getTopic());
            holder.Date.setText(getEvents.getDate());
            holder.Update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventClickInterFace.onUpdateClick(position);
                }
            });

    }

    @Override
    public int getItemCount() {
        return getEventsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView TopicName,Date,View_btn,Update;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TopicName= itemView.findViewById(R.id.topic_events);
            Date=itemView.findViewById(R.id.date_events);
            //View_btn=itemView.findViewById(R.id.view_user);
            Update=itemView.findViewById(R.id.update);
        }
    }
    public interface EventClickInterFace{
        //void onViewClick(int position);
        void onUpdateClick(int position);
    }
}
