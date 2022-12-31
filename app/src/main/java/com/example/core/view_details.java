package com.example.core;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class view_details extends AppCompatActivity {
    FirebaseDatabase database=FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
    DatabaseReference myRef;
    ProgressBar loadingPB;
    TextView Topic_user,Person_user,date_user,description_user,end_time_user,start_time_user,name1_user,ph1_user,ph2_user,name2_user,venue_user,flink_user,plink_user;
    String desig_user,eventID_user;
    Button download_btn_user,register_btn_user,feedback_btn;
    private GetEvents getEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        Topic_user=findViewById(R.id.topic_name_user);
        Person_user=findViewById(R.id.person_user);
        date_user=findViewById(R.id.date_view_user);
        start_time_user=findViewById(R.id.st_time_user);
        description_user=findViewById(R.id.desc_user);
        end_time_user=findViewById(R.id.ed_time_user);
        venue_user=findViewById(R.id.venue_user);
        ph1_user=findViewById(R.id.handlerPh1);
        ph2_user=findViewById(R.id.handlerPh2);
        name1_user=findViewById(R.id.handlerName1);
        name2_user=findViewById(R.id.handlerName2);
        register_btn_user=findViewById(R.id.register_btn_user);
        download_btn_user=findViewById(R.id.poster_download);
        feedback_btn=findViewById(R.id.feedback_btn_user);
        getEvents=getIntent().getParcelableExtra("events");
        if(getEvents!=null){
            Topic_user.setText(getEvents.getTopic());
            Person_user.setText(getEvents.getPerson());
            date_user.setText(getEvents.getDate());
            start_time_user.setText(getEvents.getStartTime());
            end_time_user.setText(getEvents.getEndTime());
            venue_user.setText(getEvents.getVenue());
            if(TextUtils.isEmpty(getEvents.getEventHandlerPh1())){
                ph1_user.setText("No information availbale");
            }else {
                ph1_user.setText(getEvents.getEventHandlerPh1());
            }
            if(TextUtils.isEmpty(getEvents.getEventHandlerPh2())){
                ph2_user.setText("No information availbale");
            }else {
                ph2_user.setText(getEvents.getEventHandlerPh2());
            }
            if(TextUtils.isEmpty(getEvents.getEventHandlerName1())){
                name1_user.setText("No information availbale");
            }else {
                name1_user.setText(getEvents.getEventHandlerName1());
            }
            if(TextUtils.isEmpty(getEvents.getEventHandlerName2())){
                name2_user.setText("No information availbale");
            }else {
                name2_user.setText(getEvents.getEventHandlerName2());
            }
            if(TextUtils.isEmpty(getEvents.getDescription())){
                description_user.setText("No information availbale");
            }else {
                description_user.setText(getEvents.getDescription());
            }
            eventID_user=getEvents.getEventsId();
        }
        download_btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String url = getEvents.getPosterLink();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"No details found",Toast.LENGTH_SHORT).show();
                }

            }
        });
        register_btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String url = getEvents.getFormLink();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"No details found",Toast.LENGTH_SHORT).show();

                }
            }
        });
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String url = getEvents.getFeedLink();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"No details found",Toast.LENGTH_SHORT).show();

                }
            }
        });    }
}