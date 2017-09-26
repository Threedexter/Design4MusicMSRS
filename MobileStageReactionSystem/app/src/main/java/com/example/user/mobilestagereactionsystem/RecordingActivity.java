package com.example.user.mobilestagereactionsystem;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.graphics.PorterDuff;
import android.widget.EditText;
import android.widget.ImageButton;

import com.back.MemoryGestureHolder;
import com.mock.Data;
import com.modals.NavigationAppActivity;
import com.modals.Navigator;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ext.gestureDetection.base.Gesture;
import ext.gestureDetection.patterns.Recorder;


/**
 * Created by fhict on 20/09/2017.
 */

public class RecordingActivity extends NavigationAppActivity {

    private Recorder rec;
    private String m_Text = "";




    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_UP:
                    // stop recording
                    rec.stop();
                    // show text + save button

                    AlertDialog.Builder builder = new AlertDialog.Builder(RecordingActivity.this);
                    final EditText input = new EditText(RecordingActivity.this);
                    builder.setTitle("Save recording as...");
                    builder.setView(input);
                    builder.setCancelable(false);

                    builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            m_Text = input.getText().toString();

                            if (m_Text.isEmpty()) {
                                String timeStamp = new SimpleDateFormat("MMdd_HHmmss").format(Calendar.getInstance().getTime());
                                m_Text = "Recording " + timeStamp;

                            }
                            Gesture g = new Gesture(0.35f, rec.dumpGesture(), m_Text);
                            MemoryGestureHolder.addGesture(g);

                            Navigator.createAlertDialog(RecordingActivity.this, "Choose effect", Data.getEffects());
                        }
                    });

                    builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rec.dumpGesture();
                        }
                    });

                    builder.show();



                    //Gesture g = new Gesture(0.35f, rec.dumpGesture(), "DYNAMIC_G_" + MemoryGestureHolder.getGestures().size());
                    //MemoryGestureHolder.addGesture(g);
                    //Navigator.createAlertDialog(this, "Connect effect", Data.getEffects());

                    break;
                case MotionEvent.ACTION_DOWN:
                    rec.start();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.recordings_screen);
        super.onCreate(savedInstanceState);

        rec = new Recorder(getApplicationContext());



        // Capture our button from layout
        ImageButton button = (ImageButton)findViewById(R.id.btRecord);
        // Register the onClick listener with the implementation above
        button.setOnTouchListener(mOnTouchListener);


    }





}
