package com.example.core;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class AddEvent extends AppCompatActivity {
    FirebaseDatabase database=FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
    DatabaseReference myRef=database.getReference("events");
    Random random = new Random();
    EditText Topic,Person,date,time,duration,description;
    Button Submit_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Topic=findViewById(R.id.topic);
        Person=findViewById(R.id.person);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        duration=findViewById(R.id.duration);
        description=findViewById(R.id.desc);
        Submit_btn=findViewById(R.id.submit_btn);
        Submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int y = random.nextInt(1000);
                    String topic=Topic.getText().toString();
                    String person=Person.getText().toString();
                    String date1=date.getText().toString();
                    String time1=time.getText().toString();
                    String dura=duration.getText().toString();
                    String desc=description.getText().toString();
                    GetEvents getEvents=new GetEvents(topic,person,date1,time1,dura,desc);
                    myRef.child(Integer.toString(y)).setValue(getEvents);
                    Toast.makeText(getApplicationContext(),"Event Added Succesfully!",Toast.LENGTH_LONG).show();
                    Topic.setText("");
                    Person.setText("");
                    date.setText("");
                    time.setText("");
                    duration.setText("");
                    description.setText("");

            }
        });
    }
}