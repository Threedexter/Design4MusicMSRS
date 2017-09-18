package com.example.user.mobilestagereactionsystem;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.DelegateRegister;
import ext.gestureDetection.base.FVector;
import ext.gestureDetection.base.SensorHandler;
import ext.gestureDetection.monitors.AcceleroMeterMonitor;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SensorTest {
    @Test
    public void useSensorMonitor() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        // Get data to mutate
        final int[] d = {0};

        // Create function with data to mutate
        Delegate<FVector> del = new Delegate<FVector>() {
            @Override
            public void invoke(FVector obj) {
                if (obj != null) {
                    d[0] = 1;
                }
            }
        };

        AcceleroMeterMonitor monitor = new AcceleroMeterMonitor(appContext);
        monitor.attachSensorUpdateEvent(del);

        // Wait 250 ms for a sensor update
        Thread.sleep(250);

        // Check if mutated
        assertTrue(d[0] == 1);
    }

    @Test
    public void useSensorMonitorWithout() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        SensorHandler.getSensorHandler(appContext);
        AcceleroMeterMonitor monitor = new AcceleroMeterMonitor();
        assertFalse(monitor == null);
    }
    @Test
    public void useSensorMonitorWith() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        AcceleroMeterMonitor monitor = new AcceleroMeterMonitor(appContext);
        assertFalse(monitor == null);
    }
}
