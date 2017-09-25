package ext.gestureDetection.patterns;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.DelegateRegister;
import ext.gestureDetection.base.FVector;
import ext.gestureDetection.base.Gesture;
import ext.gestureDetection.base.GestureDetector;
import ext.gestureDetection.base.LimitedQueue;
import ext.gestureDetection.exceptions.NoSensorHandlerException;
import ext.gestureDetection.monitors.AcceleroMeterMonitor;

/**
 * Created by Rowan on 18/09/17.
 */

public class Matcher extends Recorder {
    private List<GestureDetector> gestures = new ArrayList<>();
    private DelegateRegister<Gesture> register = new DelegateRegister<>();

    public Matcher() throws NoSensorHandlerException {
        super();
    }

    public Matcher(Context context) {
        super(context);
    }

    public DelegateRegister getRegister() {
        return register;
    }

    public void addGesture(Gesture gesture) {
        this.gestures.add(new GestureDetector(gesture));
    }

    public void addGestures(List<Gesture> gestures) {
        for (Gesture g : gestures) {
            addGesture(g);
        }
    }

    @Override
    protected void record(FVector vector) {
        if (!recording) return;
        match(vector);
    }

    @Override
    public void stop() {
        recording = false;
    }

    private void match(FVector v) {
        for (GestureDetector g : gestures) {
            g.matchGesture(v);
            if (g.matchedGesture()) {
                register.invokeAll(g.getGesture());
                g.reset();
            }
        }
    }
}
