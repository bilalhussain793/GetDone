package com.example.abdullah.getdone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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



                        //Write code here




                    }
                }



            }
        });




    }
}
