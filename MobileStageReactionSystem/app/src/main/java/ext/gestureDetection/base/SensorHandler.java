package ext.gestureDetection.base;

import android.content.Context;

import ext.gestureDetection.exceptions.NoSensorHandlerException;
import ext.gestureDetection.monitors.SensorMonitor;

/**
 * Created by Rowan on 18/09/17.
 */

public class SensorHandler {
    private static SensorHandler Instance;
    private Context mainApp;

    private SensorHandler(Context mainApp) {
        Instance = this;
        this.mainApp = mainApp;
    }


    Context getMainApp() {
        return mainApp;
    }

    public SensorReader addSensor(int type, SensorMonitor call) {
        try {
            return new SensorReader(type, call);
        } catch (NoSensorHandlerException e) {
            // Never happens
        }
        return null;
    }

    /**
     * @param mainApp
     * @return (creates and) returns a sensorhandler
     */
    public static SensorHandler getSensorHandler(Context mainApp) {
        if (Instance == null) {
            Instance = new SensorHandler(mainApp);
        }
        return Instance;
    }

    /**
     * @return a sensorhandler
     * @throws NoSensorHandlerException if no sensorhandler was initialised
     */
    public static SensorHandler getSensorHandler() throws NoSensorHandlerException {
        if (Instance == null) {
            throw new NoSensorHandlerException();
        }
        return Instance;
    }
}
