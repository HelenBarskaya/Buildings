package com.company.buildings.dwelling;

import com.company.exceptions.SpaceIndexOutOfBoundsException;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class DwellingFloor implements Floor, Serializable, Cloneable, Iterable<Space>, Comparable<Floor> {
    private Space[] arrayFlats;

    public DwellingFloor(int number) {
        arrayFlats = new Space[number];
        for (int i = 0; i < number; i++) {
            arrayFlats[i] = new Flat();
        }
    }

    public DwellingFloor(Space[] array) {
        arrayFlats = array;
    }

    public int getQuantitySpaces() {
        return arrayFlats.length;
    }

    public double getTotalArea() {
        int area = 0;
        for (Space a : arrayFlats) {
            area += a.getLivingSpace();
        }
        return area;
    }

    public int getQuantityRooms() {
        int quantity = 0;
        for (Space a : arrayFlats) {
            quantity += a.getNumberOfRooms();
        }
        return quantity;
    }

    public Space[] getArray() {
        return arrayFlats;
    }

    public Space getSpaceByNumber(int num) {
        try {
            if (num < arrayFlats.length)
                return arrayFlats[num];
            else throw new SpaceIndexOutOfBoundsException();
        } catch (SpaceIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Квартиры с таким номером не существует");
        }
        return null;
    }

    public void setSpaceByNumber(int num, Space flat) {
        try {
            if (num < arrayFlats.length)
                arrayFlats[num] = flat;
            else throw new SpaceIndexOutOfBoundsException();
        } catch (SpaceIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Квартиры с таким номером не существует");
        }
    }

    public void addSpaceByNumber(int num, Space flat) {
        Flat[] newArrayFlats = new Flat[arrayFlats.length + 1];
        for (int i = 0; i < arrayFlats.length + 1; i++) {
            newArrayFlats[i] = new Flat();
        }
        try {
            if (num == arrayFlats.length) //если квартира добавляется в конец
            {
                for (int i = 0; i < arrayFlats.length; i++) {
                    newArrayFlats[i].setLivingSpace(arrayFlats[i].getLivingSpace());
                    newArrayFlats[i].setNumberOfRooms(arrayFlats[i].getNumberOfRooms());
                }
                newArrayFlats[num].setLivingSpace(flat.getLivingSpace());
                newArrayFlats[num].setNumberOfRooms(flat.getNumberOfRooms());
            } else if (num == 0) //если квартира добавляется в начало
            {
                newArrayFlats[num].setLivingSpace(flat.getLivingSpace());
                newArrayFlats[num].setNumberOfRooms(flat.getNumberOfRooms());
                for (int i = 1; i < newArrayFlats.length; i++) {
                    newArrayFlats[i].setLivingSpace(arrayFlats[i - 1].getLivingSpace());
                    newArrayFlats[i].setNumberOfRooms(arrayFlats[i - 1].getNumberOfRooms());
                }
            } else if (num < arrayFlats.length) //если квартира добавляется в середину
            {
                int i = 0;
                for (; i < num; i++) {
                    newArrayFlats[i].setLivingSpace(arrayFlats[i].getLivingSpace());
                    newArrayFlats[i].setNumberOfRooms(arrayFlats[i].getNumberOfRooms());
                }
                newArrayFlats[num].setLivingSpace(flat.getLivingSpace());
                newArrayFlats[num].setNumberOfRooms(flat.getNumberOfRooms());
                i++;
                for (; i < newArrayFlats.length; i++) {
                    newArrayFlats[i].setLivingSpace(arrayFlats[i - 1].getLivingSpace());
                    newArrayFlats[i].setNumberOfRooms(arrayFlats[i - 1].getNumberOfRooms());
                }
            } else throw new SpaceIndexOutOfBoundsException();
        } catch (SpaceIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Некорректный номер новой квартиры");
        } finally {
            arrayFlats = newArrayFlats;
        }
    }

    public void deleteSpaceByNumber(int num) {
        Flat[] newArrayFlats = new Flat[arrayFlats.length - 1];
        for (int i = 0; i < (arrayFlats.length - 1); i++) {
            newArrayFlats[i] = new Flat();
        }
        try {
            if (num == 0) //если удаляем из начала
            {
                for (int i = 0; i < newArrayFlats.length; i++) {
                    newArrayFlats[i].setNumberOfRooms(arrayFlats[i + 1].getNumberOfRooms());
                    newArrayFlats[i].setLivingSpace(arrayFlats[i + 1].getLivingSpace());
                }
                arrayFlats = newArrayFlats;
            } else if (num == arrayFlats.length - 1) //если удаляем из конца
            {
                for (int i = 0; i < newArrayFlats.length; i++) {
                    newArrayFlats[i].setNumberOfRooms(arrayFlats[i].getNumberOfRooms());
                    newArrayFlats[i].setLivingSpace(arrayFlats[i].getLivingSpace());
                }
                arrayFlats = newArrayFlats;
            } else if (num < arrayFlats.length - 1) //если удаляем из середины
            {
                int i = 0;
                for (; i < num; i++) {
                    newArrayFlats[i].setNumberOfRooms(arrayFlats[i].getNumberOfRooms());
                    newArrayFlats[i].setLivingSpace(arrayFlats[i].getLivingSpace());
                }
                i = ++num;
                for (; i < arrayFlats.length; i++) {
                    newArrayFlats[i - 1].setNumberOfRooms(arrayFlats[i].getNumberOfRooms());
                    newArrayFlats[i - 1].setLivingSpace(arrayFlats[i].getLivingSpace());
                }
                arrayFlats = newArrayFlats;
            } else throw new SpaceIndexOutOfBoundsException();
        } catch (SpaceIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Квартиры с таким номером не существует");
        }
    }

    public Space getBestPlace() {
        Space bestFlat = new Flat();
        for (int i = 0; i < arrayFlats.length; i++) {
            if (arrayFlats[i].getLivingSpace() > bestFlat.getLivingSpace())
                bestFlat = arrayFlats[i];
        }
        return bestFlat;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer("DwellingFloor (" + arrayFlats.length + ", ");
        for (Space s : arrayFlats) {
            str.append(s.toString() + " ");
        }
        str.append(")");
        return str.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof DwellingFloor)
            if (this.arrayFlats.length == ((DwellingFloor) object).arrayFlats.length)
                for (int i = 0; i < arrayFlats.length; i++) {
                    if (!(arrayFlats[i].equals(((DwellingFloor) object).arrayFlats[i])))
                        return false;
                    return true;
                }
        return false;
    }
    @Override
    public int hashCode()
    {
        int hash = arrayFlats.length;
        for(int i=0; i<arrayFlats.length;i++)
            hash^=arrayFlats[i].hashCode();
        return hash;
    }

    @Override
    public Object clone() {
        Space[] newFlats = new Space[arrayFlats.length];
        for(int i=0; i<arrayFlats.length; i++)
        {
            newFlats[i]=(Flat)arrayFlats[i].clone();
        }
        return new DwellingFloor(newFlats);
    }

    @Override
    public MyIterator iterator() {
        return new MyIterator(); //Возвращает итератор по квартирам
    }

    @Override
    public int compareTo(Floor o) {
        return this.getQuantitySpaces()-o.getQuantitySpaces();
    }

    private class MyIterator implements java.util.Iterator<Space>
    {
        int current=0;
        @Override
        public boolean hasNext() {
            if(current<arrayFlats.length)
                return true;
            return false;
        }

        @Override
        public com.company.interfaces.Space next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return arrayFlats[current++];
        }
    }

}
