package com.example.user.mobilestagereactionsystem;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.back.MemoryGestureHolder;
import com.modals.NavigationAppActivity;

import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.Gesture;
import ext.gestureDetection.patterns.Matcher;

public class Match extends NavigationAppActivity {
    private ImageButton RecordButton;
    private Matcher rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.matching_screen);
        super.onCreate(savedInstanceState);
        this.setTitle("Match Gestures");
        RecordButton = (ImageButton) findViewById(R.id.btMatch);
        RecordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!rec.isRecording())
                    rec.start();
                RecordButton.setActivated(true);
            }
        });

        rec = new Matcher(getApplicationContext());
        rec.addGestures(MemoryGestureHolder.getGestures());

        rec.getRegister().attachDelegate(new Delegate<Gesture>() {
            @Override
            public void invoke(Gesture obj) {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.dl), "Found gesture: " + obj.getName(), Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    @Override
    protected void onPause() {
        rec.stop();
        RecordButton.setActivated(false);
        super.onPause();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        rec.stop();
        RecordButton.setActivated(false);
        return super.onOptionsItemSelected(item);
    }

}
