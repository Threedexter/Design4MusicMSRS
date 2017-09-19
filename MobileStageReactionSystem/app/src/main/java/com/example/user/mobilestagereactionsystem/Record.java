package com.example.user.mobilestagereactionsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

public class Record extends AppCompatActivity {
    ImageButton RecordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        RecordButton= (ImageButton)findViewById(R.id.ibRecord);
        RecordButton.setOnClickListener(imgButtonHandler);

        RecordButton.setTag(R.drawable.record);

    }

    View.OnClickListener imgButtonHandler = new View.OnClickListener() {

        public void onClick(View v) {
            int resId = (Integer) RecordButton.getTag();
            if(resId == R.drawable.record) {
                RecordButton.setBackgroundResource(R.drawable.stop);
                RecordButton.setTag(R.drawable.stop);
            }
            else
            {

                RecordButton.setVisibility(View.INVISIBLE);
               Button setButton = (Button)findViewById(R.id.btnSet);
                setButton.setVisibility(View.VISIBLE);
            }


        }
    };
}
