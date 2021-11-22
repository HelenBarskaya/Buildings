package com.company.interfaces;

public interface Building extends Iterable<Floor>{
    int getQuantityFloors();
    int getQuantitySpaces();
    int getQuantityRooms();
    double getTotalArea();
    Floor[] getArrayFloors();
    Floor getFloorByNumber(int n);
    void setFloorByNumber(int n, Floor f);
    Space getSpaceByNumber(int n);
    void setSpaceByNumber(int n, Space s);
    void addSpaceByNumber(int n, Space s);
    void deleteSpaceByNumber(int n);
    Space getBestPlace();
    Space[] sortSpaces();
    Object clone();
}
