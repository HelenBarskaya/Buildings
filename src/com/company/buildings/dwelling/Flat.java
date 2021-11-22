package com.company.buildings.dwelling;

import com.company.buildings.PlacementExchanger;
import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;
import com.company.interfaces.Space;

import java.io.Serializable;
import java.util.Comparator;

public class Flat implements Space, Serializable, Cloneable, Comparable<Space> {
    private int numberOfRooms;
    private double livingSpace;
    private static final int DEFAULT_NUMBER = 2;
    private  static final double DEFAULT_SPACE = 50;

    public Flat() {
        numberOfRooms = DEFAULT_NUMBER;
        livingSpace = DEFAULT_SPACE;
    }

    public Flat(double s) {
        try {
            numberOfRooms = DEFAULT_NUMBER;
            if(s>0)
                livingSpace = s;
            else throw new InvalidSpaceAreaException();
        } catch (InvalidSpaceAreaException e)
        {
            e.printStackTrace();
            livingSpace=DEFAULT_SPACE;
            System.out.println("Квартире присвоена стандартная площадь 50м");
        }
    }

    public Flat(int n, double s) {
        try {
            if (n>0)
                numberOfRooms = n;
            else throw new InvalidRoomsCountException();
            if(s>0)
                livingSpace = s;
            else throw new InvalidSpaceAreaException();
        } catch (InvalidSpaceAreaException e)
        {
            e.printStackTrace();
            livingSpace=DEFAULT_SPACE;
            System.out.println("Квартире присвоена стандартная площадь 50м");
        } catch (InvalidRoomsCountException e)
        {
            e.printStackTrace();
            numberOfRooms=DEFAULT_NUMBER;
            System.out.println("Квартире присвоено стандартное количество комнат: 2");
        }
    }

    public void setNumberOfRooms(int newNumber) {
        numberOfRooms = newNumber;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setLivingSpace(double newSpace) {
        livingSpace = newSpace;
    }

    public double getLivingSpace() {
        return livingSpace;
    }

    @Override
    public String toString()
    {
        StringBuffer str = new StringBuffer( "Flat ("+numberOfRooms+", "+livingSpace+")");
        return str.toString();
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Flat)
            if (PlacementExchanger.checkExchangeSpaces(this,(Flat) object))
                return true;
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = numberOfRooms^(int)livingSpace^(int)livingSpace<<4;
        return hash;
    }

    @Override
    public Object clone() {
        return new Flat(numberOfRooms,livingSpace);
    }

    @Override
    public int compareTo(Space o) {
        if (this.getLivingSpace() < o.getLivingSpace()) return -1;
        if (this.getLivingSpace() > o.getLivingSpace()) return 1;
        return 0;
    }
    public class Comparator implements java.util.Comparator<Space>
    {
        @Override
        public int compare(Space o1, Space o2) {
            return o1.getNumberOfRooms()-o2.getNumberOfRooms();
        }
    }
}
