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

public class EventAdapterUser extends RecyclerView.Adapter<EventAdapterUser.ViewHolder> {
    private ArrayList<GetEvents> getEventsArrayList;
    private Context context;
    int lastPos=-1;
    private EventClickInterFace eventClickInterFace;



    public EventAdapterUser(ArrayList<GetEvents> getEventsArrayList, Context context, EventClickInterFace eventClickInterFace) {
        this.getEventsArrayList = getEventsArrayList;
        this.context = context;
        this.eventClickInterFace = eventClickInterFace;
    }

    @NonNull
    @Override
    public EventAdapterUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.events_row_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapterUser.ViewHolder holder, int position) {
        GetEvents getEvents=getEventsArrayList.get(position);

        holder.TopicNameUser.setText(getEvents.getTopic());
        holder.DateUser.setText(getEvents.getDate());

        holder.View_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventClickInterFace.onViewClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getEventsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView TopicNameUser,DateUser,View_btn,Update;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TopicNameUser= itemView.findViewById(R.id.topic_events_user);
            DateUser=itemView.findViewById(R.id.date_events_user);
            View_btn=itemView.findViewById(R.id.view_user);

        }
    }
    public interface EventClickInterFace{
        void onViewClick(int position);

    }
}
