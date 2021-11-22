package com.company.buildings;
import com.company.interfaces.*;

public class ComparatorSpaces implements java.util.Comparator<Space>
{
    @Override
    public int compare(Space o1, Space o2) {
        return o1.getNumberOfRooms()-o2.getNumberOfRooms();
    }
    //Сравниваем по количеству помещений
}
