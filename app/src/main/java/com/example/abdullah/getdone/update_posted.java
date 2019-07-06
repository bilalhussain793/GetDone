package com.example.abdullah.getdone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class update_posted extends AppCompatActivity {
EditText budget;
    Button click;

    String update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_posted);

        budget=findViewById(R.id.bud);
        click=findViewById(R.id.update);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update=budget.getText().toString();


            }
        });


    }
}
