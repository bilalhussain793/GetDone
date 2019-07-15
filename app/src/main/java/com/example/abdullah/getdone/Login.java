package com.example.abdullah.getdone;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {


    Button bt_login;
    EditText et_n,et_ps;
    CheckBox checkBox;
    FirebaseUserAdapter firebaseUserAdapter=new FirebaseUserAdapter();
    TextView reg_btn;
    String Name,Password;
    private String URL_REGIST = UserDetails.Url+ "login.php";
    AlertDialog.Builder builder;

    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_n=findViewById(R.id.et_username2);
        et_ps=findViewById(R.id.et_passw);

        bt_login=findViewById(R.id.btn_sign_in2);
        reg_btn=findViewById(R.id.tv_sign_up2);
        builder = new AlertDialog.Builder(Login.this);


        checkBox=findViewById(R.id.cbx);



     /*   SharedPreferences prefs = getSharedPreferences("LOGIN", MODE_PRIVATE);

        String na=prefs.getString("nm",null);
        UserDetails.username=na;

        if (r == 2) {
            startActivity(new Intent(Login.this, Navbar.class));


        }else{
            Toast.makeText(this, "Login here", Toast.LENGTH_SHORT).show();
        }
*/

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name= et_n.getText().toString();
                Password= et_ps.getText().toString();

                if(!Name.isEmpty() || !Password.isEmpty()  ){
                    Userlogin(Name, Password);
                    SharedPreferences.Editor editor = getSharedPreferences("LOGIN", MODE_PRIVATE).edit();

                    editor.putString("nm",Name);
                    editor.apply();



                }else{
                    et_n.setError("Please insert Name");
                    et_ps.setError("Please insert Password");


                }





                // validlogin(et_n,et_ps);

            }
        });
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,sign_up.class));
            }
        });

    }

    public void validlogin(final EditText NM, final EditText password){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users/"+NM.getText().toString());
        pd=new ProgressDialog(Login.this);
        pd.setTitle("Loading....");
        pd.show();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String nM = dataSnapshot.child("Name").getValue(String.class);
                String pass = dataSnapshot.child("Password").getValue(String.class);

                if(NM.getText().toString().equals(nM)){

                    if(password.getText().toString().equals(pass)){

                        if(checkBox.isEnabled()){
                            SharedPreferences.Editor editor = getSharedPreferences("LOGIN", MODE_PRIVATE).edit();
                            editor.putInt("flg", 2);
                            editor.putString("nm",NM.getText().toString());
                            editor.apply();

                            UserDetails.username=NM.getText().toString();
                            startActivity(new Intent(Login.this,Navbar.class));
                            pd.dismiss();
                        }else{
                            SharedPreferences.Editor editor = getSharedPreferences("LOGIN", MODE_PRIVATE).edit();
                            editor.putString("nm",NM.getText().toString());
                            editor.apply();

                            startActivity(new Intent(Login.this,Navbar.class));
                            pd.dismiss();
                        }

                    }else {

                        password.setError("Wrong Password");
                    }

                }else {
                    NM.setError("Invalid User");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
                NM.setError("Invalid User");
            }
        });

    }

    private void Userlogin(final String name, final String password) {
        int value=1;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Login Successfully");
                        startActivity(new Intent(Login.this,Navbar.class));
                        builder.setPositiveButton("User SignUp Successfully", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                et_n.setText("");
                                et_ps.setText("");
                                // etType.setText("");

                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Login Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("name",Name);
                params.put("password",Password);


                return params;
            }
        };
        MySingleton.getInstance(Login.this).addTorequestquee(stringRequest);
    }
}