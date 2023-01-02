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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
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
    Button signout;
    FloatingActionButton upload_btn;
    TextView message;
    FirebaseAuth mauth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        FirebaseDatabase database=FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
        myRef=database.getReference("events");
        addEvent=findViewById(R.id.addEvent);
        eventList=findViewById(R.id.eventsView);
        loadingPB=findViewById(R.id.PBLoading);
        message=findViewById(R.id.no_events_admin);
        signout=findViewById(R.id.signout_admin);
        upload_btn=findViewById(R.id.upload_btn_home);
        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),UploadActivity.class);
                startActivity(i);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mauth.signOut();
                Toast.makeText(getApplicationContext(),"Signed out!",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),loginActivity.class);
                startActivity(i);
                finish();
            }
        });
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
        if(list.isEmpty()){
            loadingPB.setVisibility(View.GONE);
            message.setVisibility(View.VISIBLE);
            eventList.setVisibility(View.GONE);
        }
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
                loadingPB.setVisibility(View.GONE);
                message.setVisibility(View.GONE);
                eventList.setVisibility(View.VISIBLE);
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