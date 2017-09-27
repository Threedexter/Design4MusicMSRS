package com.modals;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.user.mobilestagereactionsystem.EffectsActivity;
import com.example.user.mobilestagereactionsystem.HomeScren;
import com.example.user.mobilestagereactionsystem.ManageRecordingActivity;
import com.example.user.mobilestagereactionsystem.Match;
import com.example.user.mobilestagereactionsystem.PopScreenSummon;
import com.example.user.mobilestagereactionsystem.R;
import com.example.user.mobilestagereactionsystem.RecordingActivity;

import ext.gestureDetection.patterns.Recorder;

/**
 * Created by Rowan on 26/09/17.
 */

public abstract class NavigationAppActivity extends AppCompatActivity {
    private DrawerLayout dl;
    protected View thisView;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String whois = this.getClass().getName();

        if (!Navigator.wasVisited(whois)) {
            // show popup
            startActivity(new Intent(NavigationAppActivity.this, PopScreenSummon.class));
        }

        Navigator.addScreenVisited(whois);

        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                                                       @Override
                                                       public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                           int id = item.getItemId();

                                                           if (id == R.id.homeItem) {
                                                               PopScreenSummon.setResource(R.drawable.homescreen);
                                                               Navigator.moveView(NavigationAppActivity.this, HomeScren.class);
                                                           }
                                                           else if(id == R.id.manageRecordingItem){
                                                               PopScreenSummon.setResource(R.drawable.manage_recordings);
                                                               Navigator.moveView(NavigationAppActivity.this, ManageRecordingActivity.class);
                                                           }
                                                           else if(id == R.id.recordingItem){
                                                               PopScreenSummon.setResource(R.drawable.record_tut);
                                                               Navigator.moveView(NavigationAppActivity.this, RecordingActivity.class);
                                                           }
                                                           else if (id == R.id.effectsItem){
                                                               PopScreenSummon.setResource(R.drawable.effects);
                                                               Navigator.moveView(NavigationAppActivity.this, EffectsActivity.class);
                                                           }
                                                           else if (id == R.id.matchItem){
                                                               PopScreenSummon.setResource(R.drawable.test_gestures);
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

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_scren, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setContentView(View view) {
        thisView = view;
        super.setContentView(view);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_help) {
            startActivity(new Intent(NavigationAppActivity.this, PopScreenSummon.class));
        }
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Recorder.stopAll();
        Recorder.wipeAll();
        super.onBackPressed();
    }
}
