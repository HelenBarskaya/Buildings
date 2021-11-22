package com.company.buildings.dwelling.hotel;

import com.company.buildings.dwelling.*;
import com.company.interfaces.*;

public class Hotel extends Dwelling implements Building {
    public Hotel(int quantityFloors, int[] quantityFlats) {
        super(quantityFloors, quantityFlats);
    }

    public Hotel(Floor[] arrayFloors) {
        super(arrayFloors);
    }

    public int getStars() {
        int stars = 0;
        for (int i = 0; i < this.getQuantityFloors(); i++)
            if (this.getFloorByNumber(i) instanceof HotelFloor)
                if (stars < ((HotelFloor) this.getFloorByNumber(i)).getStars())
                    stars = ((HotelFloor) this.getFloorByNumber(i)).getStars();
        return stars;
    }

    @Override
    public Space getBestPlace() {
        Space bestSpace = new Flat();
        double areaCoef = 0;
        for (int i = 0; i < this.getQuantityFloors(); i++)
            if (this.getFloorByNumber(i) instanceof HotelFloor)
                for (int j = 0; j < this.getFloorByNumber(i).getQuantitySpaces(); j++)
                    if (areaCoef < this.getFloorByNumber(i).getSpaceByNumber(j).getLivingSpace() * 0.25 * ((HotelFloor) this.getFloorByNumber(i)).getStars()) {
                        bestSpace = this.getFloorByNumber(i).getSpaceByNumber(j);
                        areaCoef = this.getFloorByNumber(i).getSpaceByNumber(j).getLivingSpace() * 0.25 * ((HotelFloor) this.getFloorByNumber(i)).getStars();
                    }
        return bestSpace;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer("Hotel (" + this.getStars() + ", " + this.getQuantityFloors() + ", ");
        for (Floor f : this.getArrayFloors()) {
            str.append(f.toString());
        }
        str.append(")");
        return str.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Hotel)
            if (this.getArrayFloors().length == ((Dwelling) object).getArrayFloors().length)
                for (int i = 0; i < this.getArrayFloors().length; i++) {
                    if (!(this.getFloorByNumber(i).equals(((Hotel) object).getFloorByNumber(i))))
                        return false;
                    return true;
                }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode ^= this.getQuantityFloors();
        for (int i = 0; i < this.getQuantityFloors(); i++)
            if (this.getFloorByNumber(i) instanceof HotelFloor)
                hashCode ^= ((HotelFloor) this.getFloorByNumber(i)).hashCode();
        return hashCode;
    }
}
