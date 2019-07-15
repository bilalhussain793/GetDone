package com.example.abdullah.getdone;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class Navbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);

        SharedPreferences prefs = getSharedPreferences("LOGIN", MODE_PRIVATE);

        String na=prefs.getString("nm",null);

        if(na==null)
        {
            startActivity(new Intent(Navbar.this,Login.class
            ));
        }
        else
        {
            UserDetails.username = na;
        }



        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        HomeFragment blankFragment=new HomeFragment();
        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frame_layout, blankFragment);
        fragmentTransaction.commit(); // save the changes

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                String menu=""+menuItem;

                if(menu.equals("Earn Money")){

                    HomeFragment blankFragment=new HomeFragment();
                    // create a FragmentManager
                    FragmentManager fm = getFragmentManager();
                    // create a FragmentTransaction to begin the transaction and replace the Fragment
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    // replace the FrameLayout with new Fragment
                    fragmentTransaction.replace(R.id.frame_layout, blankFragment);
                    fragmentTransaction.commit(); // save the changes

                }if(menu.equals("My Tasks")){


                    MyTaskFragment mtFragment=new MyTaskFragment();
                    // create a FragmentManager
                    FragmentManager fm = getFragmentManager();
                    // create a FragmentTransaction to begin the transaction and replace the Fragment
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    // replace the FrameLayout with new Fragment
                    fragmentTransaction.replace(R.id.frame_layout, mtFragment);
                    fragmentTransaction.commit(); // save the changes

                }if(menu.equals("Post a task")){
                    PostTaskFragment ptFragment=new PostTaskFragment();
                    // create a FragmentManager
                    FragmentManager fm = getFragmentManager();
                    // create a FragmentTransaction to begin the transaction and replace the Fragment
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    // replace the FrameLayout with new Fragment
                    fragmentTransaction.replace(R.id.frame_layout, ptFragment);
                    fragmentTransaction.commit(); // save the changes

                }if(menu.equals("Messages")){

                    MessageFragment mstFragment=new MessageFragment();
                    // create a FragmentManager
                    FragmentManager fm = getFragmentManager();
                    // create a FragmentTransaction to begin the transaction and replace the Fragment
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    // replace the FrameLayout with new Fragment
                    fragmentTransaction.replace(R.id.frame_layout, mstFragment);
                    fragmentTransaction.commit(); // save the changes

                }if(menu.equals("More")){

                    MoreFragment mFragment=new MoreFragment();
                    // create a FragmentManager
                    FragmentManager fm = getFragmentManager();
                    // create a FragmentTransaction to begin the transaction and replace the Fragment
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    // replace the FrameLayout with new Fragment
                    fragmentTransaction.replace(R.id.frame_layout, mFragment);
                    fragmentTransaction.commit(); // save the changes
                }


                return true;
            }
        });



    }
}
