package com.riabchenko.algorithms.concurency;

import org.junit.Test;

/**
 *
 */
public class SingletonTest {
    int constant;
    @Test
    public void testSingleton() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            Thread t = new Thread() {
                @Override
                public void run() {
                    System.out.printf("Thread #%d: %s\n", finalI,  Singleton.getInstance());
                }
            };
            t.start();
        }
        Thread.sleep(1000);
    }
}
