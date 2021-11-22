package com.company.interfaces;

public interface Floor extends Iterable<Space>, Comparable<Floor> {
    int getQuantitySpaces();
    double getTotalArea();
    int getQuantityRooms();
    Space[] getArray();
    Space getSpaceByNumber(int n);
    void setSpaceByNumber(int n,Space s);
    void addSpaceByNumber(int n, Space s);
    void deleteSpaceByNumber(int n);
    Space getBestPlace();
    Object clone();
}
