package com.company.buildings.office;

import com.company.exceptions.*;
import com.company.interfaces.*;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class OfficeBuilding implements Building, Serializable, Cloneable, Iterable<Floor> {
    private class Node implements Serializable{
        Floor officeFloor = null;
        private Node next = null;
        private Node prev = null;

        public Node() {}
    }

    private int length, currentPl;
    private Node headMain = new Node(), tail, head, current;
    {
        headMain.next = headMain;
        headMain.prev = headMain;
        tail = headMain;
        head = headMain;
        current = headMain;
    }

    private Node getNodeByNumber(int index){
        if (index < 0 || index >= length) throw new FloorIndexOutOfBoundsException();
        int fromTail = length - index - 1;
        int fromHead = index;

        if (fromTail < fromHead){
            current = tail;
            currentPl = length - 1;
            while(currentPl != index){
                current = current.prev;
                currentPl--;
            }
        } else if (fromTail >= fromHead){
            current = head;
            currentPl = 0;
            while(currentPl != index){
                current = current.next;
                currentPl++;
            }
        }
        return current;
    }
    private void addNodeByNumber (Node a, int index){
        getNodeByNumber(index);
        a.next = current;
        a.prev = current.prev;
        current.prev.next = a;
        current.prev = a;

        current = a;
        length++;
    }
    private void deleteNodeByNumber(int index){
        getNodeByNumber(index);
        current.prev.next = current.next;
        current.next.prev = current.prev;
        current = current.prev;
        currentPl--;
        length--;
    }
    public OfficeBuilding(int floorCounts, int[] officeCounts){
        length = floorCounts;
        currentPl = 0;

        headMain.next = head;
        head.prev = headMain;
        head = new Node();
        current = head;

        // голова уже ссылается на headMain из-за инициализации

        while (currentPl != length - 1){
            current.officeFloor = new OfficeFloor(officeCounts[currentPl]);
            current.next = new Node();
            current.next.prev = current;
            current = current.next;
            currentPl++;
        }
        tail = current;
        tail.officeFloor = new OfficeFloor(officeCounts[length - 1]);
        tail.next = headMain; //хвост -> голова
        tail.next.prev = tail; //голова -> хвост
        //сейчас current на хвосте, currentPl = 4;
    }

    public OfficeBuilding(Floor... a){
        length = a.length;
        currentPl = 0;

        headMain.next = head;
        head.prev = headMain;
        head = new Node();
        current = head;

        while (currentPl != length - 1){
            current.officeFloor = new OfficeFloor(a[currentPl]);
            current.next = new Node();
            current.next.prev = current;
            current = current.next;
            currentPl++;
        }
        tail = current;
        tail.officeFloor = new OfficeFloor(a[length - 1]);
        tail.next = headMain;
        tail.next.prev = tail;
    }

    public int getQuantityFloors() {
        return length;
    }

    public int getQuantitySpaces() {
        int number = 0;
        for (int i = 0; i < this.getQuantityFloors(); i++) {
            number += this.getNodeByNumber(i).officeFloor.getQuantitySpaces();
        }
        return number;
    }

    public int getQuantityRooms() {
        int num=0;
        for (int i = 0; i < this.getQuantityFloors(); i++) {
            num+=this.getFloorByNumber(i).getQuantityRooms();
        }
        return num;
    }

    public double getTotalArea() {
        int area = 0;
        for (int i = 0; i < this.getQuantityFloors(); i++) {
            area += this.getNodeByNumber(i).officeFloor.getTotalArea();
        }
        return area;
    }

    public Floor[] getArrayFloors() {
        Floor[] array = new Floor[this.getQuantityFloors()];
        for (int i = 0; i < array.length; i++) {
            array[i] = this.getFloorByNumber(i);
        }
        return array;
    }

    public Floor getFloorByNumber(int num) {
        try {
            if(num<this.getQuantityFloors())
                return this.getNodeByNumber(num).officeFloor;
            else throw new FloorIndexOutOfBoundsException();
        }catch (FloorIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
        return this.getFloorByNumber(this.getQuantityFloors()-1);
    }

    public Space getSpaceByNumber(int num) {
        try {
            if(num<this.getQuantitySpaces()) {
                int k = 0;
                current = head;
                currentPl = 0;
                for (int i=0; i < length; i++) {
                    for(int j = 0; j < current.officeFloor.getQuantitySpaces(); j++, k++){
                        if (k == num){
                            return (Office) current.officeFloor.getSpaceByNumber(j);
                        }
                    }
                    current = current.next;
                    currentPl++;
                }
            }else throw new SpaceIndexOutOfBoundsException();
        }catch (SpaceIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
        return this.getSpaceByNumber(this.getQuantitySpaces()-1);
    }
    public void setFloorByNumber(int num, Floor floor)
    {
        try {
            if(num<this.getQuantityFloors()) {
                Floor ret = getNodeByNumber(num).officeFloor;
                current.officeFloor = new OfficeFloor(floor);
            } else throw new FloorIndexOutOfBoundsException();
        } catch (FloorIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
    }
    public void setSpaceByNumber(int num, Space office)
    {
        try {
            if(num<this.getQuantitySpaces()) {
                int numFloor = 0;
                for (; numFloor < this.getQuantityFloors(); numFloor++) {
                    if (num > this.getFloorByNumber(numFloor).getQuantitySpaces()) {
                        num -= this.getFloorByNumber(numFloor).getQuantitySpaces();
                    } else if (num <= this.getFloorByNumber(numFloor).getQuantitySpaces()) {
                        this.getFloorByNumber(numFloor).setSpaceByNumber(num, office);
                        break;
                    }
                }
            } else throw new SpaceIndexOutOfBoundsException();
        }catch (SpaceIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
    }
    public void addSpaceByNumber(int num, Space office)
    {
        try {
            if(num<=this.getQuantitySpaces()) {
                int numFloor = 0;
                for (; numFloor < this.getQuantityFloors(); numFloor++) { //Ищем нужный этаж
                    if (num > this.getFloorByNumber(numFloor).getQuantitySpaces()) {
                        num -= this.getFloorByNumber(numFloor).getQuantitySpaces();
                    } else if (num <= this.getFloorByNumber(numFloor).getQuantitySpaces()) { //Нашли
                        this.getFloorByNumber(numFloor).addSpaceByNumber(num, office);
                        break;
                    }
                }
            } else throw new SpaceIndexOutOfBoundsException();
        }catch (SpaceIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
    }
    public void deleteSpaceByNumber(int num)
    {
        try {
            if(num<this.getQuantitySpaces()) {
                int numFloor = 0;
                for (; numFloor < this.getQuantityFloors(); numFloor++) { //Ищем нужный этаж
                    if (num > this.getFloorByNumber(numFloor).getQuantitySpaces()) {
                        num -= this.getFloorByNumber(numFloor).getQuantitySpaces();
                    } else if (num <= this.getFloorByNumber(numFloor).getQuantitySpaces()) { //Нашли
                        this.getFloorByNumber(numFloor).deleteSpaceByNumber(num);
                        break;
                    }
                }
            }else throw new SpaceIndexOutOfBoundsException();
        }catch (SpaceIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
    }
    public Space getBestPlace()
    {
        Space bestSpace=new Office();
        for(int i = 0; i< this.getQuantityFloors(); i++)
            if(bestSpace.getLivingSpace()<this.getFloorByNumber(i).getBestPlace().getLivingSpace())
                bestSpace=this.getFloorByNumber(i).getBestPlace();
        return bestSpace;
    }
    public Space[] sortSpaces()
    {
        Space[] offices = new Space[this.getQuantitySpaces()];
        int n=0;
        for(int i = 0; i<this.getQuantityFloors(); i++)//Заполняем массив
            for(int j = 0; j<this.getFloorByNumber(i).getQuantitySpaces(); j++,n++)
                offices[n]=this.getFloorByNumber(i).getSpaceByNumber(j);
        for (n = offices.length - 1; n >= 1; n--) // Пузырьковая сортировка
        {
            for (int j = 0; j < n; j++) {
                if (offices[j].getLivingSpace() < offices[j + 1].getLivingSpace()) {
                    Space buffer = offices[j];
                    offices[j] = offices[j + 1];
                    offices[j + 1] = buffer;
                }
            }
        }
        return offices;
    }
    @Override
    public String toString()
    {
        StringBuffer str = new StringBuffer("OfficeBuilding ("+ this.getQuantityFloors()+", ");
        for (int i=0; i<this.getQuantityFloors();i++) {
            str.append(this.getFloorByNumber(i).toString());
        }
        str.append(")");
        return str.toString();
    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof OfficeBuilding)
            if (this.getQuantityFloors() == ((OfficeBuilding) object).getQuantityFloors())
                for (int i = 0; i < this.getQuantityFloors(); i++) {
                    if (!(this.getFloorByNumber(i).equals(((OfficeBuilding) object).getFloorByNumber(i))))
                        return false;
                    return true;
                }
        return false;
    }
    public int hashCode()
    {
        int hash = this.getQuantityFloors();
        for(int i=0; i<this.getQuantityFloors();i++)
            hash^=this.getFloorByNumber(i).hashCode();
        return hash;
    }

    @Override
    public Object clone() {
        Floor[] newFloors = new Floor[this.getQuantityFloors()];
        for(int i=0; i<this.getQuantityFloors(); i++)
        {

            newFloors[i]=(Floor) this.getFloorByNumber(i).clone();
        }
        OfficeBuilding newBuilding = new OfficeBuilding(newFloors);
        return newBuilding;
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
            if(current<length)
                return true;
            return false;
        }

        @Override
        public Floor next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return getFloorByNumber(current++);
        }
    }
}
