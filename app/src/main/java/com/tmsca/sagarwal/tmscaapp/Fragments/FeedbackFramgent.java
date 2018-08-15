package com.tmsca.sagarwal.tmscaapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tmsca.sagarwal.tmscaapp.R;

import java.util.ArrayList;

//This is for feedback and reporting bugs.

public class FeedbackFramgent extends Fragment{

    Button sendBug;
    EditText bugContent;

    // When the view gets created

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feedback, container, false);
        sendBug = rootView.findViewById(R.id.sendEmail);
        bugContent = rootView.findViewById(R.id.bugContent);


        sendBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bug = bugContent.getText().toString();

                if(bug.equals("")){
                    Toast.makeText(getActivity(), "Please put some content inside of the bug report box.", Toast.LENGTH_SHORT).show();
                }else{
                    String send = "tmscabugreport@gmail.com";
                    String[] split = send.split(",");


                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, split);
                    email.putExtra(Intent.EXTRA_TEXT, bug);
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Choose an app to send this email with."));
                }



            }
        });

        return rootView;
    }
}
