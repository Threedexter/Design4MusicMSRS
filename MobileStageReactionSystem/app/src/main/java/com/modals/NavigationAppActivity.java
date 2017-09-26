package com.modals;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.back.Debugger;
import com.example.user.mobilestagereactionsystem.EffectsActivity;
import com.example.user.mobilestagereactionsystem.HomeScren;
import com.example.user.mobilestagereactionsystem.ManageRecordingActivity;
import com.example.user.mobilestagereactionsystem.Match;
import com.example.user.mobilestagereactionsystem.R;
import com.example.user.mobilestagereactionsystem.RecordingActivity;

import ext.gestureDetection.patterns.Recorder;

/**
 * Created by Rowan on 26/09/17.
 */

public abstract class NavigationAppActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                                                       @Override
                                                       public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                           int id = item.getItemId();

                                                           if (id == R.id.homeItem) {
                                                               Navigator.moveView(NavigationAppActivity.this, HomeScren.class);
                                                           }
                                                           else if(id == R.id.manageRecordingItem){
                                                               Navigator.moveView(NavigationAppActivity.this, ManageRecordingActivity.class);
                                                           }
                                                           else if(id == R.id.recordingItem){
                                                               Navigator.moveView(NavigationAppActivity.this, RecordingActivity.class);
                                                           }
                                                           else if (id == R.id.effectsItem){
                                                               Navigator.moveView(NavigationAppActivity.this, EffectsActivity.class);
                                                           }
                                                           else if (id == R.id.matchItem){
                                                               Navigator.moveView(NavigationAppActivity.this, Match.class);
                                                           }

                                                           return false;
                                                       }
                                                   }
        );


        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Recorder.stopAll();
        Recorder.wipeAll();
        super.onBackPressed();
    }
}
