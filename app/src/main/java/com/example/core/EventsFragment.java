package com.example.core;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class EventsFragment extends Fragment implements EventAdapterUser.EventClickInterFace {
    FloatingActionButton addEvent;
    RecyclerView eventList;
    ArrayList<GetEvents> list;
    ProgressBar loadingPB;
    DatabaseReference myRef;

    GetEvents getEvents;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v=inflater.inflate(R.layout.fragment_events, container, false);
        FirebaseDatabase database=FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
        myRef=database.getReference("events");
        eventList=v.findViewById(R.id.eventsViewUser);
        loadingPB=v.findViewById(R.id.PBLoading);
        list=new ArrayList<>();
        EventAdapterUser eventAdapterUser=new EventAdapterUser(list,getContext(),this);
        eventList.setLayoutManager(new LinearLayoutManager(getContext()));
        eventList.setAdapter(eventAdapterUser);
        list.clear();
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                loadingPB.setVisibility(View.GONE);
                getEvents =dataSnapshot.getValue(GetEvents.class);
                list.add(getEvents);
                eventAdapterUser.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                loadingPB.setVisibility(View.GONE);
                eventAdapterUser.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                loadingPB.setVisibility(View.GONE);
                eventAdapterUser.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                loadingPB.setVisibility(View.GONE);
                eventAdapterUser.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }


    @Override
    public void onViewClick(int position) {
        getEvents=list.get(position);
        Intent intent=new Intent(getContext(),view_details.class);
        intent.putExtra("events",getEvents);
        startActivity(intent);
    }


}

