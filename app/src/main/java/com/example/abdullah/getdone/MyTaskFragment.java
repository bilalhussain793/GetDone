package com.example.abdullah.getdone;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyTaskFragment extends Fragment {
    private static final String URL_GETDATA = "http://192.168.8.100/GetDone/get_my_post.php";

    AlertDialog.Builder builder;
    //the recyclerview
    RecyclerView recyclerView;
    public static List<Get_post> productList;

    private static final String URL_GETDATA1 = "http://192.168.0.105/GetDone/get_my_offer.php";


    RecyclerView recyclerView1;
    List<Get_offer> productList1;

    View view;
    private Fragment baseContext;
    private int contentView;
    String username;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_my_task, container, false);
        builder = new AlertDialog.Builder(getActivity());
        username=UserDetails.username;



        UserLogin1(username);


        recyclerView1 = view.findViewById(R.id.recylcerView1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));

        //initializing the productlist
        productList1 = new ArrayList<>();

        UserLogin(username);


        recyclerView = view.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //initializing the productlist
        productList = new ArrayList<>();







        return view;


    }
    private void UserLogin(final String category) {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GETDATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        builder.setTitle("Server Response");
                        builder.setMessage("Response:" + response);
                        builder.setPositiveButton("User Login Successfully", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        try {
                            Toast.makeText(getActivity(),response ,Toast.LENGTH_LONG).show();

                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Get_post(
                                        product.getInt("id"),
                                        product.getString("name"),
                                        product.getString("description"),
                                        product.getString("last_date"),
                                        product.getString("type"),
                                        product.getString("status"),
                                        product.getString("date"),
                                        product.getString("type_of_task"),
                                        product.getString("budget"),
                                        product.getString("location"),
                                        product.getInt("no_of_persons")

                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            PostAdapter adapter = new PostAdapter(getActivity(), productList);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Login Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);

                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addTorequestquee(stringRequest);
    }
    public static void runinit(){

    }
    private void UserLogin1(final String username) {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GETDATA1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        builder.setTitle("Server Response");
                        builder.setMessage("Response:" + response);
                        builder.setPositiveButton("User Login Successfully", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        try {
                            Toast.makeText(getActivity(),response ,Toast.LENGTH_LONG).show();

                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList1.add(new Get_offer(
                                        product.getInt("gig_id"),
                                        product.getString("buyer_name"),
                                        product.getString("amount")


                                ));

                            }

                            //creating adapter object and setting it to recyclerview
                            OffersAdapter adapter = new OffersAdapter(getActivity(), productList1);
                            recyclerView1.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Login Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);

                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addTorequestquee(stringRequest);
    }

}