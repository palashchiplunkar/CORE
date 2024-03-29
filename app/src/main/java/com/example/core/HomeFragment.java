package com.example.core;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.InputStream;
import java.util.ArrayList;


public class HomeFragment extends Fragment {
    SliderView sliderView;
    ArrayList<String> urls= new ArrayList<>();
    int i=0;
//    int[] images={R.drawable.one, R.drawable.two,
//    R.drawable.three,
//    R.drawable.four,
//    R.drawable.five,
//    R.drawable.six};
    String[] images={"https://images.pexels.com/photos/1632790/pexels-photo-1632790.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://images.pexels.com/photos/36717/amazing-animal-beautiful-beautifull.jpg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
    ,"https://images.pexels.com/photos/129743/pexels-photo-129743.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
"https://images.pexels.com/photos/56875/tree-dawn-nature-bucovina-56875.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
"https://images.pexels.com/photos/589841/pexels-photo-589841.jpeg?auto=compress&cs=tinysrgb&w=600",
"https://images.pexels.com/photos/957024/forest-trees-perspective-bright-957024.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"};
    Button upcoming_events,member_button,report_button;
    Uri uri = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfull";
                        if (!task.isSuccessful()) {
                            msg = "failed";
                        }
                    }
                });

    sliderView=v.findViewById(R.id.image_slider);
    SliderAdapter sliderAdapter=new SliderAdapter(images);
    sliderView.setSliderAdapter(sliderAdapter);
    sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
    sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
    sliderView.startAutoCycle();
    upcoming_events=v.findViewById(R.id.upcoming);
    member_button=v.findViewById(R.id.memberbt);
    report_button=v.findViewById(R.id.reportbt);
    upcoming_events.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,new EventsFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    });
    member_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                String url =urls.get(0);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }catch (Exception e){
                Toast.makeText(getContext(),"No details found",Toast.LENGTH_SHORT).show();
            }
        }
    });
        report_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String url =urls.get(1);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(getContext(),"No details found",Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseDatabase db= FirebaseDatabase.getInstance("https://core-72194-default-rtdb.firebaseio.com/");
        DatabaseReference uploadRef;
        uploadRef=db.getReference("uploads");

        uploadRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 for(DataSnapshot shot : dataSnapshot.getChildren()){
                     urls.add(shot.getValue().toString());
                     i++;
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}