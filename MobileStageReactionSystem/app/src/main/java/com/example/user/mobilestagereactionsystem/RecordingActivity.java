package com.example.user.mobilestagereactionsystem;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.graphics.PorterDuff;
import android.support.v7.widget.Toolbar;




/**
 * Created by fhict on 20/09/2017.
 */

public class RecordingActivity extends AppCompatActivity  {

     private DrawerLayout dl;
     private ActionBarDrawerToggle abdt;


     private Button btRecord;
     private Button btStop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordings_screen);

        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

                                                       @Override
                                                       public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                         int id = item.getItemId();

                                                           if (id == R.id.homeitem)
                                                           {
                                                               Log.d("MenuItem", "Home is clicked");
                                                           }
                                                           return false;
                                                       }
                                                   }
        );

        // Makes the second button be invisible until someone clicks on record
        btRecord = (Button) findViewById(R.id.btRecord);
        btStop = (Button) findViewById(R.id.btStop);
        btStop.setVisibility(View.GONE);

        // Links the onclick trigger to the left button
        btRecord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (btRecord.getText().toString()) {
                    case "Record": ButtonRecord();
                        break;
                    case "Stop":  ButtonStop();
                        break;
                    case "Clear": ButtonClear();
                        break;
                    case "Continue": ButtonContinue();
                        break;

                }

            }
        });

        // Links the onclick trigger to the right button
        btStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (btStop.getText().toString()) {
                    case "Pause": ButtonPause();
                        break;
                    case "Save":  ButtonSave();
                        break;


                }

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void ButtonRecord()
    {
        btRecord.setText("Stop");
        btRecord.getBackground().setColorFilter(0xffff0000, PorterDuff.Mode.MULTIPLY);
        btStop.setVisibility(View.VISIBLE);
    }

    private void ButtonStop()
    {
        btRecord.setText("Clear");
        btRecord.getBackground().setColorFilter(0xff0000ff, PorterDuff.Mode.MULTIPLY);
        btStop.setText("Save");
    }

    private void ButtonClear()
    {
        btRecord.setText("Record");
        btRecord.getBackground().setColorFilter(0xffcccccc, PorterDuff.Mode.MULTIPLY);
        btStop.setVisibility(View.GONE);
        btStop.setText("Pause");
    }

    private void ButtonSave()
    {
        btRecord.setText("Record");
        btStop.setText("Pause");
        btRecord.getBackground().setColorFilter(0xffcccccc, PorterDuff.Mode.MULTIPLY);
        btStop.setVisibility(View.GONE);
    }

    private void ButtonPause()
    {
        btRecord.setText("Continue");
        btRecord.getBackground().setColorFilter(0xffcccccc, PorterDuff.Mode.MULTIPLY);
        btStop.setVisibility(View.GONE);
    }

    private void ButtonContinue()
    {
        btRecord.setText("Stop");
        btRecord.getBackground().setColorFilter(0xffff0000, PorterDuff.Mode.MULTIPLY);
        btStop.setVisibility(View.VISIBLE);
    }





}
