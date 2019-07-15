package com.example.abdullah.getdone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_dtl_task_offer extends AppCompatActivity {

    TextView name,type,status,location,budget,last_date,no_persons,type_of_task,desc,Name;
    Button offer;

    private static final String URL_GETDATA =  UserDetails.Url +"get_offer.php";

    AlertDialog.Builder builder;
    //the recyclerview
    RecyclerView recyclerView;
    List<Get_offer> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtl_task_offer);
        name = findViewById(R.id.tvtitle);
        type = findViewById(R.id.typ);
        status = findViewById(R.id.stat);
        location = findViewById(R.id.location);
        budget = findViewById(R.id.budget);
//        last_date = findViewById(R.id.lstdate);
        no_persons = findViewById(R.id.No_of_persons);
        type_of_task = findViewById(R.id.cat);
        offer = findViewById(R.id.btn_off);
        desc = findViewById(R.id.dsc);
        Name = findViewById(R.id.nam);
        builder = new AlertDialog.Builder(this);

        Name.setText(UserDetails.username);




        final String id = getIntent().getStringExtra("id");
        final String Name = getIntent().getStringExtra("Name");
        final String Desc = getIntent().getStringExtra("Desc");
        final String Last_Date = getIntent().getStringExtra("Last Date");
        final String Date = getIntent().getStringExtra("Date");
        final String Status = getIntent().getStringExtra("Status");
        final String Location = getIntent().getStringExtra("Location");
        final String Type = getIntent().getStringExtra("Type");
        final String Type_of_Task = getIntent().getStringExtra("Type of Task");
        final String Budget = getIntent().getStringExtra("Budget");
        final String No_Of_Persons = getIntent().getStringExtra("No Of Persons");

        if(Type=="8")
        {
            type.setText("Pickup & Delivery");

        }
        else  if(Type=="9")
        {
            type.setText("Cleaning");
        }
        else  if(Type=="10")
        {
            type.setText("Gardening");
        }
        else  if(Type=="11")
        {
            type.setText("Home Services");
        }
        else  if(Type=="12")
        {
            type.setText("It Services");
        }
        else  if(Type=="13")
        {
            type.setText("Others");

        }
        else
        {
            type.setText("Nothing");
        }





        name.setText(Name);

        status.setText(Status);
        location.setText(Location);
        budget.setText("Rs." + Budget);
        //last_date.setText(Last_Date);
        no_persons.setText(No_Of_Persons);
        type_of_task.setText(Type_of_Task);
        desc.setText(Desc);




        UserLogin(id);

        offer.setOnClickListener(new View.OnClickListener() {//Calling on click listener for Add Button

            public void onClick(View v)//calling the onClick function
            {
                Intent intent = new Intent(v.getContext(), offer.class);


                intent.putExtra("id", id);

                v.getContext().startActivity(intent);


            }
        });
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();
//        loadProducts();
    }
    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GETDATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(activity_dtl_task_offer.this,response ,Toast.LENGTH_LONG).show();

                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Get_offer(
                                        product.getInt("gig_id"),
                                        product.getString("buyer_name"),
                                        product.getString("amount")


                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            MyOffersAdapter adapter = new MyOffersAdapter(activity_dtl_task_offer.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }




    private void UserLogin(final String id) {

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
                            Toast.makeText(activity_dtl_task_offer.this,response ,Toast.LENGTH_LONG).show();

                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Get_offer(
                                        product.getInt("gig_id"),
                                        product.getString("buyer_name"),
                                        product.getString("amount")


                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            MyOffersAdapter adapter = new MyOffersAdapter(activity_dtl_task_offer.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity_dtl_task_offer.this, "Login Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("gig_id", id);

                return params;
            }
        };
        MySingleton.getInstance(activity_dtl_task_offer.this).addTorequestquee(stringRequest);
    }


}