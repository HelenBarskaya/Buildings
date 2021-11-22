package com.company.buildings.office;

import com.company.buildings.PlacementExchanger;
import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;
import com.company.interfaces.Space;

import java.io.Serializable;

public class Office implements Space, Serializable, Cloneable, Comparable<Space> {
    private int numberOfRooms;
    private  double livingSpace;
    private static final int DEFAULT_NUMBER = 1;
    private static final double DEFAULT_SPACE = 250;

    public Office() {
        numberOfRooms = DEFAULT_NUMBER;
        livingSpace = DEFAULT_SPACE;
    }
    public Office(Space a){
        this.livingSpace = a.getLivingSpace();
        this.numberOfRooms = a.getNumberOfRooms();
    }

    public Office(double s) {
        try {
            numberOfRooms = DEFAULT_NUMBER;
            if (s > 0)
                livingSpace = s;
            else throw new InvalidSpaceAreaException();
        } catch (InvalidSpaceAreaException e) {
            e.printStackTrace();
            livingSpace = DEFAULT_SPACE;
            System.out.println("Офису присвоена стандартная площадь 250м");
        }
    }

    public Office(int n, double s) {
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
            System.out.println("Офису присвоена стандартная площадь 250м");
        } catch (InvalidRoomsCountException e)
        {
            e.printStackTrace();
            numberOfRooms=DEFAULT_NUMBER;
            System.out.println("Офису присвоено стандартное количество комнат: 1");
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
        StringBuffer str = new StringBuffer( "Office ("+numberOfRooms+", "+livingSpace+") ");
        return str.toString();
    }
    @Override
    public boolean equals(Object object){
        if(object instanceof Office)
            if (PlacementExchanger.checkExchangeSpaces(this,(Office) object))
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
        return new Office(this.numberOfRooms,this.livingSpace);
    }

    @Override
    public int compareTo(Space o) {
        if (this.getLivingSpace() < o.getLivingSpace()) return -1;
        if (this.getLivingSpace() > o.getLivingSpace()) return 1;
        return 0;
    }
}
