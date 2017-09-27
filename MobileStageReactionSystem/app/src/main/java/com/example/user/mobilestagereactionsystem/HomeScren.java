package com.example.user.mobilestagereactionsystem;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.back.IAlertContent;
import com.mock.Data;
import com.modals.NavigationAppActivity;

public class HomeScren extends NavigationAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home_screen);
        super.onCreate(savedInstanceState);
        this.setTitle("Home");

        ListView lv = (ListView) findViewById(R.id.lvEffects);

        ArrayAdapter<IAlertContent> ag = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Data.getEffects());
        lv.setAdapter(ag);

        final Button ba = (Button)findViewById(R.id.btAuto);
        final Button bc = (Button)findViewById(R.id.btCrowd);
        final Button bd = (Button)findViewById(R.id.btDJ);

        ba.getBackground().setColorFilter(0xff99cc00, PorterDuff.Mode.MULTIPLY);

        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ba.getBackground().setColorFilter(0xff99cc00, PorterDuff.Mode.MULTIPLY);
                bc.getBackground().clearColorFilter();
                bd.getBackground().clearColorFilter();
            }
        });

        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bc.getBackground().setColorFilter(0xff99cc00, PorterDuff.Mode.MULTIPLY);
                ba.getBackground().clearColorFilter();
                bd.getBackground().clearColorFilter();
            }
        });

        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.getBackground().setColorFilter(0xff99cc00, PorterDuff.Mode.MULTIPLY);
                bc.getBackground().clearColorFilter();
                ba.getBackground().clearColorFilter();
            }
        });
    }
}
