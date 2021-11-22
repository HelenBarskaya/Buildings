package com.company.buildings.threads;

public class MySemaphore {
    boolean permission=true;

    public synchronized void clean() throws InterruptedException {
        while(permission) wait(); //0
        this.notify();
    }

    public synchronized void repair() throws InterruptedException {
        while (!permission) wait(); //1
        this.notify();
    }

    public synchronized void changePermission()
    {
        if(!permission) permission=true;
        else if(permission) permission=false;
        this.notify();
    }
}
