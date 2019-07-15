package com.example.abdullah.getdone;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class offer extends AppCompatActivity {
EditText price,why;
CheckBox agree;
    private static String URL_LOGIN = UserDetails.Url+"offer.php";
    AlertDialog.Builder builder;
Button offer;

int a;

String retail,reson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        price=findViewById(R.id.amount);
        why=findViewById(R.id.task);
        offer=findViewById(R.id.btn_off);
        builder = new AlertDialog.Builder(offer.this);
        final String id= getIntent().getStringExtra("id");
       final  String username = UserDetails.username;


        a=34;


        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retail=price.getText().toString();
                reson=why.getText().toString();
                if(!retail.isEmpty() || !reson.isEmpty()){
                    UserOffer(retail,reson,id,username);
                }else{
                    price.setError("Please insert Amount");
                    why.setError("Please insert Description");
                }





            }
        });

    }
    private void UserOffer(final String retail, final String reson,final String id,final String username) {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        builder.setTitle("Server Response");
                        builder.setMessage("Response:" + response);
                        builder.setPositiveButton("Submitted Successfully", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                price.setText("");
                                why.setText("");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(offer.this, "submission Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("retail", retail);
                params.put("reason", reson);
                params.put("id",id);
                params.put("username",username);

                return params;
            }
        };
        MySingleton.getInstance(offer.this).addTorequestquee(stringRequest);
    }
}
