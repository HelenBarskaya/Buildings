package com.company.buildings.threads;

import com.company.interfaces.*;

import java.util.concurrent.Semaphore;

public class SequentalCleaner implements Runnable {
    private Floor floor;
    private final MySemaphore semaphore;

    public SequentalCleaner(Floor f, MySemaphore s) {
        floor = f;
        semaphore = s;
    }

    @Override
    public void run() {
        if (!Thread.interrupted()) {
            try {
            for (int i = 0; i < floor.getQuantitySpaces(); i++) {
                    semaphore.clean();
                    System.out.println("Cleaning room number " + i + " with total area " + floor.getSpaceByNumber(i).getLivingSpace() + " square meters");
                    semaphore.changePermission();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The end of the floor");
        } else System.out.println("Thread interrupted");
    }
}
