package com.example.user.mobilestagereactionsystem;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import ext.gestureDetection.patterns.Recorder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RecordTest {
    @Test
    public void TestRecord() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Recorder r = new Recorder(appContext);

        assertTrue(r.getVectors().isEmpty());

        r.start();
        // Measure
        Thread.sleep(1000);
        // End measure
        r.stop();

        assertFalse(r.getVectors().isEmpty());
    }
}
