package com.example.abdullah.getdone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class update_profile extends AppCompatActivity {

    Button click;
    EditText username,email,phone,password,confirmpassword;

    String name,Email,Phone,Pass,Confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        username=findViewById(R.id.user);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phn);
        password=findViewById(R.id.pass);
        confirmpassword=findViewById(R.id.cpass);

        click=findViewById(R.id.update);
        FirebaseDatabase database = FirebaseDatabase.getInstance();


//        SharedPreferences prefs = getSharedPreferences("LOGIN", MODE_PRIVATE);
//        userphone = prefs.getString("phone", null);
//
//        String a=prefs.getString("phone",null);


        String a=UserDetails.username;
        Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();



        DatabaseReference myRef = database.getReference("users/"+a);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String phn = dataSnapshot.child("Phone").getValue(String.class);
                String nam = dataSnapshot.child("Name").getValue(String.class);

                String pass2 = dataSnapshot.child("Password").getValue(String.class);
                String ema = dataSnapshot.child("Email").getValue(String.class);

                username.setText(nam);
                email.setText(ema);
                phone.setText(phn);
                password.setText(pass2);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });




        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=username.getText().toString();
                Email=email.getText().toString();
                Phone=phone.getText().toString();
                Pass=password.getText().toString();
                Confirm=confirmpassword.getText().toString();



                if(username.length()==0){
                    username.setError("Empty!");
                }
                if(email.length()==0)
                {
                    email.setError("Empty");
                }
                if(phone.length()==0)
                {
                    phone.setError("Empty");
                }
                if(password.length()==0)
                {
                    password.setError("Empty");
                }
                if(confirmpassword.length()==0)
                {
                    confirmpassword.setError("Empty");
                }

                if(Email.contains("@")&&Email.contains(".com")) {
                    if (Pass.equals(Confirm)) {



                FirebaseUserAdapter firebaseUserAdapter=new FirebaseUserAdapter();

                firebaseUserAdapter.adduser(name,Email,Phone,Pass);

                        Toast.makeText(update_profile.this, "Updated", Toast.LENGTH_SHORT).show();
finish();
startActivity(new Intent(update_profile.this,Navbar.class));



                    }
                }



            }
        });




    }
}
