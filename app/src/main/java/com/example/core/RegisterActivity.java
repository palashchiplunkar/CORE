package com.example.core;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
                                    GetUsers getUsers=new GetUsers(em,na,us);
                                    String stamp=Long.toString(System.currentTimeMillis());
                                    myRef.child(stamp).setValue(getUsers).addOnCompleteListener(new OnCompleteListener<Void>() {
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