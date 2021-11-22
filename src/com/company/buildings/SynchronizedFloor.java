package com.company.buildings;

import com.company.interfaces.*;

import java.util.Iterator;

public class SynchronizedFloor implements Floor {
    Floor floor;
    public SynchronizedFloor(Floor floor)
    {
        this.floor = floor;
    }
    @Override
    public synchronized int getQuantitySpaces() {
        return floor.getQuantitySpaces();
    }

    @Override
    public synchronized double getTotalArea() {
        return floor.getTotalArea();
    }

    @Override
    public synchronized int getQuantityRooms() {
        return floor.getQuantityRooms();
    }

    @Override
    public synchronized Space[] getArray() {
        return floor.getArray();
    }

    @Override
    public synchronized Space getSpaceByNumber(int n) {
        return floor.getSpaceByNumber(n);
    }

    @Override
    public synchronized void setSpaceByNumber(int n, Space s) {
        floor.setSpaceByNumber(n,s);
    }

    @Override
    public synchronized void addSpaceByNumber(int n, Space s) {
        floor.addSpaceByNumber(n,s);
    }

    @Override
    public synchronized void deleteSpaceByNumber(int n) {
        floor.deleteSpaceByNumber(n);
    }

    @Override
    public synchronized Space getBestPlace() {
        return floor.getBestPlace();
    }

    @Override
    public synchronized Object clone() {
        return floor.clone();
    }

    @Override
    public synchronized int compareTo(Floor o) {
        return floor.compareTo(o);
    }

    @Override
    public Iterator<Space> iterator() {
        return floor.iterator();
    }

}
