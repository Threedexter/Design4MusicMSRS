package com.example.user.mobilestagereactionsystem;

import junit.framework.Assert;

import org.junit.Test;

import ext.dataConversion.JSONConverter;
import ext.gestureDetection.base.FVector;


/**
 * Created by Rowan on 18/09/17.
 */

public class JSONTest {
    @Test
    public void ConvertFVectorToJSON() throws Exception {
        FVector testVector = new FVector();
        String JSON = JSONConverter.convertToJSON(testVector);
        Assert.assertTrue(JSON.equals("{\"x\":0.0,\"y\":0.0,\"z\":0.0}"));
    }

    @Test
    public void ConvertJSONToFVector() throws Exception {
        String JSON = "{\"x\":5.0,\"y\":6.0,\"z\":7.0}";
        FVector test = (FVector)JSONConverter.convertToClass(JSON, FVector.class);
        Assert.assertTrue(test.getX() == 5);
        Assert.assertTrue(test.getY() == 6);
        Assert.assertTrue(test.getZ() == 7);
    }

    @Test
    public void ConvertJSONFull() throws Exception {
        FVector test = new FVector(5,6,7);
        Assert.assertTrue(test.getX() == 5);
        Assert.assertTrue(test.getY() == 6);
        Assert.assertTrue(test.getZ() == 7);
        String JSON = JSONConverter.convertToJSON(test);

        test = (FVector)JSONConverter.convertToClass(JSON, FVector.class);
        Assert.assertTrue(test.getX() == 5);
        Assert.assertTrue(test.getY() == 6);
        Assert.assertTrue(test.getZ() == 7);
    }
}
