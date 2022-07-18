package com.example.core;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class AddEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseDatabase database=FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
    DatabaseReference myRef=database.getReference("events");

    EditText Topic,Person,date,description,end_time,start_time,name1,ph1,ph2,name2,venue,plink,flink;
    String desig,eventID;
    Button Submit_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfull";
                        if (!task.isSuccessful()) {
                            msg = "failed";
                        }
                        System.out.println(task.getResult());
                        //Toast.makeText(AddEvent.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        Spinner spinner = (Spinner) findViewById(R.id.designation);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.designation_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        Topic=findViewById(R.id.topic);
        Person=findViewById(R.id.person);
        date=findViewById(R.id.date);
        start_time=findViewById(R.id.time);
        description=findViewById(R.id.desc);
        end_time=findViewById(R.id.end_time);
        venue=findViewById(R.id.venue);
        ph1=findViewById(R.id.ph1);
        ph2=findViewById(R.id.ph2);
        name1=findViewById(R.id.name1);
        name2=findViewById(R.id.name2);
        flink=findViewById(R.id.formLink);
        plink=findViewById(R.id.posterLink);
        Submit_btn=findViewById(R.id.submit_btn);
        Submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String topic=Topic.getText().toString();
                    String person=Person.getText().toString();
                    String date1=date.getText().toString();
                    String st_time=start_time.getText().toString();
                    String ed_time=end_time.getText().toString();
                    String desc=description.getText().toString();
                    String ven=venue.getText().toString();
                    String ph11=ph1.getText().toString();
                    String ph22=ph2.getText().toString();
                    String name11=name1.getText().toString();
                    String name22=name2.getText().toString();
                    String pl=plink.getText().toString();
                    String fl=flink.getText().toString();
                    eventID=topic;
                    GetEvents getEvents=new GetEvents(topic,desig,person,date1,st_time,ed_time,ven,desc,ph11,ph22,name11,name22,eventID,fl,pl);
//
                myRef.child(eventID).setValue(getEvents).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Topic.setText("");
                            date.setText("");
                            ph1.setText("");
                            ph2.setText("");
                            venue.setText("");
                            Person.setText("");
                            name2.setText("");
                            end_time.setText("");
                            start_time.setText("");
                            description.setText("");
                            name1.setText("");
                            flink.setText("");
                            plink.setText("");
                            Toast.makeText(AddEvent.this,"Event Added!",Toast.LENGTH_SHORT).show();
                            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

                                NotificationChannel notificationChannel=new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
                                NotificationManager notificationManager=getSystemService(NotificationManager.class);
                                notificationManager.createNotificationChannel(notificationChannel);
                            }

                            FcmNotificationsSender fcmNotificationsSender=new FcmNotificationsSender("/topics/general","A new Event Scheduled",topic,getApplicationContext(),AddEvent.this);
                            fcmNotificationsSender.SendNotifications();
                            Intent i =new Intent(AddEvent.this,AdminHome.class);
                            startActivity(i);


                        }else {
                            Toast.makeText(AddEvent.this,"Failed to Add a Event!",Toast.LENGTH_LONG).show();

                        }

                    }
                });

            }


        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        desig=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}