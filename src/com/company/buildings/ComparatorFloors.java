package com.company.buildings;

import com.company.interfaces.Floor;

public class ComparatorFloors implements java.util.Comparator<Floor>
{
    @Override
    public int compare(Floor o1, Floor o2) { //сравниваем по площади
        if(o1.getTotalArea()==o2.getTotalArea()) return 0;
        if(o1.getTotalArea()<o2.getTotalArea()) return -1;
        return 1;
    }
}