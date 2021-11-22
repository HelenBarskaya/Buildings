package com.company.buildings.threads;

import com.company.interfaces.*;

public class Repairer extends Thread{
    private Floor floor;
    public Repairer(Floor f)
    {
        floor = f;
    }
    @Override
    public void run() {
        if(!Thread.interrupted()) {
            for (int i = 0; i < floor.getQuantitySpaces(); i++) {
                System.out.println("Repairing space number " + i + " with total area " + floor.getSpaceByNumber(i).getLivingSpace() + " square meters");
            }
            System.out.println("The end of the floor");
        }else System.out.println("Thread interrupted");
    }
}
