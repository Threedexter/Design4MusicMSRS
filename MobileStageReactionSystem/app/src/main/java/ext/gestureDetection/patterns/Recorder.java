package ext.gestureDetection.patterns;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.FVector;
import ext.gestureDetection.base.LimitedQueue;
import ext.gestureDetection.base.TimedObject;
import ext.gestureDetection.exceptions.NoSensorHandlerException;
import ext.gestureDetection.monitors.AcceleroMeterMonitor;

/**
 * Created by Rowan on 18/09/17.
 */

public class Recorder {
    protected AcceleroMeterMonitor monitor;
    protected List<TimedObject<FVector>> vectors = new LimitedQueue<>(1000);

    protected boolean recording = false;

    private static final long MILLISECONDS_DISREGARD_START = 1000;
    private static final long MILLISECONDS_DISREGARD_END = 1000;

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

    protected void record(FVector vector) {
        if (!recording) return;

        // Add to list
        vectors.add(new TimedObject<>(vector));
        Log.d("TEST", vector.toString());
    }

    public void start() {
        recording = true;
    }

    public void stop() {
        recording = false;
        long start = vectors.get(0).getTime();
        long end = vectors.get(vectors.size() - 1).getTime();
        List<TimedObject<FVector>> newList = new ArrayList<>();

        // Make a new list with objects within time reach
        for (TimedObject<FVector> to : vectors) {
            if (to.getTime() > start + MILLISECONDS_DISREGARD_START // If the object was measured after the tolerance zone
                    || to.getTime() < end + MILLISECONDS_DISREGARD_END) // If the object was measured before the tolerance zone
            {
                // Add to list
                newList.add(to);
            }
        }

        // clear and replace the list
        clear();
        this.vectors = newList;
    }

    public void clear() {
        this.vectors.clear();
    }

    public List<FVector> getVectors() {
        List<FVector> vectors = new ArrayList<>(this.vectors.size());

        for (TimedObject<FVector> v : this.vectors) {
            vectors.add(v.getObject());
        }

        return vectors;
    }
}
