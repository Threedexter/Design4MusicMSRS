package com.example.user.mobilestagereactionsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.mock.Data;
import com.modals.NavigationAppActivity;
import com.modals.Navigator;

import ext.gestureDetection.base.Gesture;


/**
 * Created by fhict on 25/09/2017.
 */

public class ManageRecordingActivity extends NavigationAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.manage_recording_screen);
        super.onCreate(savedInstanceState);

        ListView lv = (ListView) findViewById(R.id.lvRecordings);

        ArrayAdapter<Gesture> ag = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Data.getGestures());
        lv.setAdapter(ag);

        Button add = (Button) findViewById(R.id.btAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigator.moveView(ManageRecordingActivity.this, Record.class);
            }
        });
    }
}
