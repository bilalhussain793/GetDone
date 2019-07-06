package com.example.abdullah.getdone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=Name.getText().toString();
                email=Email.getText().toString();
                subject=Subject.getText().toString();
                mess=message.getText().toString();



            }
        });





    }
}
