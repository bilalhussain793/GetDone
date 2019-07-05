package com.example.abdullah.getdone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class sign_up extends AppCompatActivity {
    private Spinner sp_day,sp_month;
    private EditText et_phone,et_username,et_email,et_password,et_confirmpass;
    ArrayAdapter adapter_day,adapter_month;
    Button reg_btn;
    String nm,ph,em,pass,cnfm_pass;
    ProgressDialog pd;

    FirebaseUserAdapter firebaseUserAdapter =new FirebaseUserAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_username=findViewById(R.id.et_username1);
        et_email=findViewById(R.id.et_email1);
        et_phone = findViewById(R.id.et_phn_number1);
        et_password=findViewById(R.id.et_pass1);
        et_confirmpass=findViewById(R.id.et_cp);

        reg_btn=findViewById(R.id.btn_sign_up1);


        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addusermethod();
            }
        });

    }

    private void addusermethod(){


        nm=et_username.getText().toString();
        em=et_email.getText().toString();
        ph=et_phone.getText().toString();
        pass=et_password.getText().toString();
        cnfm_pass=et_confirmpass.getText().toString();

        if(nm.length()==0){
            et_username.setError("Empty!");
        }else {
            if(em.length()==0){
                et_email.setError("Empty!");
            }else{
                if (pass.length()==0){
                    et_password.setError("Empty");
                }else {
                    if(cnfm_pass.length()==0){
                        et_confirmpass.setError("Empty");
                    }else {
                        if(ph.length()==0){
                            et_phone.setError("Empty");
                        }else{
                            if(em.contains("@")&&em.contains(".com")){
                                if(pass.equals(cnfm_pass)){
// Write a message to the database
                                    pd=new ProgressDialog(sign_up.this);
                                    pd.setTitle("Loading....");
                                    pd.show();

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("users/"+nm);

                                    myRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // This method is called once with the initial value and again
                                            // whenever data at this location is updated.
                                            String value = dataSnapshot.child("Email").getValue(String.class);
                                            String value2 = dataSnapshot.child("Name").getValue(String.class);
                                            if(ph.equals(value2) ){

                                                et_phone.setError("User is already Registered");
                                                // Toast.makeText(Register.this, "Phone is already Registered", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                if(em.equals(value)){
                                                    et_email.setError("Email is already in use.");
                                                    //   Toast.makeText(Register.this, "Email is already in use.", Toast.LENGTH_SHORT).show();
                                                }else {

                                                    firebaseUserAdapter.adduser(nm,em,ph,pass);
                                                    startActivity(new Intent(sign_up.this,Login.class));
                                                    Toast.makeText(sign_up.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                    pd.dismiss();


                                                }
                                            }
                                        }

                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w("TAG", "Failed to read value.", error.toException());
                                        }
                                    });



                                }else {

                                    et_password.setError("Not Matched");
                                    et_confirmpass.setError("Not Matched");

                                }
                            }else {
                                et_email.setError("Email is not Correct");
                                Toast.makeText(this, "Email is not correct", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            }
        }
    }
}
