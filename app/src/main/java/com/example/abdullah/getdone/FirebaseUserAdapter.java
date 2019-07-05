package com.example.abdullah.getdone;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUserAdapter {

    public String adduser(String name,String email,String phone,String password){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users/"+name);

        myRef.child("Name").setValue(name);
        myRef.child("Email").setValue(email);
        myRef.child("Phone").setValue(phone);
        myRef.child("Password").setValue(password);


        return "Added";

    }
    public String addpoints(String phone,int points){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users/"+phone);

        myRef.child("Points").setValue(points);

        return "";
    }

}
