package com.example.core;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth mauth=FirebaseAuth.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
    DatabaseReference myRef=database.getReference("users");
    ProgressBar loadingPB;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadingPB=findViewById(R.id.PBLoading);
        loadingPB.setVisibility(View.VISIBLE);
        user=mauth.getCurrentUser();
        if(user!=null) {
            myRef.child("admins").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean flag = false;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        if (snapshot.child("isAdmin").getValue().equals("true") && snapshot.child("Email").getValue().equals(user.getEmail())) {
                            flag = true;
                            break;
                        }
                    }
                    loadingPB.setVisibility(View.GONE);

                    if (flag) {

                        Intent i = new Intent(SplashActivity.this, AdminHome.class);
                        startActivity(i);
                        finish();
                    } else {

                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }else{
                    new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, loginActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
            loadingPB.setVisibility(View.GONE);
        }
    }


}