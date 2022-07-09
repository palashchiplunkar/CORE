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

public class loginActivity extends AppCompatActivity {
    Button register,admin,signin;
    EditText email,pass;
    ProgressBar loadingPB;
    FirebaseAuth mauth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadingPB=findViewById(R.id.PBLoading);
        register=findViewById(R.id.register_btn);
        admin=findViewById(R.id.admin_btn);
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
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(loginActivity.this,adminLogin.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String em=email.getText().toString();
                String pa=pass.getText().toString();
                if(TextUtils.isEmpty(em) && TextUtils.isEmpty(pa)){
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(loginActivity.this,"Fields Should Not Be Empty!",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    mauth.signInWithEmailAndPassword(em,pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(loginActivity.this,"Welcome Back!",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(loginActivity.this,MainActivity.class);
                                startActivity(i);

                            }else{
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(loginActivity.this,"Failed! Please Check your Credentials",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mauth.getCurrentUser();
        if(user!=null){
            Intent i = new Intent(loginActivity.this,MainActivity.class);
            startActivity(i);
            this.finish();
        }
    }

}