package com.example.user.mobilestagereactionsystem;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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
    }
}
