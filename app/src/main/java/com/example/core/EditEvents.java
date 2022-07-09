package com.example.core;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EditEvents extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseDatabase database=FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
    DatabaseReference myRef;
    ProgressBar loadingPB;
    EditText Topic,Person,date,description,end_time,start_time,name1,ph1,ph2,name2,venue,flink,plink;
    String desig,eventID;
    Button update_btn,delete_btn;
    private GetEvents getEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_events);
        //loadingPB.setVisibility(View.GONE);
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
        update_btn=findViewById(R.id.update_btn);
        delete_btn=findViewById(R.id.delete_btn);
        getEvents=getIntent().getParcelableExtra("events");
        if(getEvents!=null){
            Topic.setText(getEvents.getTopic());
            Person.setText(getEvents.getPerson());
            date.setText(getEvents.getDate());
            start_time.setText(getEvents.getStartTime());
            end_time.setText(getEvents.getEndTime());
            description.setText(getEvents.getDescription());
            venue.setText(getEvents.getVenue());
            ph1.setText(getEvents.getEventHandlerPh1());
            ph2.setText(getEvents.getEventHandlerPh2());
            name1.setText(getEvents.getEventHandlerName1());
            name2.setText(getEvents.getEventHandlerName2());
            flink.setText(getEvents.getFormLink());
            plink.setText(getEvents.getPosterLink());
            eventID=getEvents.getEventsId();
        }
        //System.out.println(eventID);
        myRef=database.getReference("events").child(eventID);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loadingPB.setVisibility(View.VISIBLE);
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
                System.out.println(plink.getText().toString());
                String fl=flink.getText().toString();
                HashMap map=new HashMap();
                map.put("date",date1);
                map.put("description",desc);
                map.put("designation",desig);
                map.put("endTime",ed_time);
                map.put("eventHandlerName1",name11);
                map.put("eventHandlerName2",name22);
                map.put("eventHandlerPh1",ph11);
                map.put("eventHandlerPh2",ph22);
                map.put("eventsId",eventID);
                map.put("formLink",fl);
                map.put("person",person);
                map.put("posterLink",pl);
                map.put("startTime",st_time);
                map.put("topic",topic);
                map.put("venue",ven);

               myRef.updateChildren(map).addOnCompleteListener(new OnCompleteListener() {
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
                            Toast.makeText(EditEvents.this,"Event Updated!",Toast.LENGTH_SHORT).show();
                            Intent i =new Intent(EditEvents.this,AdminHome.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(EditEvents.this,"Failed to Update Event!",Toast.LENGTH_LONG).show();

                        }

                  }
                });

            }
        });
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteEvent();
            }
        });

    }
    private void deleteEvent(){
        myRef.removeValue();
        Toast.makeText(EditEvents.this,"Event Deleted!",Toast.LENGTH_LONG).show();
        Intent i =new Intent(EditEvents.this,AdminHome.class);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        desig=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}