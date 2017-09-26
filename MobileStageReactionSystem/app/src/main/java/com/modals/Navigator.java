package com.modals;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Debug;
import android.util.Log;

import com.back.Debugger;
import com.back.IAlertContent;
import com.example.user.mobilestagereactionsystem.HomeScren;
import com.example.user.mobilestagereactionsystem.Match;
import com.example.user.mobilestagereactionsystem.Record;

import java.util.List;

/**
 * Created by Rowan on 19/09/17.
 */

public class Navigator {
    private static final String[] options = new String[]{"Home", "Test gesture", "Record"};
    private AlertDialog navigation;
    private Context context;

    public Navigator(Context context) {
        this.context = context;
        createNavigation();
    }

    /**
     * Creates the navigation alert dialog to call
     */
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

    /**
     * Generates and displays an alert dialog with options
     * @param context The context that should display this alert dialog
     * @param name The name of the alert dialog
     * @param options The content of the alert dialog
     */
    public static void createAlertDialog(Context context, String name, final List<IAlertContent> options) {
        final IAlertContent[] a = options.toArray(new IAlertContent[options.size()]);
        final String[] b = new String[options.size()];
        int i = 0;
        for (IAlertContent ia : options) {
            b[i] = ia.getOptionName();
            i++;
        }

        new AlertDialog.Builder(context).setTitle(name)
                .setItems(b, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        a[which].trigger();
                    }
                }).setCancelable(false).create().show();
    }

    /**
     * Shows a quick mock-up navigation
     */
    public void show() {
        navigation.show();
    }

    /**
     * Moves the view from one activity to another
     * @param global The activity to move from or the general app context
     * @param view The class of the activity to move to
     */
    public static void moveView(Context global, Class view) {
        Intent i = new Intent(global, view);
        global.startActivity(i);
    }
}
