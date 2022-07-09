package com.example.core;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminLogin extends AppCompatActivity {
    Button admin_signin;
    EditText uname,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        admin_signin=findViewById(R.id.submit_btn);
        uname=findViewById(R.id.username);
        pass=findViewById(R.id.password_admin);
        admin_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un=uname.getText().toString();
                String pa=pass.getText().toString();
                if(!un.equals("admin") ) {
                    Toast.makeText(adminLogin.this,"Wrong Username!",Toast.LENGTH_LONG).show();
                    return;
                }else if(!pa.equals("admin")){
                    Toast.makeText(adminLogin.this,"Wrong password!",Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    Toast.makeText(adminLogin.this,"Welcome Admin!",Toast.LENGTH_LONG).show();

                    Intent i=new Intent(adminLogin.this,AdminHome.class);
                    startActivity(i);
                }
            }
        });
    }
}