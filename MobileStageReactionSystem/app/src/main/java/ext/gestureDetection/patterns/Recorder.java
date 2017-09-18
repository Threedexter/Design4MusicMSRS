package ext.gestureDetection.patterns;

import android.content.Context;

import java.util.List;
import java.util.Queue;

import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.FVector;
import ext.gestureDetection.base.LimitedQueue;
import ext.gestureDetection.exceptions.NoSensorHandlerException;
import ext.gestureDetection.monitors.AcceleroMeterMonitor;

/**
 * Created by Rowan on 18/09/17.
 */

public class Recorder {
    private AcceleroMeterMonitor monitor;
    private List<FVector> vectors = new LimitedQueue<>(1000);

    private boolean recording = false;

    public Recorder() throws NoSensorHandlerException {
        this.monitor = new AcceleroMeterMonitor();
        setup();
    }

    public Recorder(Context context) {
        this.monitor = new AcceleroMeterMonitor(context);
        setup();
    }

    private final void setup() {
        Delegate<FVector> del = new Delegate<FVector>() {
            @Override
            public void invoke(FVector obj) {
                record(obj);
            }
        };
        this.monitor.attachSensorUpdateEvent(del);
    }

    private void record(FVector vector) {
        if (!recording) return;

        // Add to list
        vectors.add(vector);

        // todo: calculate over all vectors

    }

    public void start() {
        recording = true;
    }

    public void stop() {
        recording = false;
    }

    public List<FVector> getVectors() {
        return vectors;
    }
}
