package com.example.user.mobilestagereactionsystem;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.back.MemoryGestureHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.modals.Navigator;

import java.io.IOException;

import ext.dataConversion.JSONConverter;
import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.Gesture;
import ext.gestureDetection.patterns.Matcher;
import ext.gestureDetection.patterns.Recorder;

public class Match extends AppCompatActivity {

    ImageButton RecordButton;
    Button SetButton;

    private Matcher rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        RecordButton = (ImageButton) findViewById(R.id.ibRecord);
        RecordButton.setOnClickListener(imgButtonHandler);

        RecordButton.setTag(R.drawable.record);
        SetButton= (Button)findViewById(R.id.btnSet);

        rec = new Matcher(getApplicationContext());

        String test = "{\"movement\":[{\"x\":0.34476504,\"y\":2.9975405,\"z\":8.118738},{\"x\":0.961272,\"y\":2.6384103,\"z\":9.009381},{\"x\":0.8726865,\"y\":3.2501287,\"z\":9.51336},{\"x\":0.9636662,\"y\":4.4759603,\"z\":7.6889787},{\"x\":0.8403648,\"y\":5.675455,\"z\":8.371326},{\"x\":0.19991584,\"y\":6.999449,\"z\":6.1219735},{\"x\":0.35673606,\"y\":8.123527,\"z\":4.935647},{\"x\":0.62249243,\"y\":8.941146,\"z\":4.5166616},{\"x\":0.62608373,\"y\":9.494206,\"z\":1.3156139},{\"x\":0.5255273,\"y\":9.194932,\"z\":1.7142484},{\"x\":0.4141969,\"y\":9.190144,\"z\":2.3918076},{\"x\":0.7015011,\"y\":8.540117,\"z\":3.6391866},{\"x\":0.24540567,\"y\":7.740454,\"z\":5.795165},{\"x\":0.33518824,\"y\":6.2249246,\"z\":5.831078},{\"x\":0.62129533,\"y\":4.050989,\"z\":8.033744}],\"name\":\"TEST_GESTURE_1\",\"tolerance\":2.5}";

        try {
            rec.addGesture((Gesture)JSONConverter.convertToClass(test, Gesture.class));
        } catch (IOException e) {
            Log.d("\t\t\tGESTURE ERROR: ", e.getMessage());
        }

        rec.addGestures(MemoryGestureHolder.getGestures());

        rec.getRegister().attachDelegate(new Delegate<Gesture>() {
            @Override
            public void invoke(Gesture obj) {

                SetButton.setText("Found gesture: " + obj.getName());
                RecordButton.setVisibility(View.INVISIBLE);
                SetButton.setVisibility(View.VISIBLE);
                rec.stop();
            }
        });

        final Activity x = this;

        SetButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordButton.setVisibility(View.VISIBLE);
                SetButton.setVisibility(View.INVISIBLE);
                RecordButton.setBackgroundResource(R.drawable.record);
                RecordButton.setTag(R.drawable.record);
                Navigator.moveView(x, HomeScren.class);
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
