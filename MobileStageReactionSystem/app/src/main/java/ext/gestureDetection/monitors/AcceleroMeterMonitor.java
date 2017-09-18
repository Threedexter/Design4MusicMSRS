package ext.gestureDetection.monitors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.DelegateRegister;
import ext.gestureDetection.base.SensorHandler;
import ext.gestureDetection.base.SensorReader;
import ext.gestureDetection.base.FVector;
import ext.gestureDetection.exceptions.NoSensorHandlerException;

/**
 * Created by Rowan on 18/09/17.
 */

public class AcceleroMeterMonitor implements SensorMonitor {

    private FVector velocity = new FVector();
    private SensorReader Accelero;
    private DelegateRegister<FVector> register = new DelegateRegister<>();

    /**
     * Accelerometer monitor class. Calls updateSensor when the sensor is update
     * @param context
     */
    public AcceleroMeterMonitor(Context context) {
        this.Accelero = SensorHandler.getSensorHandler(context).addSensor(Sensor.TYPE_ACCELEROMETER, this);
    }

    /**
     * Accelerometer monitor class. Calls updateSensor when the sensor is update
     * @throws NoSensorHandlerException
     */
    public AcceleroMeterMonitor() throws NoSensorHandlerException {
        this.Accelero = SensorHandler.getSensorHandler().addSensor(Sensor.TYPE_ACCELEROMETER, this);
    }

    /**
     * @param del the delegate to invoke
     */
    public void attachSensorUpdateEvent(Delegate<FVector> del) {
        register.attachDelegate(del);
    }

    @Override
    public void updateSensor(SensorEvent e) {
        velocity.setX(e.values[0]);
        velocity.setY(e.values[1]);
        velocity.setZ(e.values[2]);
        register.invokeAll(velocity);
    }
}
