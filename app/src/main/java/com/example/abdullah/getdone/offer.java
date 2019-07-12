package com.example.abdullah.getdone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class offer extends AppCompatActivity {
EditText price,why;
CheckBox agree;
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
        agree=findViewById(R.id.checkBox);

        a=34;


        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retail=price.getText().toString();
                reson=why.getText().toString();




            }
        });

    }
}
