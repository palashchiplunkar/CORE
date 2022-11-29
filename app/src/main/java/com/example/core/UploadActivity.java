package com.example.core;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadActivity extends AppCompatActivity {
    FirebaseDatabase db= FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
    DatabaseReference uploadRef;
    Button office,report;
    ProgressBar pbar;
    EditText text1,text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        uploadRef=db.getReference();
        office = findViewById(R.id.upload_btn1);
        report = findViewById(R.id.update_btn2);
        pbar= findViewById(R.id.PBLoading);
        text1 = findViewById(R.id.office);
        text2 = findViewById(R.id.report);

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbar.setVisibility(View.VISIBLE);
                String input = text1.getText().toString();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(UploadActivity.this, "Please Enter the Link", Toast.LENGTH_SHORT).show();
                } else {
                    uploadRef.child("uploads").child("office_bearers").setValue(input).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(UploadActivity.this, "Upload Successfull", Toast.LENGTH_SHORT).show();
                                text1.setText("");
                            } else {
                                Toast.makeText(UploadActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
                pbar.setVisibility(View.GONE);
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbar.setVisibility(View.VISIBLE);
                String input = text2.getText().toString();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(UploadActivity.this, "Please Enter the Link", Toast.LENGTH_SHORT).show();
                } else {
                    uploadRef.child("uploads").child("report").setValue(input).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(UploadActivity.this, "Upload Successfull", Toast.LENGTH_SHORT).show();
                                text2.setText("");
                            } else {
                                Toast.makeText(UploadActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
                pbar.setVisibility(View.GONE);
            }

        });

    }
}