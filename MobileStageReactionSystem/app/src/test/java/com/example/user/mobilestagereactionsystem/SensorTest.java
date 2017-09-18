package com.example.user.mobilestagereactionsystem;

import org.junit.Test;

import ext.gestureDetection.exceptions.NoSensorHandlerException;
import ext.gestureDetection.monitors.AcceleroMeterMonitor;


/**
 * Created by Rowan on 18/09/17.
 */

public class SensorTest {
    @Test(expected = NoSensorHandlerException.class)
    public void useSensorMonitorWithout() throws Exception {
        new AcceleroMeterMonitor();
    }
}
