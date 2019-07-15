package com.example.abdullah.getdone;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private static final String URL_GETDATA = UserDetails.Url +"get_post.php";

    //a list to store all the products
    List<Get_post> productList;

    //the recyclerview
    RecyclerView recyclerView;

    View view;
    private Fragment baseContext;
    private int contentView;
    ListView lv;
    ImageView gardening,cleaning,it,home,other,pnd;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


// Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_home, container, false);



        recyclerView = view.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //initializing the productlist
        productList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
        gardening=view.findViewById(R.id.garden);
        cleaning=view.findViewById(R.id.clean);
        it=view.findViewById(R.id.it);
        home=view.findViewById(R.id.home);
        other=view.findViewById(R.id.other);
        pnd=view.findViewById(R.id.pnd);

        gardening.setOnClickListener(new View.OnClickListener() {//Calling on click listener for Add Button

            public void onClick(View v)//calling the onClick function
            {
                Intent intent = new Intent(v.getContext(), show_categor.class);


                intent.putExtra("category", "10");

                v.getContext().startActivity(intent);


            }
        });
        cleaning.setOnClickListener(new View.OnClickListener() {//Calling on click listener for Add Button

            public void onClick(View v)//calling the onClick function
            {
                Intent intent = new Intent(v.getContext(),show_categor.class);


                intent.putExtra("category", "9");

                v.getContext().startActivity(intent);


            }
        });
        home.setOnClickListener(new View.OnClickListener() {//Calling on click listener for Add Button

            public void onClick(View v)//calling the onClick function
            {
                Intent intent = new Intent(v.getContext(),show_categor.class);


                intent.putExtra("category", "11");

                v.getContext().startActivity(intent);


            }
        });
        it.setOnClickListener(new View.OnClickListener() {//Calling on click listener for Add Button

            public void onClick(View v)//calling the onClick function
            {
                Intent intent = new Intent(v.getContext(),show_categor.class);


                intent.putExtra("category", "12");

                v.getContext().startActivity(intent);


            }
        });
        other.setOnClickListener(new View.OnClickListener() {//Calling on click listener for Add Button

            public void onClick(View v)//calling the onClick function
            {
                Intent intent = new Intent(v.getContext(),show_categor.class);


                intent.putExtra("category", "13");

                v.getContext().startActivity(intent);


            }
        });

        pnd.setOnClickListener(new View.OnClickListener() {//Calling on click listener for Add Button

            public void onClick(View v)//calling the onClick function
            {
                Intent intent = new Intent(v.getContext(),show_categor.class);


                intent.putExtra("category", "8");

                v.getContext().startActivity(intent);


            }
        });





        return view;


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
                                        product.getInt("no_of_persons"),
                                        product.getString("username")

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

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

}