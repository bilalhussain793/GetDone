package com.example.abdullah.getdone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class update_profile extends AppCompatActivity {

    Button click;
    EditText username,email,phone,password,confirmpassword,cnic;
    private String URL_REGIST = UserDetails.Url+"update_profile.php";
    AlertDialog.Builder builder;
    String nm =UserDetails.username;
    String Name,Email,Phone,Pass,Confirm,Cnic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        username=findViewById(R.id.user);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phn);
        password=findViewById(R.id.pass);
        confirmpassword=findViewById(R.id.cpass);
        cnic=findViewById(R.id.cnic);

        click=findViewById(R.id.update);
        username.setText(nm);





//        SharedPreferences prefs = getSharedPreferences("LOGIN", MODE_PRIVATE);
//        userphone = prefs.getString("phone", null);
//
//        String a=prefs.getString("phone",null);


        /*String a=UserDetails.username;

        Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();*/
        final Spinner spinner =  findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Select type");
        categories.add("Buyer");
        categories.add("Seller");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
















        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name=username.getText().toString();
                Email=email.getText().toString();
                Phone=phone.getText().toString();
                Pass=password.getText().toString();
                Confirm=confirmpassword.getText().toString();
                Cnic=cnic.getText().toString();
                String type = null;

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
                    Toast.makeText(update_profile.this,"Please Select Type" ,Toast.LENGTH_LONG).show();


                }





                if(Name.length()==0){
                    username.setError("Empty!");
                }
                if(Email.length()==0)
                {
                    email.setError("Empty");
                }
                if(Phone.length()==0)
                {
                    phone.setError("Empty");
                }
                if(Pass.length()==0)
                {
                    password.setError("Empty");
                }
                if(Confirm.length()==0)
                {
                    confirmpassword.setError("Empty");
                }

                if(Email.contains("@")&&Email.contains(".com")) {
                    if (Pass.equals(Confirm)) {
                        int value = Integer.parseInt(type);

                        UserUpdate(Name, Email, type, Pass,Cnic,Phone);








                        Toast.makeText(update_profile.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(update_profile.this,Navbar.class));



                    }
                    else
                    {
                        Toast.makeText(update_profile.this, "Password Dont Match", Toast.LENGTH_SHORT).show();


                    }

                }
                else
                {
                    Toast.makeText(update_profile.this, "Invalid Email", Toast.LENGTH_SHORT).show();

                }



            }
        });




    }
    private void UserUpdate(final String name, final String email1, final String type, final String password1, final String cnic1 ,final String phone1) {
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

                                username.setText("");
                                email.setText("");
                                // etType.setText("");
                                password.setText("");
                                phone.setText("");
                                cnic.setText("");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(update_profile.this, "SignUp Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("name",Name);
                params.put("email",Email);
                params.put("type", type);
                params.put("password",Pass);
                params.put("cnic",Cnic);
                params.put("phone",Phone);

                return params;
            }
        };
        MySingleton.getInstance(update_profile.this).addTorequestquee(stringRequest);
    }


    public static class show_category extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_category);
        }
    }
}
