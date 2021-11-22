package com.company.buildings.dwelling;

import com.company.exceptions.*;
import com.company.interfaces.*;
import java.io.Serializable;
import java.util.NoSuchElementException;

public class Dwelling implements Building, Serializable, Iterable<Floor> {
    private Floor[] arrayFloors;

    public Dwelling(int quantityFloors, int[] quantityFlats) {
        arrayFloors = new Floor[quantityFloors];
        for (int i = 0; i < quantityFloors; i++) {
            arrayFloors[i] = new DwellingFloor(quantityFlats[i]);
        }
    }

    public Dwelling(Floor[] arrayFloors) {
        this.arrayFloors = arrayFloors;
    }

    public int getQuantityFloors() {
        return arrayFloors.length;
    }

    public int getQuantitySpaces() {
        int num = 0;
        for (Floor a : arrayFloors) {
            num += a.getQuantitySpaces();
        }
        return num;
    }

    public double getTotalArea() {
        int area = 0;
        for (Floor a : arrayFloors) {
            area += a.getTotalArea();
        }
        return area;
    }

    public int getQuantityRooms() {
        int num = 0;
        for (Floor a : arrayFloors) {
            num += a.getQuantityRooms();
        }
        return num;
    }

    public Floor[] getArrayFloors() {
        return arrayFloors;
    }

