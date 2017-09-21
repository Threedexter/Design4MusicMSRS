package ext.gestureDetection.base;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Timer;
import java.util.TimerTask;

import ext.gestureDetection.exceptions.NoSensorHandlerException;
import ext.gestureDetection.monitors.SensorMonitor;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by Rowan on 18/09/17.
 */

public class SensorReader implements SensorEventListener{
    private static SensorManager mSensorManager;
    private SensorMonitor mon;

    private boolean fetch = true;

    /**
     * @param sensor the type of sensor to use
     * @param mon the monitor to trigger on trigger
     */
    SensorReader(int sensor, SensorMonitor mon) throws NoSensorHandlerException {
        this.mon = mon;
        Timer timer = new Timer();
        timer.schedule(new SensorThrottle(), 0, 200);

        if (mSensorManager == null) {
            mSensorManager = (SensorManager)(SensorHandler.getSensorHandler().getMainApp()).getSystemService(SENSOR_SERVICE);
        }

        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(sensor),40000);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (fetch) {
            mon.updateSensor(event);
            fetch = false;
        }
    }

    class SensorThrottle extends TimerTask {
        public void run() {
            fetch = true;
        }
    }
}
