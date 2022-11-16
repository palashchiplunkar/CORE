package com.example.core;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {
    TextView email;
    FirebaseUser user;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    Button sign_out;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        email=v.findViewById(R.id.email_profile);
        user=auth.getCurrentUser();
        email.setText(user.getEmail());
        sign_out=v.findViewById(R.id.signout);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Toast.makeText(getContext(),"Signed out!",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getContext(),loginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        return v;
    }
}