package com.example.user.mobilestagereactionsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.back.Debugger;
import com.back.MemoryGestureHolder;
import com.modals.NavigationAppActivity;


import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.Gesture;
import ext.gestureDetection.patterns.Matcher;

public class Match extends NavigationAppActivity {

    ImageButton RecordButton;
    Button SetButton;

    private Matcher rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_record);
        super.onCreate(savedInstanceState);
        RecordButton = (ImageButton) findViewById(R.id.ibRecord);
        RecordButton.setOnClickListener(imgButtonHandler);

        RecordButton.setTag(R.drawable.record);
        SetButton= (Button)findViewById(R.id.btnSet);

        rec = new Matcher(getApplicationContext());
        rec.addGestures(MemoryGestureHolder.getGestures());

        Debugger.addDelegate(new Delegate<String>() {
            @Override
            public void invoke(String obj) {
                ((TextView)findViewById(R.id.tvTitle)).setText(obj);
            }
        });

        rec.getRegister().attachDelegate(new Delegate<Gesture>() {
            @Override
            public void invoke(Gesture obj) {

                SetButton.setText("Found gesture: " + obj.getName());
                RecordButton.setVisibility(View.INVISIBLE);
                SetButton.setVisibility(View.VISIBLE);
                rec.stop();
            }
        });

        SetButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordButton.setVisibility(View.VISIBLE);
                SetButton.setVisibility(View.INVISIBLE);
                RecordButton.setBackgroundResource(R.drawable.record);
                RecordButton.setTag(R.drawable.record);
            }
        });

    }

    View.OnClickListener imgButtonHandler = new View.OnClickListener() {
        public void onClick(View v) {
            int resId = (Integer) RecordButton.getTag();
            if (resId == R.drawable.record) {
                rec.start();

                RecordButton.setBackgroundResource(R.drawable.stop);
                RecordButton.setTag(R.drawable.stop);
            }
        }
    };


}
