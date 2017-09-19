package ext.gestureDetection.patterns;

import android.content.Context;

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

    public DelegateRegister getRegister() {
        return register;
    }

    public Matcher(Context context) {
        super(context);
    }

    public void addGesture(GestureDetector gesture) {
        this.gestures.add(gesture);
    }

    public void addGestures(List<GestureDetector> gestures) {
        this.gestures.addAll(gestures);
    }

    @Override
    protected void record(FVector vector) {
        if (!recording) return;
        match(vector);
    }

    private void match(FVector v) {
        int maxErr = 3;

        // for each gesture
        for (GestureDetector g : gestures) {

            // Get tolerance
            float t = g.getGesture().getTolerance();

            // Get index of gesture and match the vector
            FVector vg = g.getGesture().getMovement().get(g.getIndex());

            if (!checkIfInBoundaries(vg, v, t)) {
                // if ongoing
                if (g.isOngoing()) {
                    // Count error
                    g.raiseErrorCount();

                    // if no more errors are accepted
                    if (g.getErrorCount() >= maxErr) {
                        g.resetErrors();
                        g.resetIndex();
                    }
                }
            } else {
                g.raiseIndex();
                g.resetErrors();
            }

            if (g.shouldTrigger()) {
                register.invokeAll(g.getGesture());
                g.resetErrors();
                g.resetIndex();
            }
        }
    }

    //todo: move for UnitTests
    private boolean checkIfInBoundaries(FVector current, FVector other, float tolerance) {
        float d = current.length();
        float o = other.length();

        boolean ret = Math.abs((o - d * (1-tolerance)) / d) <= tolerance;
        if (!ret) return ret;

        // assuming that tolerance is a percentage of angle (180)
        return 180 * tolerance > current.angleBetween(other);
    }
}
