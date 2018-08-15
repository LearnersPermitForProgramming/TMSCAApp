package com.tmsca.sagarwal.tmscaapp.Fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.tmsca.sagarwal.tmscaapp.R;

public class HomeFragment extends Fragment{
    // Declaring the variables - going to convert to Kotlin soon.
    TextView welcome;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home, container, false);
        welcome = (TextView) rootView.findViewById(R.id.textView4);


        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Log.d("AUTH", "User is Signed in");
        }else if(FirebaseAuth.getInstance().getCurrentUser() == null){
            Log.d("AUTH", "The User is not logged in using FIREBASE AUTH");

        }else{
            Log.d("AUTH", "There is really something going on that is wrong");
        }

        final FirebaseAuth fAuth = FirebaseAuth.getInstance();
        DatabaseReference fNotesDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("basic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                System.out.println(name);
                //if(name == null) happens when the user logs in with their google account. This is fixed in the latest update.
                if(name == null){
                    welcome.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName() + ", welcome to the Pearson Math and Science Team!");
                    name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                    FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("basic").child(name);
                }else{
                    welcome.setText(name + ", welcome to the Math and Science Team!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        System.out.print(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        return rootView;
    }
}
