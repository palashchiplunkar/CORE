package com.example.core;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Timestamp;
import java.time.Instant;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    Button signin, reg_button;
    EditText email, pass, cpass, name, usn;
    FirebaseAuth mauth;
    FirebaseDatabase database=FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
    DatabaseReference myRef=database.getReference("users");
    ProgressBar loadingPB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final String[] year_submit = new String[1];
        final String[] section_submit = new String[1];
        String[] year=new String[]{
                "2","3","4"
        };
        String[] section=new String[]{
                "CS1","CS2","CS3"
        };
        ArrayAdapter<String> year_adapter =new ArrayAdapter<>(this,R.layout.drop_down_item,year);
        ArrayAdapter<String> section_adapter =new ArrayAdapter<>(this,R.layout.drop_down_item,section);
        AutoCompleteTextView yr= findViewById(R.id.year);
        AutoCompleteTextView sec = findViewById(R.id.section);
        yr.setAdapter(year_adapter);
        sec.setAdapter(section_adapter);
        yr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                year_submit[0] =yr.getText().toString();
            }
        });
        sec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                section_submit[0] = sec.getText().toString();
            }
        });
        loadingPB=findViewById(R.id.PBLoading);
        signin = findViewById(R.id.signin);
        reg_button = findViewById(R.id.register_button);
        email = findViewById(R.id.remail);
        usn = findViewById(R.id.usn);
        name = findViewById(R.id.fname_field);
        pass = findViewById(R.id.rpass);
        cpass = findViewById(R.id.rcpass);
        mauth = FirebaseAuth.getInstance();
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String em=email.getText().toString();
                String na=name.getText().toString();
                String us=usn.getText().toString();
                String pa=pass.getText().toString();
                String cpa=cpass.getText().toString();
                System.out.println(em);
                if(!pa.equals(cpa)){
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this,"Password Didn't Match!",Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(em) && TextUtils.isEmpty(na) && TextUtils.isEmpty(us) && TextUtils.isEmpty(pa) && TextUtils.isEmpty(cpa)){
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this,"Fields Should Not Be Empty!",Toast.LENGTH_LONG).show();
                }else{
                    mauth.createUserWithEmailAndPassword(em,pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                HashMap<String, String> users = new HashMap<>();
                                users.put("Name",na);
                                users.put("Email",em);
                                users.put("USN",us);
                                users.put("year",year_submit[0]);
                                users.put("section",section_submit[0]);
                                users.put("isAdmin","false");
                                    //GetUsers getUsers=new GetUsers(em,na,us);
                                    //String stamp=Long.toString(System.currentTimeMillis());
                                    myRef.child("year-"+year_submit[0]).child(section_submit[0]).push().setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                loadingPB.setVisibility(View.GONE);
                                                Toast.makeText(RegisterActivity.this,"Registration Done!",Toast.LENGTH_LONG).show();
                                                Intent i=new Intent(RegisterActivity.this,loginActivity.class);
                                                startActivity(i);
                                                finish();
                                            }else{
                                                Toast.makeText(RegisterActivity.this,"Failed to register!",Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });


                            }else{
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this,"Failed to register!",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
    }

}