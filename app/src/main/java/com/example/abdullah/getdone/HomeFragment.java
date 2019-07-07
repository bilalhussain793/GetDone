package com.example.abdullah.getdone;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class HomeFragment extends Fragment {

    View view;
    private Fragment baseContext;
    private int contentView;
    ListView lv;
    ArrayList<String> desc=new ArrayList<String>();
    ArrayList<String> prc=new ArrayList<String>();
    ArrayList<String> ofr=new ArrayList<String>();
    ArrayList<String> loc=new ArrayList<String>();
    DatabaseReference databaseReference;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_home, container, false);


        databaseReference= FirebaseDatabase.getInstance().getReference("posttask");

        lv=view.findViewById(R.id.recylcerView);

        desc.add("Clean my house");
        prc.add("Rs. 3832");
        ofr.add("offers 9");
        loc.add("lahore");

        desc.add("Need a tutor");
        prc.add("Rs. 5002");
        ofr.add("offers 5");
        loc.add("lahore");

        desc.add("need a writer 500 rs per page");
        prc.add("Rs. 5000");
        ofr.add("10");
        loc.add("lahore");

        desc.add("I want a co-worker");
        prc.add("Rs. 50000");
        ofr.add("offers 4");
        loc.add("lahore");
//
        final ListAdapt adapter=new ListAdapt(getActivity(),desc,prc,ofr,loc);

        lv.setAdapter(adapter);

//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                String value =dataSnapshot.getValue(POST.class).toString();
//
//                desc.add(value);
//                prc.add("3832");
//                ofr.add("ahmas");
//                loc.add("lahore");
//               // adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        return view;


    }
//    public void getdata(){
//        String url = "https://getdone-2c10b.firebaseio.com/posttask.json";
//
//
//        StringRequest request = new StringRequest(Request.Meth/od.GET, url, new Response.Listener<String>(){
//            @Override
//            public void onResponse(String s) {
//                if(s.equals("null")){
//                    Toast.makeText(getActivity(), "no data", Toast.LENGTH_LONG).show();
//                }
//                else{
//                    Toast.makeText(getActivity(), ""+s, Toast.LENGTH_SHORT).show();
//                    try {
//                        JSONArray obj = new JSONArray(s);
//                        ArrayList<String> arr=new ArrayList<String>();
//                        prc.add(obj.getString(1));
//
////
////                        if(!obj.has(user)){
////                            Toast.makeText(Login.this, "user not found", Toast.LENGTH_LONG).show();
////                        }
////                        else if(obj.getJSONObject(user).getString("password").equals(pass)){
////                            UserDetails.username = user;
////                            UserDetails.password = pass;
////                            startActivity(new Intent(Login.this, Users.class));
////                        }
////                        else {
////                            Toast.makeText(Login.this, "incorrect password", Toast.LENGTH_LONG).show();
////                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//            }
//        },new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                System.out.println("" + volleyError);
//                Toast.makeText(getActivity(), "Volley Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        RequestQueue rQueue = Volley.newRequestQueue(getActivity());
//        rQueue.add(request);
//    }

}