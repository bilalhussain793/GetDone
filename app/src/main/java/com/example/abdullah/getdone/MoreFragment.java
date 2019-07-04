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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MoreFragment extends Fragment {

    View view;
    private Fragment baseContext;
    private int contentView;
    ListView lv;
    ArrayList<String> arrayList=new ArrayList<String>();
    ArrayAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_more, container, false);

        arrayList.add("About us");
        arrayList.add("Contact us");
        arrayList.add("Update Profile");
        arrayList.add("Term and Conditions");
        arrayList.add("Logout");
        arrayList.add("Deactivate my account");

        lv=view.findViewById(R.id.lv);
        adapter=new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,arrayList);
        lv.setAdapter(adapter);

        return view;


    }

}



