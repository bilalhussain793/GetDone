package com.example.abdullah.getdone;

import android.content.DialogInterface;
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

import java.util.HashMap;
import java.util.Map;

public class update_posted extends AppCompatActivity {
    private EditText budget,title;
    Button click;
    private String URL_REGIST = UserDetails.Url+ "update_posted.php";
    AlertDialog.Builder builder;


    String update,Title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_posted);
        builder = new AlertDialog.Builder(update_posted.this);

        budget=findViewById(R.id.bud);
        title=findViewById(R.id.tit);

        click=findViewById(R.id.update);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update=budget.getText().toString();
                Title=title.getText().toString();

                if(!update.isEmpty()  ||  !Title.isEmpty())
                {
                    budget_update(update,Title);





                }
                else {
                    budget.setError("Please Insert Amount");

                }





            }
        });


    }
    private void budget_update(final String Amount,final String Title) {
        int value=1;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Details");
                        builder.setMessage("Updated Successfully");
                        builder.setPositiveButton("User SignUp Successfully", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                budget.setError("Please Insert Amount");
                                title.setError("Please Insert Title");


                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(update_posted.this, "Updation Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("Budget",update);
                params.put("Title",Title);


                return params;
            }
        };
        MySingleton.getInstance(update_posted.this).addTorequestquee(stringRequest);
    }
}
