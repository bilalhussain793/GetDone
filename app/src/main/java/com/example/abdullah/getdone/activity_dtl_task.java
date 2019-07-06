package com.example.abdullah.getdone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class activity_dtl_task extends AppCompatActivity {

    TextView name,type,status,detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtl_task);
        name=findViewById(R.id.nam);
        type=findViewById(R.id.typ);
        status=findViewById(R.id.stat);
        detail=findViewById(R.id.dtail);
    }
}
