package com.example.user.mobilestagereactionsystem;

import org.junit.Test;

import ext.gestureDetection.base.LimitedQueue;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class QueueTest {
    @Test
    public void TestQueue() throws Exception {
        LimitedQueue<Integer> ints = new LimitedQueue<>(2);

        assertTrue(ints.isEmpty());

        ints.add(1); // 1
        assertTrue(ints.size() == 1);

        ints.add(2); // 1, 2
        assertTrue(ints.get(1) == 2);

        ints.add(3); // 2, 3
        assertTrue(ints.size() == 2);
        assertTrue(ints.get(0) == 2);
        assertTrue(ints.get(1) == 3);
    }
}