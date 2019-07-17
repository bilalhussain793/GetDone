package com.example.abdullah.getdone;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

import com.android.volley.AuthFailureError;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sign_up extends AppCompatActivity {

    private EditText etUser_Name, etEmail, etPassword, etConfirm_password, etcnic,etphone;
    private Button btnSignUp;
    // private Spinner spinnerType;#10.0.02.2
    private String URL_REGIST = UserDetails.Url+"register.php";
    AlertDialog.Builder builder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUser_Name = findViewById(R.id.et_username1);
        etEmail = findViewById(R.id.et_email1);
        etcnic = findViewById(R.id.et_cnic);
        etphone = findViewById(R.id.et_phone);




        etPassword = findViewById(R.id.et_pass1);
        btnSignUp = findViewById(R.id.btn_sign_up1);
        builder = new AlertDialog.Builder(sign_up.this);
//        //Spinner display data


        final Spinner spinner =  findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Select type");
        categories.add("Post a task");
        categories.add("Earn Money");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etUser_Name.getText().toString();
                String email = etEmail.getText().toString();

                String type = null;
                String password = etPassword.getText().toString();
                String cnic= etcnic.getText().toString();
                String phone= etphone.getText().toString();

                String typeabd = spinner.getSelectedItem().toString();

                if(typeabd=="Seller")
                {
                    type="1";

                }
                else if(typeabd=="Buyer")
                {
                    type="2";

                }
                else {
                    Toast.makeText(getApplicationContext(),"Please Select Type" ,Toast.LENGTH_LONG).show();
//

                }

                //
                //String confirm_password = etConfirm_password.getText().toString();

                int value = Integer.parseInt(type);

                if(!name.isEmpty() || !email.isEmpty() ||  !password.isEmpty() ||  !cnic.isEmpty() ||  !phone.isEmpty() ){
                    UserSignUP(name, email, type, password,cnic,phone);
                    startActivity(new Intent(sign_up.this,Login.class));
                }else{
                    etUser_Name.setError("Please insert UserName");
                    etEmail.setError("Please insert Email");
                    //etType.setError("Please insert Type(Seller/Buyer)");
                    etPassword.setError("Please insert Password");
                    etcnic.setError("Please insert cnic");

                }
            }
        });





    }
    private void UserSignUP(final String name, final String email, final String type, final String password, final String cnic ,final String phone) {
        int value=1;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("SignUp Successfully");
                        builder.setPositiveButton("User SignUp Successfully", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                etUser_Name.setText("");
                                etEmail.setText("");
                                // etType.setText("");
                                etPassword.setText("");
                                etphone.setText("");
                                etcnic.setText("");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(sign_up.this, "SignUp Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("name",name);
                params.put("email",email);
                params.put("type", type);
                params.put("password",password);
                params.put("cnic",cnic);
                params.put("phone",phone);

                return params;
            }
        };
        MySingleton.getInstance(sign_up.this).addTorequestquee(stringRequest);
    }

    /*private void addusermethod(){


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
    }*/
}
