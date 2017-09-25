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

    @Test
    public void sizeTest() throws Exception{
        FVector a1 = new FVector(2, 1, 1);
        FVector b1 = new FVector(1, 1, 1);

        Assert.assertTrue(a1.sizesBigger(b1) == 2);
        Assert.assertTrue(b1.sizesBigger(a1) == 1/2f);

        Assert.assertTrue(a1.sizesSmaller(b1) == 1/2f);
        Assert.assertTrue(b1.sizesSmaller(a1) == 2);

        Assert.assertTrue(a1.sizesSmaller(b1) == b1.sizesBigger(a1));
        Assert.assertTrue(b1.sizesSmaller(a1) == a1.sizesBigger(b1));
    }

    @Test
    public void sizeDifferenceTest() throws Exception{
        FVector a1 = new FVector(2, 1, 1);
        FVector b1 = new FVector(1, 1, 1);
        FVector c1 = new FVector(10, 10, 10);
        FVector d1 = new FVector(10, 10, 9);
        FVector d2 = new FVector(10, 10, 8);
        FVector d3 = new FVector(10, 10, 7);

        Assert.assertFalse(a1.isCompressionOf(b1, 0.2f));
        Assert.assertFalse(a1.isEnlargementOf(b1, 0.2f));
        Assert.assertFalse(a1.isSizedVersionOf(b1, 0.2f));

        Assert.assertTrue(b1.isCompressionOf(c1, 0.2f));
        Assert.assertFalse(b1.isEnlargementOf(c1, 0.2f));
        Assert.assertTrue(c1.isEnlargementOf(b1, 0.2f));
        Assert.assertTrue(c1.isSizedVersionOf(b1, 0.2f));
        Assert.assertTrue(d1.isSizedVersionOf(c1, 0.2f));
        Assert.assertTrue(b1.isSizedVersionOf(d1, 0.2f));

        Assert.assertFalse(a1.isSizedVersionOf(c1, 0.2f));

        Assert.assertTrue(b1.isSizedVersionOf(c1, 0.2f));
        Assert.assertTrue(b1.isSizedVersionOf(d1, 0.2f));
        Assert.assertTrue(b1.isSizedVersionOf(d2, 0.2f));
        Assert.assertFalse(b1.isSizedVersionOf(d2, 0.1f));
        Assert.assertFalse(b1.isSizedVersionOf(d3, 0.2f));
    }

    @Test
    public void lowestTest() throws Exception{
        FVector a1 = new FVector(2, 1, 1);
        FVector b1 = new FVector(1, 1, 1);
        FVector c1 = new FVector(10, 10, 10);
        FVector d1 = new FVector(10, 3, 9);
        FVector d2 = new FVector(10, -10, 8);
        FVector d3 = new FVector(10, 10, 7);

        Assert.assertTrue(a1.lowestValue() == 1);
        Assert.assertTrue(b1.lowestValue() == 1);
        Assert.assertTrue(c1.lowestValue() == 10);
        Assert.assertTrue(d1.lowestValue() == 3);
        Assert.assertTrue(d2.lowestValue() == -10);
        Assert.assertTrue(d3.lowestValue() == 7);
    }

    @Test
    public void aroundTest() throws Exception{
        FVector a1 = new FVector(2, 1, 1);
        FVector b1 = new FVector(1, 1, 1);
        FVector c1 = new FVector(10, 10, 10);
        FVector d1 = new FVector(10, 10, 9);
        FVector d2 = new FVector(10, 10, 8);
        FVector d3 = new FVector(10, 10, 7);

        Assert.assertFalse(a1.isAround(b1, 0.2f));
        Assert.assertTrue(a1.isAround(b1, 1));
        Assert.assertTrue(b1.isAround(b1, 0.2f));
        Assert.assertFalse(b1.isAround(c1, 0.2f));

        Assert.assertTrue(d1.isAround(c1, 0.2f));
        Assert.assertTrue(c1.isAround(d1, 0.2f));

        Assert.assertTrue(c1.isAround(d2, 0.2f));
        Assert.assertFalse(c1.isAround(d3, 0.2f));
        Assert.assertTrue(c1.isAround(d3, 0.3f));
    }

    @Test
    public void directionTest() throws Exception{
        FVector d1 = new FVector(1, 1, 0.5f);
        FVector d2 = new FVector(1, 1, -0.5f);
        FVector l1 = new FVector(2, 1, 1);
        FVector l2 = new FVector(2, 1, -1);
        FVector l3 = new FVector(2, 1, -0.5f);
        FVector l4 = new FVector(2, 1, 0.5f);

        Assert.assertTrue(l1.sameDirectionsAs(d1, 0));
        Assert.assertFalse(l2.sameDirectionsAs(d1, 0));
        Assert.assertFalse(l3.sameDirectionsAs(d1, 0));
        Assert.assertTrue(l4.sameDirectionsAs(d1, 0));
        Assert.assertTrue(l4.sameDirectionsAs(d1, 0.5f));
        Assert.assertFalse(l3.sameDirectionsAs(d1, 0));

        Assert.assertFalse(l1.sameDirectionsAs(d2, 0));
        Assert.assertTrue(l2.sameDirectionsAs(d2, 0));
        Assert.assertTrue(l3.sameDirectionsAs(d2, 0));
        Assert.assertFalse(l4.sameDirectionsAs(d2, 0));
        Assert.assertTrue(l4.sameDirectionsAs(d2, 0.5f));
        Assert.assertTrue(l3.sameDirectionsAs(d2, 0));
    }
}
