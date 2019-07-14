package com.example.abdullah.getdone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class activity_dtl_task extends AppCompatActivity {

    TextView name,type,status,location,budget,last_date,no_persons,type_of_task,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtl_task);
        name=findViewById(R.id.tvtitle);
        type=findViewById(R.id.typ);
        status=findViewById(R.id.stat);
        location=findViewById(R.id.location);
        budget=findViewById(R.id.budget);
        last_date=findViewById(R.id.lstdate);
        no_persons=findViewById(R.id.No_of_persons);
        type_of_task=findViewById(R.id.cat);
        desc=findViewById(R.id.dsc);





        final String id= getIntent().getStringExtra("id");
        final  String Name= getIntent().getStringExtra("Name");
        final String Desc= getIntent().getStringExtra("Desc");
        final String Last_Date= getIntent().getStringExtra("Last Date");
        final String Date= getIntent().getStringExtra("Date");
        final String Status= getIntent().getStringExtra("Status");
        final String Location= getIntent().getStringExtra("Location");
        final String Type= getIntent().getStringExtra("Type");
        final String Type_of_Task= getIntent().getStringExtra("Type of Task");
        final String Budget= getIntent().getStringExtra("Budget");
        final String No_Of_Persons= getIntent().getStringExtra("No Of Persons");

        name.setText(Name);
        type.setText(Type);
        status.setText(Status);
        location.setText(Location);
        budget.setText("Rs."+Budget);
        last_date.setText(Last_Date);
        no_persons.setText(No_Of_Persons);
        type_of_task.setText(Type_of_Task);
        desc.setText(Desc);



    }
}
