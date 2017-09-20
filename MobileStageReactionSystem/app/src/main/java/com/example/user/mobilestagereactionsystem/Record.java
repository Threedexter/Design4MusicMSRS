package com.example.user.mobilestagereactionsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

import com.fasterxml.jackson.core.JsonProcessingException;

import ext.dataConversion.JSONConverter;
import ext.gestureDetection.patterns.Recorder;

public class Record extends AppCompatActivity {

    ImageButton RecordButton;
    Button SetButton;

    private Recorder rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        RecordButton = (ImageButton) findViewById(R.id.ibRecord);
        RecordButton.setOnClickListener(imgButtonHandler);

        RecordButton.setTag(R.drawable.record);
        SetButton= (Button)findViewById(R.id.btnSet);

        rec = new Recorder(getApplicationContext());
        SetButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Log.d("\t\t\t", "SENDING");
                    Log.d("\t\t\tGESTURE RESULT: ", JSONConverter.convertToJSON(rec.dumpGesture()));
                } catch (JsonProcessingException e) {
                    Log.d("\t\t\tGESTURE ERROR: ", e.getMessage());
                }
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
            } else {
                rec.stop();

                RecordButton.setVisibility(View.INVISIBLE);
                SetButton.setVisibility(View.VISIBLE);
            }
        }
    };


}
