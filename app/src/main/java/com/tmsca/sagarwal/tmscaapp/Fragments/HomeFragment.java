package com.tmsca.sagarwal.tmscaapp.Fragments;

import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.tmsca.sagarwal.tmscaapp.R;

public class HomeFragment extends Fragment{

    TextView welcome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home, container, false);
        welcome = (TextView) rootView.findViewById(R.id.textView4);
        welcome.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail() + ", Welcome to the Math and Science Team Website");
        return rootView;
    }
}
