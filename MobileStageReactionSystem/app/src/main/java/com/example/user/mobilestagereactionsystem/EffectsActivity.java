package com.example.user.mobilestagereactionsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.back.IAlertContent;
import com.mock.Data;
import com.modals.NavigationAppActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by fhict on 25/09/2017.
 */

public class EffectsActivity extends NavigationAppActivity {
    private String m_Text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.effects_screen);
        super.onCreate(savedInstanceState);

        ListView lv = (ListView) findViewById(R.id.lvEffects);
        this.setTitle("Stage Effects");

        ArrayAdapter<IAlertContent> ag = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Data.getEffects());
        lv.setAdapter(ag);

        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.btAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EffectsActivity.this);
                final EditText input = new EditText(EffectsActivity.this);
                builder.setTitle("Add effect...");
                builder.setView(input);

                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();

                        if (m_Text.isEmpty()) {
                            String timeStamp = new SimpleDateFormat("MMdd_HHmmss").format(Calendar.getInstance().getTime());
                            m_Text = "Effect " + timeStamp;
                        }
                        Data.addEffect(m_Text);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }
}
