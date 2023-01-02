package com.example.core;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class loginActivity extends AppCompatActivity {
    Button register,admin,signin;
    EditText email,pass;
    TextView forgot_pass;
    ProgressBar loadingPB;
    FirebaseAuth mauth=FirebaseAuth.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
    DatabaseReference myRef=database.getReference("users");


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        loadingPB=findViewById(R.id.PBLoading);
        register=findViewById(R.id.register_btn);
        forgot_pass=findViewById(R.id.forgot_pass);
        signin=findViewById(R.id.submit_btn);
        email=findViewById(R.id.username);
        pass=findViewById(R.id.password_admin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(loginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ForgotPass.class);
                startActivity(i);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                signin.setEnabled(false);
                String em=email.getText().toString();
                String pa=pass.getText().toString();
                if(TextUtils.isEmpty(em) || TextUtils.isEmpty(pa)){
                    loadingPB.setVisibility(View.GONE);
                    signin.setEnabled(true);
                    Toast.makeText(loginActivity.this,"Fields Should Not Be Empty!",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    mauth.signInWithEmailAndPassword(em,pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                intentProtector(em);

                            }else{
                                loadingPB.setVisibility(View.GONE);
                                signin.setEnabled(true);
                                Toast.makeText(loginActivity.this,task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            }
        });
    }

    private void intentProtector(String email) {
        myRef.child("admins").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean flag=false;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                    if(snapshot.child("isAdmin").getValue().equals("true") && snapshot.child("Email").getValue().equals(email)){
                        flag=true;
                        break;
                    }
                }
                loadingPB.setVisibility(View.GONE);

                if(flag){
                    Toast.makeText(loginActivity.this,"Welcome Back!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(loginActivity.this,AdminHome.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(loginActivity.this,"Welcome Back!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(loginActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}