package com.company.buildings.dwelling.hotel;

import com.company.buildings.dwelling.*;
import com.company.interfaces.*;

import java.util.Objects;

public class HotelFloor extends DwellingFloor {
    private int stars;
    private static final int DEFAULT_STARS = 1;

    public HotelFloor(int number) {
        super(number);
        stars = DEFAULT_STARS;
    }

    public HotelFloor(Space[] array) {
        super(array);
        stars = DEFAULT_STARS;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int n) {
        stars = n;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer("HotelFloor (" + stars + ", " + this.getQuantitySpaces() + ", ");
        for (Space s : this.getArray()) {
            str.append(s.toString() + " ");
        }
        str.append(")");
        return str.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof HotelFloor) {
            if(this.stars == ((HotelFloor) object).stars) { //сравниваем звездность
                if (this.getArray().length == ((HotelFloor) object).getArray().length) { //сравниваем кол-во помещений
                    for (int i = 0; i < this.getArray().length; i++) {
                        if (!(this.getArray()[i].equals(((HotelFloor) object).getArray()[i]))) //сравниваем каждое помещение
                            return false;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode() ^ stars;
        return hash;
    }
}
