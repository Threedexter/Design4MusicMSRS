package com.modals;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.back.IAlertContent;

import java.util.List;

/**
 * Created by Rowan on 19/09/17.
 */

public abstract class Navigator {

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
     * Moves the view from one activity to another
     * @param global The activity to move from or the general app context
     * @param view The class of the activity to move to
     */
    public static void moveView(Context global, Class view) {
        Intent i = new Intent(global, view);
        global.startActivity(i);
    }
}
