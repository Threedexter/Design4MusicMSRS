package ext.gestureDetection.base;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import ext.gestureDetection.exceptions.NoSensorHandlerException;
import ext.gestureDetection.monitors.SensorMonitor;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by Rowan on 18/09/17.
 */

public class SensorReader implements SensorEventListener{
    private static SensorManager mSensorManager;
    private SensorMonitor mon;

    /**
     * @param sensor the type of sensor to use
     * @param mon the monitor to trigger on trigger
     */
    SensorReader(int sensor, SensorMonitor mon) throws NoSensorHandlerException {
        this.mon = mon;

        if (mSensorManager == null) {
            mSensorManager = (SensorManager)(SensorHandler.getSensorHandler().getMainApp()).getSystemService(SENSOR_SERVICE);
        }

        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(sensor),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        mon.updateSensor(event);
    }
}
