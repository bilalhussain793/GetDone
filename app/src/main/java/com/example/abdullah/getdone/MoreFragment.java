package com.example.abdullah.getdone;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(getContext(), ""+arrayList.get(i), Toast.LENGTH_SHORT).show();

                if(arrayList.get(i).equals("About us")){

                    startActivity(new Intent(getContext(),about_us.class));
                }
               else if(arrayList.get(i).equals("Contact us")){

                    startActivity(new Intent(getContext(),contact_us.class));
                }
               else if(arrayList.get(i).equals("Update Profile")){

                    startActivity(new Intent(getContext(),update_profile.class));
                }
              else if(arrayList.get(i).equals("Term and Conditions")){

                    startActivity(new Intent(getContext(),terms.class));
                }
                else if(arrayList.get(i).equals("Logout")){

                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("LOGIN", MODE_PRIVATE).edit();
                    editor.putInt("flg", 0);
                    editor.putString("nm", "");

                    editor.apply();
                    startActivity(new Intent(getContext(), MainActivity.class));



                    startActivity(new Intent(getContext(),Login.class));
                }
                else if(arrayList.get(i).equals("Deactivate my account")){

                    startActivity(new Intent(getContext(),Login.class));
                }

            }
        });

        return view;

    }

}