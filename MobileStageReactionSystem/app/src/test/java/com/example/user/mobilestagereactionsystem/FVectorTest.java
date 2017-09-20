package com.example.user.mobilestagereactionsystem;

import org.junit.Assert;
import org.junit.Test;

import ext.gestureDetection.base.Delegate;
import ext.gestureDetection.base.DelegateRegister;
import ext.gestureDetection.base.FVector;

import static org.junit.Assert.assertTrue;

/**
 * Created by Rowan on 18/09/17.
 */

public class FVectorTest {
    @Test
    public void subtractTest() throws Exception {
        FVector a = new FVector(1, 2, 3);
        FVector b = new FVector(1, 1, 1);
        a = FVector.subtract(a, b);

        Assert.assertTrue(a.getX() == 0);
        Assert.assertTrue(a.getY() == 1);
        Assert.assertTrue(a.getZ() == 2);
    }

    @Test
    public void addTest() throws Exception {
        FVector a = new FVector(-1, -2, 3);
        FVector b = new FVector(1, 1, 1);
        a = FVector.add(a, b);

        Assert.assertTrue(a.getX() == 0);
        Assert.assertTrue(a.getY() == -1);
        Assert.assertTrue(a.getZ() == 4);
    }

    @Test
    public void lengthTest() throws Exception {
        FVector a = new FVector(1, 0, 0);
        Assert.assertTrue(a.length() == 1);
    }

    @Test
    public void angleTest() throws Exception {
        FVector a = new FVector(1, 1, 0);
        FVector b = new FVector(0, 0, 0);

        double x = b.angleBetween(a) * 180 / Math.PI;
        Assert.assertTrue(Math.round(x) == 90);
    }

    @Test
    public void pointBetweenTest() throws Exception {
        FVector a1 = new FVector(2, 2, 2);
        FVector b1 = new FVector(0, 0, 0);
        FVector t1 = new FVector(1, 1, 1);

        Assert.assertTrue(t1.isBetween(b1, a1, 0));
        Assert.assertTrue(t1.isBetween(a1, b1, 0));

        FVector texactmin = new FVector(0, 0, 0);
        FVector texactmax = new FVector(2, 2, 2);

        FVector txoutmax = new FVector(10, 1, 1);
        FVector txoutmin = new FVector(-10, 1, 1);

        FVector tyoutmax = new FVector(1, 10, 1);
        FVector tyoutmin = new FVector(1, -10, 1);

        FVector tzoutmax = new FVector(1, 1, 10);
        FVector tzoutmin = new FVector(1, 1, -10);

        FVector twooutmin = new FVector(-1, -1, 1);
        FVector threeoutmin = new FVector(-1, -1, -1);
        FVector twooutmax = new FVector(10, -10, 1);
        FVector threeoutmax = new FVector(10, 10, 10);

        Assert.assertTrue(texactmax.isBetween(b1, a1, 0));
        Assert.assertTrue(texactmin.isBetween(b1, a1, 0));
        Assert.assertFalse(txoutmax.isBetween(b1, a1, 0));
        Assert.assertFalse(tyoutmax.isBetween(b1, a1, 0));
        Assert.assertFalse(tzoutmax.isBetween(b1, a1, 0));
        Assert.assertFalse(txoutmin.isBetween(b1, a1, 0));
        Assert.assertFalse(tyoutmin.isBetween(b1, a1, 0));
        Assert.assertFalse(tzoutmin.isBetween(b1, a1, 0));

        Assert.assertFalse(twooutmax.isBetween(b1, a1, 0));
        Assert.assertFalse(threeoutmax.isBetween(b1, a1, 0));
        Assert.assertFalse(twooutmin.isBetween(b1, a1, 0));
        Assert.assertFalse(threeoutmin.isBetween(b1, a1, 0));

        Assert.assertTrue(texactmax.isBetween(a1, b1, 0));
        Assert.assertTrue(texactmin.isBetween(a1, b1, 0));
        Assert.assertFalse(txoutmax.isBetween(a1, b1, 0));
        Assert.assertFalse(tyoutmax.isBetween(a1, b1, 0));
        Assert.assertFalse(tzoutmax.isBetween(a1, b1, 0));
        Assert.assertFalse(txoutmin.isBetween(a1, b1, 0));
        Assert.assertFalse(tyoutmin.isBetween(a1, b1, 0));
        Assert.assertFalse(tzoutmin.isBetween(a1, b1, 0));

        Assert.assertFalse(twooutmax.isBetween(a1, b1, 0));
        Assert.assertFalse(threeoutmax.isBetween(a1, b1, 0));
        Assert.assertFalse(twooutmin.isBetween(a1, b1, 0));
        Assert.assertFalse(threeoutmin.isBetween(a1, b1, 0));
    }

    @Test
    public void pointBetweenToleranceTest() throws Exception {
        FVector a1 = new FVector(2, 2, 2);
        FVector b1 = new FVector(0, 0, 0);
        FVector t1 = new FVector(0, 2, 2.1f);
        FVector t2 = new FVector(0, 2.1f, 2);
        FVector t3 = new FVector(-0.1f, 2, 2);

        Assert.assertTrue(t1.isBetween(a1, b1, 0.1f));
        Assert.assertTrue(t1.isBetween(a1, b1, 0.2f));
        Assert.assertFalse(t1.isBetween(a1, b1, 0));

        Assert.assertTrue(t2.isBetween(a1, b1, 0.1f));
        Assert.assertTrue(t2.isBetween(a1, b1, 0.2f));
        Assert.assertFalse(t2.isBetween(a1, b1, 0));

        Assert.assertTrue(t3.isBetween(a1, b1, 0.1f));
        Assert.assertTrue(t3.isBetween(a1, b1, 0.2f));
        Assert.assertFalse(t3.isBetween(a1, b1, 0));
    }
}