    public Floor getFloorByNumber(int num) {
        try {
            if (num < arrayFloors.length)
                return arrayFloors[num];
            else throw new FloorIndexOutOfBoundsException();
        } catch(FloorIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
        return arrayFloors[arrayFloors.length-1];
    }

    public Space getSpaceByNumber(int numFlat) {
        try {
            if(numFlat<this.getQuantitySpaces()) {
                int numFloor = 0;
                if (numFlat < 0 || numFlat > this.getQuantitySpaces())
                    return null;
                for (; numFloor < arrayFloors.length; numFloor++) {
                    if (numFlat > arrayFloors[numFloor].getQuantitySpaces()) {
                        numFlat -= arrayFloors[numFloor].getQuantitySpaces();
                    } else if (numFlat <= arrayFloors[numFloor].getQuantitySpaces()) {
                        if (numFloor == 0)
                            return arrayFloors[numFloor].getSpaceByNumber(numFlat);
                        else
                            return arrayFloors[numFloor].getSpaceByNumber(numFlat - 1);
                    }
                }
            } else throw new SpaceIndexOutOfBoundsException();
        } catch(SpaceIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
        return this.getSpaceByNumber(this.getQuantitySpaces()-1);
    }

    public void setFloorByNumber(int num, Floor newFloor) {
        try {
            if (num < arrayFloors.length)
                arrayFloors[num] = newFloor;
            else throw new FloorIndexOutOfBoundsException();
        }catch(FloorIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
            e.printMessage();
        }
    }

    public void setSpaceByNumber(int numFlat, Space newFlat) {
        try {
            if(numFlat<this.getQuantitySpaces()) {
                int numFloor = 0;
                for (; numFloor < arrayFloors.length; numFloor++) {
                    if (numFlat > arrayFloors[numFloor].getQuantitySpaces()) {
                        numFlat -= arrayFloors[numFloor].getQuantitySpaces();
                    } else if (numFlat <= arrayFloors[numFloor].getQuantitySpaces()) {
                        if (numFloor == 0)
                            arrayFloors[numFloor].setSpaceByNumber(numFlat, newFlat);
                        else
                            arrayFloors[numFloor].setSpaceByNumber(numFlat - 1, newFlat);
                    }
                }
            } else throw new SpaceIndexOutOfBoundsException();
        }catch(SpaceIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
    }

    public void addSpaceByNumber(int numFlat, Space newFlat) {
        try {
            if(numFlat<=this.getQuantitySpaces()) {
                int numFloor = 0;
                for (; numFloor < arrayFloors.length; numFloor++) {
                    if (numFlat > arrayFloors[numFloor].getQuantitySpaces()) {
                        numFlat -= arrayFloors[numFloor].getQuantitySpaces();
                    } else if (numFlat <= arrayFloors[numFloor].getQuantitySpaces()) {
                        if (numFloor == 0) {
                            arrayFloors[numFloor].addSpaceByNumber(numFlat, newFlat);
                            break;
                        } else {
                            arrayFloors[numFloor].addSpaceByNumber(numFlat - 1, newFlat);
                            break;
                        }
                    }
                }
            } else throw new SpaceIndexOutOfBoundsException();
        }catch(SpaceIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
    }

    public void deleteSpaceByNumber(int numFlat) {
        try {
            if(numFlat<this.getQuantitySpaces()) {
                int numFloor = 0;
                for (; numFloor < arrayFloors.length; numFloor++) {
                    if (numFlat > arrayFloors[numFloor].getQuantitySpaces()) {
                        numFlat -= arrayFloors[numFloor].getQuantitySpaces();
                        break;
                    } else if (numFlat <= arrayFloors[numFloor].getQuantitySpaces()) {
                        if (numFloor == 0) {
                            arrayFloors[numFloor].deleteSpaceByNumber(numFlat);
                        } else {
                            arrayFloors[numFloor].deleteSpaceByNumber(numFlat - 1);
                        }
                        break;
                    }
                }
            }else throw new SpaceIndexOutOfBoundsException();
        }catch(SpaceIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
    }

    public Space getBestPlace() {
        Space bestFlat = arrayFloors[0].getSpaceByNumber(0);
        for (int i = 0; i < arrayFloors.length; i++) {
            if (bestFlat.getLivingSpace() < arrayFloors[i].getBestPlace().getLivingSpace()) {
                bestFlat = arrayFloors[i].getBestPlace();
            }
        }
        return bestFlat;
    }

    public Space[] sortSpaces() {
        Space[] sortedArrayFlats = new Space[this.getQuantitySpaces()];
        int i = 0;
        for (int j = 0; j < this.getQuantityFloors(); j++)// Проходимся по всем этажам
            for (int k = 0; k < arrayFloors[j].getQuantitySpaces(); k++, i++)// Проходимся по всем квартирам на этаже
                sortedArrayFlats[i] = arrayFloors[j].getSpaceByNumber(k);// И заполняем квартирами массив
        for (i = sortedArrayFlats.length - 1; i >= 1; i--) // Пузырьковая сортировка
        {
            for (int j = 0; j < i; j++) {
                if (sortedArrayFlats[j].getLivingSpace() < sortedArrayFlats[j + 1].getLivingSpace()) {
                    Space buffer = sortedArrayFlats[j];
                    sortedArrayFlats[j] = sortedArrayFlats[j + 1];
                    sortedArrayFlats[j + 1] = buffer;
                }
            }
        }
        return sortedArrayFlats;
    }

    @Override
    public String toString()
    {
        StringBuffer str = new StringBuffer("Dwelling ("+ arrayFloors.length+", ");
        for (Floor f:arrayFloors) {
            str.append(f.toString());
        }
        str.append(")");
        return str.toString();
    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof Dwelling)
            if (this.arrayFloors.length == ((Dwelling) object).arrayFloors.length)
                for (int i = 0; i < arrayFloors.length; i++) {
                    if (!(arrayFloors[i].equals(((Dwelling) object).arrayFloors[i])))
                        return false;
                    return true;
                }
        return false;
    }
    public int hashCode()
    {
        int hash = arrayFloors.length;
        for(int i=0; i<arrayFloors.length;i++)
            hash^=arrayFloors[i].hashCode();
        return hash;
    }

    @Override
    public Object clone() {
        Floor[] newFloors = new Floor[arrayFloors.length];
        for(int i=0; i<arrayFloors.length; i++)
        {
            newFloors[i]=(Floor)arrayFloors[i].clone();
        }
        return new Dwelling(newFloors);
    }

    @Override
    public MyIterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements java.util.Iterator<Floor>
    {
        int current = 0;
        @Override
        public boolean hasNext() {
            if(current<arrayFloors.length)
                return true;
            return false;
        }

        @Override
        public com.company.interfaces.Floor next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return arrayFloors[current++];
        }
    }
}
