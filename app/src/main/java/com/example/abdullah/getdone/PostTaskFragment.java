package com.example.abdullah.getdone;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;



public class PostTaskFragment extends Fragment {

    View view;
    private Fragment baseContext;
    private int contentView;

    EditText title,describtion,person,bud,locaton;
    RadioButton physical,online;

    String Title,Dis,Per,Bud,Loc;
    Button sign;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_post_task, container, false);

        title=view.findViewById(R.id.tit);
        describtion=view.findViewById(R.id.desc);
        person=view.findViewById(R.id.Personinput);
        bud=view.findViewById(R.id.Budget);
        locaton=view.findViewById(R.id.location);
        physical= view.findViewById(R.id.radioButton);
        online=view.findViewById(R.id.radioButton1);
        sign=view.findViewById(R.id.btn_sign_in);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title=title.getText().toString();
                Dis=describtion.getText().toString();
                Per=person.getText().toString();
                Bud=bud.getText().toString();
                Loc=locaton.getText().toString();


                if(title.length()==0){
                    title.setError("Empty!");
                }
                if(describtion.length()==0)
                {
                    describtion.setError("Empty");
                }
                if(person.length()==0)
                {
                    person.setError("Empty");
                }
                if(bud.length()==0)
                {
                    bud.setError("Empty");
                }
                if(locaton.length()==0)
                {
                    locaton.setError("Empty");
                }
                else{

                    //here write

                }

            }
        });

        return view;


    }

}