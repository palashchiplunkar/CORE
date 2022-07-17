package com.example.core;

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

public class AdminHome extends AppCompatActivity implements EventAdapter.EventClickInterFace{
    FloatingActionButton addEvent;
    private RecyclerView eventList;
    EventAdapter eventAdapter;
    ArrayList<GetEvents> list;
    ProgressBar loadingPB;
    DatabaseReference myRef;
    GetEvents getEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        FirebaseDatabase database=FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
        myRef=database.getReference("events");
        addEvent=findViewById(R.id.addEvent);
        eventList=findViewById(R.id.eventsView);
        loadingPB=findViewById(R.id.PBLoading);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(AdminHome.this,AddEvent.class);
                startActivity(intent);
            }
        });
        list=new ArrayList<>();
        eventAdapter=new EventAdapter(list,this,this);
        eventList.setLayoutManager(new LinearLayoutManager(this));
        eventList.setAdapter(eventAdapter);
        getAllEvents();
    }

    private void getAllEvents() {
        list.clear();
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                loadingPB.setVisibility(View.GONE);
                getEvents=dataSnapshot.getValue(GetEvents.class);
                list.add(getEvents);
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                loadingPB.setVisibility(View.GONE);
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                loadingPB.setVisibility(View.GONE);
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                loadingPB.setVisibility(View.GONE);
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    @Override
    public void onUpdateClick(int position) {
        getEvents=list.get(position);
        Intent intent=new Intent(AdminHome.this,EditEvents.class);
        intent.putExtra("events",getEvents);
        startActivity(intent);
    }
}