package com.example.abdullah.getdone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class contact_us extends AppCompatActivity {
EditText Name,Email,Subject,message;
Button send;
String name,email,subject,mess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Name=findViewById(R.id.input11Text);

        Email=findViewById(R.id.ema);

        Subject=findViewById(R.id.subj);

        message=findViewById(R.id.msg);


        send=findViewById(R.id.btn_off);
Name.setText(UserDetails.username);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=Name.getText().toString();
                email=Email.getText().toString();
                subject=Subject.getText().toString();
                mess=message.getText().toString();

                if(subject.length()==0){
                    Subject.setError("Empty!");
                }else {
                    if (email.length() == 0) {
                        Email.setError("Empty!");
                    } else {
                        if (mess.length() == 0) {
                            message.setError("Empty");
                        } else {


                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("Contact/"+name);


                            myRef.child("message").setValue(mess);
                            myRef.child("Subject").setValue(subject);


                            startActivity(new Intent(contact_us.this,Navbar.class));



                        }
                    }


                }

            }
        });





        }
}
