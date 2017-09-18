package com.example.user.mobilestagereactionsystem;

import org.junit.Test;

import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.DelegateRegister;
import ext.gestureDetection.base.FVector;

import static org.junit.Assert.assertTrue;

/**
 * Created by Rowan on 18/09/17.
 */

public class DelegateTest {
    @Test
    public void useDelegate() throws Exception {
        // Get data to mutate
        final int[] d = {0};

        // Create function with data to mutate
        Delegate<FVector> del = new Delegate<FVector>() {
            @Override
            public void invoke(FVector obj) {
                d[0] = 1;
            }
        };

        // Invoke to mutate
        del.invoke(null);
        assertTrue(d[0] == 1);
    }

    @Test
    public void useDelegateRegister() throws Exception {
        // Get data to mutate
        final int[] d = {0};

        // Create function with data to mutate
        Delegate<FVector> del = new Delegate<FVector>() {
            @Override
            public void invoke(FVector obj) {
                d[0] = 1;
            }
        };

        // Create register
        DelegateRegister<FVector> reg = new DelegateRegister<>();
        reg.attachDelegate(del);

        // Invoke to mutate
        reg.invokeAll(null);
        assertTrue(d[0] == 1);
    }
}
