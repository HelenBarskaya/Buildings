package com.company.buildings;
import com.company.exceptions.InexchangeableFloorsException;
import com.company.exceptions.InexchangeableSpacesException;
import com.company.interfaces.*;

public class PlacementExchanger {
    public static boolean checkExchangeSpaces(Space a, Space b)
    {
        if(a.getLivingSpace()==b.getLivingSpace() && a.getNumberOfRooms()==b.getNumberOfRooms())
            return true;
        return false;
    }
    public static boolean checkExchangeFloors(Floor a, Floor b)
    {
        if(a.getQuantitySpaces()==b.getQuantitySpaces()&&a.getTotalArea()==b.getTotalArea())
            return true;
        return false;
    }
    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException
    {
        try {
            if (checkExchangeSpaces(floor1.getSpaceByNumber(index1), floor2.getSpaceByNumber(index2))) {
                Space buf = floor1.getSpaceByNumber(index1);
                floor1.setSpaceByNumber(index1, floor2.getSpaceByNumber(index2));
                floor2.setSpaceByNumber(index2, buf);
                System.out.println("Помещения заменены");
            } else throw new InexchangeableSpacesException();
        } catch (InexchangeableSpacesException e)
        {
            e.printStackTrace();
            System.out.println("Невозможно обменять помещения");
        }
    }
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException
    {
        try {
            if (checkExchangeFloors(building1.getFloorByNumber(index1), building2.getFloorByNumber(index2))) {
                Floor buf = building1.getFloorByNumber(index1);
                building1.setFloorByNumber(index1, building2.getFloorByNumber(index2));
                building2.setFloorByNumber(index2, buf);
                System.out.println("Этажи заменены");
            } else throw new InexchangeableFloorsException();
        } catch (InexchangeableFloorsException e)
        {
            e.printStackTrace();
            System.out.println("Невозможно обменять этажи");
        }
    }
}
