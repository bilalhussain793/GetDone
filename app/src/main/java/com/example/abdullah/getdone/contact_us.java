package com.example.abdullah.getdone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class contact_us extends AppCompatActivity {
EditText Name,Email,Subject,Message;
Button send;
String name,email,subject,mess;
    private String URL_REGIST = "http://192.168.10.13/GetDone/contact.php";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Name=findViewById(R.id.input11Text);

        Email=findViewById(R.id.ema);

        Subject=findViewById(R.id.subj);

        Message=findViewById(R.id.msg);


        send=findViewById(R.id.btn_off);
        builder = new AlertDialog.Builder(contact_us.this);
Name.setText(UserDetails.username);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=Name.getText().toString();
                email=Email.getText().toString();
                subject=Subject.getText().toString();
                mess=Message.getText().toString();

                if(subject.length()==0){
                    Subject.setError("Empty!");
                }else {
                    if (email.length() == 0) {
                        Email.setError("Empty!");
                    } else {
                        if (mess.length() == 0) {
                            Message.setError("Empty");
                        } else {
                            Contact(name, email, subject, mess);






                           // startActivity(new Intent(contact_us.this,Navbar.class));



                        }
                    }


                }

            }
        });





        }
    private void Contact(final String name, final String email, final String subject, final String mess) {
        int value=1;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Detail");
                        builder.setMessage("Message Saved Successfully");
                        builder.setPositiveButton("User SignUp Successfully", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Name.setText("");
                                Subject.setText("");
                                // etType.setText("");
                                Message.setText("");
                                Email.setText("");


                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contact_us.this, "Submit Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("Name",name);
                params.put("Email",email);
                params.put("Message", mess);
                params.put("Subject",subject);


                return params;
            }
        };
        MySingleton.getInstance(contact_us.this).addTorequestquee(stringRequest);
    }
}
