package com.example.abdullah.getdone;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;


public class PostTaskFragment extends Fragment {

    View view;
    private Fragment baseContext;
    RadioButton radioButton;
    private int contentView;
    AlertDialog.Builder builder;

    String nm =UserDetails.username;

    EditText title,etdescription,person,budget,locaton,unm;
    private String URL_REGIST = UserDetails.Url+"posttask.php";
    RadioButton physical,online;

    String Title,description,no_of_Persons,Budget,Location;
    String typeabd = null;
    String type=null,typetask=null;
    String Type_of_task=null;
    Button sign;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_post_task, container, false);

        title=view.findViewById(R.id.tit);
        etdescription=view.findViewById(R.id.desc);
        person=view.findViewById(R.id.Personinput);
        budget=view.findViewById(R.id.Budget);
        locaton=view.findViewById(R.id.location);


        sign=view.findViewById(R.id.btn_sign_in);

        unm=view.findViewById(R.id.usernames);
        builder = new AlertDialog.Builder(getContext());

        unm.setText(UserDetails.username);
        final Spinner spinner =  view.findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Select Category");
        categories.add("Pick Up & Deliver");
        categories.add("Cleaning");
        categories.add("Gardening");
        categories.add("Home Services");
        categories.add("IT Services");
        categories.add("Other");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        final Spinner spinner1 =  view.findViewById(R.id.spinner1);
        List<String> categories1 = new ArrayList<String>();
        categories1.add("Select Type of Task");
        categories1.add("Physical");
        categories1.add("Online");



        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories1);
        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title=title.getText().toString();
                description=etdescription.getText().toString();
                no_of_Persons=person.getText().toString();
                Budget=budget.getText().toString();
                Location=locaton.getText().toString();

                typeabd = spinner.getSelectedItem().toString();
                Type_of_task = spinner.getSelectedItem().toString();


                if(typeabd=="Pick Up & Deliver")
                {
                    type="8";

                }
                else if(typeabd=="Cleaning")
                {
                    type="9";

                }
                else if(typeabd=="Gardening")
                {
                    type="10";

                }
                else if(typeabd=="Home Services")
                {
                    type="11";

                }
                else if(typeabd=="IT Services")
                {
                    type="12";

                }
                else if(typeabd=="Other")
                {
                    type="13";

                }
                else {
                    Toast.makeText(getActivity(),"Please Catergory Type" ,Toast.LENGTH_LONG).show();
//

                }
                int value = Integer.parseInt(type);







                if(Title.length()==0){
                    title.setError("Empty!");
                }
                if(description.length()==0)
                {
                    etdescription.setError("Empty");
                }
                if(no_of_Persons.length()==0)
                {
                    person.setError("Empty");
                }
                if(Budget.length()==0)
                {
                    budget.setError("Empty");
                }
                if(Location.length()==0)
                {
                    locaton.setError("Empty");
                }
                else
                {





                   PostTask(Title, description, no_of_Persons, Budget,Location,type,Type_of_task,nm);




                    //Toast.makeText(getContext(),String.valueOf(selectedId),Toast.LENGTH_SHORT).show();

                    Toast.makeText(getContext(), "Task Posted\n Successful", Toast.LENGTH_SHORT).show();
                    //here write
                }

            }
        });

        return view;


    }

    private void PostTask(final String Title, final String description, final String no_of_Persons, final String Budget,final String Location,final String type,final String type_of_task,final String Username) {
        int value=1;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Task Details");
                        builder.setMessage("Task Posted Successfully");
                        builder.setPositiveButton("Task Posted Successfully", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


//                                title.setText("");
//                                // etType.setText("");
//                                locaton.setText("");
//                                budget.setText("");
//                                person.setText("");
//                                etdescription.setText("");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "SignUp Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("Title",Title);
                params.put("description",description);
                params.put("locaton", Location);
               // params.put("type",radioButton.getText());
                params.put("Budget",Budget);
                params.put("no_of_Persons",no_of_Persons);
                params.put("type",type);
                params.put("type_of_task",type_of_task);
                params.put("username",nm);




                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addTorequestquee(stringRequest);
    }
}