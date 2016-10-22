package com.epam.algorithms.concurency;

public class Singleton {
    private static Singleton instance;

    private int counter;

    private Singleton() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    System.out.println("Synchronization!");
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void print() {
        System.out.println(this);
    }
}
