package com.company.buildings.office;

import com.company.exceptions.SpaceIndexOutOfBoundsException;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class OfficeFloor implements Floor, Serializable, Cloneable, Iterable<Space>, Comparable<Floor> {

    private class Node implements Serializable{
        private Office office = null;
        private Node next = null;
        public Node(){
            office = new Office();
        }
    }
    private int length;
    private Node headMain = new Node(), head, tail, current;
    {
        headMain.next = headMain;
        head = headMain;
        tail = headMain;
        current = headMain;
    }

    private Node getNodeByNumber(int index){
        if (index < 0 || index >= length) throw new SpaceIndexOutOfBoundsException();
        current = head;
        int currentId = 0;
        while(currentId != index){
            current = current.next;
            currentId++;
        }
        return current;
    }

    private void addNodeByNumber (Node a, int index){
        if (index == 0){
            current = headMain;
            Node temp = current;
            a.next = headMain.next;
            headMain.next = a;
            head = a;
            length++;
        }else {
            getNodeByNumber(index - 1);
            Node temp = current;
            a.next = current.next;
            current = temp;
            current.next = a;
            length++;
        }
    }

    private void deleteNodeByNumber(int index){
        getNodeByNumber(index - 1);
        Node temp = current.next;
        current.next = current.next.next;
        length--;
    }

    public OfficeFloor(int floorCount){
        length = floorCount;

        head = new Node();
        headMain.next = head;
        current = head;

        for(int i = 0; i < length - 1; i++){
            current.office = new Office();
            current.next = new Node();
            current = current.next;
        }
        tail = current;
        tail.office = new Office();
        tail.next = headMain;
    }

    public OfficeFloor(Space[] office){
        length = office.length;

        head = new Node();
        headMain.next = head;
        current = head;

        //сейчас указатель на голове
        for (int i = 0; i < length - 1; i++){
            current.office = new Office(office[i]);
            current.next = new Node();
            current = current.next;
        }
        tail = current;
        tail.office = new Office(office[length - 1]);
        tail.next = headMain;
    }

    public OfficeFloor(Floor a){
        length = a.getQuantitySpaces();

        head = new Node();
        headMain.next = head;
        current = head;
        for (int i = 0; i < length - 1; i++){
            current.office = new Office(a.getSpaceByNumber(i));
            current.next = new Node();
            current =  current.next;
        }
        tail = current;
        tail.office = new Office(a.getSpaceByNumber(length - 1));
        tail.next = headMain;
    }

    public int getQuantitySpaces()
    {
        return length;
    }
    public  double getTotalArea()
    {
        current = head;
        int currentId = 0;
        double area = 0;
        while (currentId != length){
            area += current.office.getLivingSpace();
            current = current.next;
            currentId++;
        }
        return area;
    }
    public int getQuantityRooms()
    {
        current = head;
        int sum = 0;
        for(int i=0;i<length;i++){
            sum += current.office.getNumberOfRooms();
            current = current.next;
        }
        return sum;
    }
    public Office[] getArray()
    {
        current = head;
        Office[] array = new Office[this.getQuantitySpaces()];
        for(int i=0; i<length;i++)
        {
            array[i]=current.office;
            current=current.next;
        }
        return array;
    }
    public Space getSpaceByNumber(int num)
    {
        try {
            if(num<length)
                return this.getNodeByNumber(num).office;
            else throw new SpaceIndexOutOfBoundsException();
        } catch (SpaceIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
        return this.getNodeByNumber(this.getQuantitySpaces()-1).office;
    }
    public void setSpaceByNumber(int num, Space office)
    {
        try {
            if(num<this.getQuantitySpaces()) {
                Space currentOffice = this.getNodeByNumber(num).office;
                currentOffice.setNumberOfRooms(office.getNumberOfRooms());
                currentOffice.setLivingSpace(office.getLivingSpace());
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
                Node node = new Node();
                node.office = new Office(office);
                addNodeByNumber(node, num);
            }
            else throw new SpaceIndexOutOfBoundsException();
        }catch (SpaceIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
    }
    public void deleteSpaceByNumber(int num)
    {
        try {
            if(num<this.getQuantitySpaces())
                this.deleteNodeByNumber(num);
            else throw new SpaceIndexOutOfBoundsException();
        }catch (SpaceIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            e.printMessage();
        }
    }
    public Space getBestPlace()
    {
        current=head;
        Space bestSpace=head.office;
        for(int i = 0; i<this.getQuantitySpaces(); i++)
        {
            if(bestSpace.getLivingSpace()<current.office.getLivingSpace())
            {
                bestSpace=current.office;
            }
            current=current.next;
        }
        return bestSpace;
    }

    @Override
    public String toString()
    {
        StringBuffer str = new StringBuffer("OfficeFloor ("+ this.getQuantitySpaces()+", ");
        for (int i=0; i<this.getQuantitySpaces(); i++) {
            str.append(this.getSpaceByNumber(i).toString());
        }
        str.append(")");
        return str.toString();
    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof OfficeFloor)
            if (this.getQuantitySpaces() == ((OfficeFloor) object).getQuantitySpaces())
                for (int i = 0; i < this.getQuantitySpaces(); i++) {
                    if (!(this.getSpaceByNumber(i).equals(((OfficeFloor) object).getSpaceByNumber(i))))
                        return false;
                    return true;
                }
        return false;
    }
    public int hashCode()
    {
        int hash = this.getQuantitySpaces();
        for(int i=0; i<this.getQuantitySpaces();i++)
            hash^=this.getSpaceByNumber(i).hashCode();
        return hash;
    }

    @Override
    public Object clone() {
        Space[] newOffices = new Space[this.getQuantitySpaces()];
        for(int i=0; i<this.getQuantitySpaces(); i++)
        {

            newOffices[i]= (Space)this.getSpaceByNumber(i).clone();
        }
        OfficeFloor newFloor = new OfficeFloor(newOffices);
        return newFloor;
    }

    @Override
    public MyIterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements java.util.Iterator<Space>
    {
        int current;
        @Override
        public boolean hasNext() {
            if(current<length)
                return true;
            return false;
        }

        @Override
        public com.company.interfaces.Space next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return getSpaceByNumber(current++);
        }
    }
    @Override
    public int compareTo(Floor o) {
        return this.getQuantitySpaces()-o.getQuantitySpaces();
    }
    
}

