package com.back;

import android.util.Log;

import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.DelegateRegister;

/**
 * Created by Rowan on 21/09/17.
 */

public abstract class Debugger {
    private static boolean log = false;
    private static DelegateRegister<String> register;

    public static void log(String s) {
        log("LOGGER:", s);
    }

    public static void log(String tag, String s) {
        if (log) {
            Log.d(tag, s);
            if (register != null) {
                register.invokeAll(tag + " " + s);
            }
        }
    }

    public static void addDelegate(Delegate<String> del) {
        if (register == null) {
            register = new DelegateRegister<>();
        }
        register.attachDelegate(del);
    }

    public static boolean isLogging() {
        return log;
    }

    public static void setLogging(boolean log) {
        Debugger.log = log;
    }
}
