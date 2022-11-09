package com.example.core;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {
    Button proceed;
    EditText email;
    ProgressBar loadingPB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        proceed=findViewById(R.id.proceed_btn);
        email=findViewById(R.id.email_forgot);
        loadingPB=findViewById(R.id.PBLoading);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em=email.getText().toString();
                if(em.isEmpty()){
                    Toast.makeText(ForgotPass.this,"Email required!",Toast.LENGTH_SHORT).show();
                }else{
                    loadingPB.setVisibility(View.VISIBLE);
                    System.out.println(em);
                    FirebaseAuth.getInstance().sendPasswordResetEmail(em).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPass.this,"Done..Please Check Your Email For Further Instructions!",Toast.LENGTH_SHORT).show();
                            loadingPB.setVisibility(View.GONE);
                            finish();
                        }else{
                            Toast.makeText(ForgotPass.this,"No User Exists with this email!",Toast.LENGTH_SHORT).show();
                            loadingPB.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
    }
}