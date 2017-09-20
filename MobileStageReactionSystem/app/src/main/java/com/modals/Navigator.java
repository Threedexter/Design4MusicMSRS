package com.modals;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.example.user.mobilestagereactionsystem.HomeScren;
import com.example.user.mobilestagereactionsystem.Match;
import com.example.user.mobilestagereactionsystem.Record;

/**
 * Created by Rowan on 19/09/17.
 */

public class Navigator {
    private static final String[] options = new String[]{"Home", "Effects", "Recordings"};
    private AlertDialog navigation;
    private Context context;

    public Navigator(Context context) {
        this.context = context;
        createNavigation();
    }

    private void createNavigation() {
        if (navigation == null) {
            navigation = new AlertDialog.Builder(context).setTitle("Menu")
                    .setItems(options, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("MENU: ", options[which]);
                            switch (which) {
                                case 0 :
                                    moveView(context, HomeScren.class);
                                    break;
                                case 1 :
                                    moveView(context, Match.class);
                                    break;
                                case 2 :
                                    moveView(context, Record.class);
                                    break;
                            }
                        }
                    }).create();
        }
    }

    public void show() {
        navigation.show();
    }

    public static void moveView(Context global, Class view) {
        Intent i = new Intent(global, view);
        global.startActivity(i);
    }
}
