package ext.gestureDetection.monitors;

import android.hardware.SensorEvent;

/**
 * Created by Rowan on 18/09/17.
 */

public interface SensorMonitor {
    void updateSensor(SensorEvent e);
}
