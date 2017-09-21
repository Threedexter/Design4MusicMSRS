package com.back;

import java.util.ArrayList;
import java.util.List;

import ext.gestureDetection.base.Gesture;

/**
 * Created by Rowan on 20/09/17.
 */

public class MemoryGestureHolder {
    private static List<Gesture> gestures = new ArrayList<>();

    private MemoryGestureHolder() {}

    public static List<Gesture> getGestures() {
        return gestures;
    }

    public static void addGesture(Gesture gesture) {
        gestures.add(gesture);
    }

    public static void addGestures(List<Gesture> gestures) {
        for (Gesture g : gestures) {
            addGesture(g);
        }
    }
}
