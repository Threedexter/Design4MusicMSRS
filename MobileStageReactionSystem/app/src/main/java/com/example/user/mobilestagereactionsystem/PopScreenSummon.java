package com.example.user.mobilestagereactionsystem;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.modals.Navigator;

/**
 * Created by Rowan on 27/09/17.
 */

public class PopScreenSummon extends Activity {

    private static int resource = R.drawable.homescreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.r_home_popwindow);


        Button btn = (Button) findViewById(R.id.btnConfirm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn = (Button) findViewById(R.id.btnIgnore);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigator.ignoreHelp();
                onBackPressed();
            }
        });

        ImageView iv = (ImageView) findViewById(R.id.imgTutorial);
        iv.setImageResource(resource);
    }

    public static void setResource(int r) {
        resource = r;
    }


}
