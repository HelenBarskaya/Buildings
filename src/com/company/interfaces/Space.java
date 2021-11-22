package com.company.interfaces;

public interface Space extends Comparable<Space>{
    int getNumberOfRooms();
    void setNumberOfRooms(int a);
    double getLivingSpace();
    void setLivingSpace(double a);
    Object clone();
}
