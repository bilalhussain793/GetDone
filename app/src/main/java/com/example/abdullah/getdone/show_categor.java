package com.example.abdullah.getdone;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class show_categor extends AppCompatActivity {
    private static final String URL_GETDATA = "http://192.168.0.105/GetDone/get_post_category.php";

    AlertDialog.Builder builder;
    //the recyclerview
    RecyclerView recyclerView;
    List<Get_post> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categor);
        final String category = getIntent().getStringExtra("category");
        builder = new AlertDialog.Builder(show_categor.this);
        UserLogin(category);
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();







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
                            Toast.makeText(show_categor.this,response ,Toast.LENGTH_LONG).show();

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
                            PostAdapter adapter = new PostAdapter(show_categor.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(show_categor.this, "Login Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("category", category);

                return params;
            }
        };
        MySingleton.getInstance(show_categor.this).addTorequestquee(stringRequest);
    }

}
