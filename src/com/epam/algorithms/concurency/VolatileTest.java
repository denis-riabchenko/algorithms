package com.epam.algorithms.concurency;

/**
 *
 */
public class VolatileTest {
    private static volatile int count = 0;

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int local_value = count;
            while (local_value < 5){
                if(local_value != count){
                    System.out.printf("Got Change for count : %d\n", count);
                    local_value = count;
                }
            }
        }
    }

    static class ChangeMaker extends Thread{
        @Override
        public void run() {
            int local_value = count;
            while (count < 5){
                System.out.printf("Incrementing count : %d\n", local_value + 1);
                count = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}