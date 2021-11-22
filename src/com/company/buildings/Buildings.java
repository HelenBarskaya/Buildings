package com.company.buildings;

import com.company.buildings.dwelling.*;
import com.company.buildings.dwelling.hotel.Hotel;
import com.company.buildings.dwelling.hotel.HotelFloor;
import com.company.buildings.factories.*;
import com.company.interfaces.*;

import java.io.*;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Buildings implements Serializable {
    private static BuildingFactory factory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory f) {
        factory = f;
    }
    public static Space createSpace(double area)
    {
        return factory.createSpace(area);
    }
    public static Space createSpace(int roomsCount, double area)
    {
        return factory.createSpace(roomsCount,area);
    }
    public static Floor createFloor(int spacesCount) {
        return factory.createFloor(spacesCount);
    }
    public static Floor createFloor(Space[] spaces) {
        return factory.createFloor(spaces);
    }
    public static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return factory.createBuilding(floorsCount,spacesCounts);
    }
    public static Building createBuilding(Floor[] floors) {
        return factory.createBuilding(floors);
    }

    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        int floorsCount = building.getQuantityFloors();
        DataOutputStream stream = new DataOutputStream(out);
        stream.writeInt(floorsCount); //количество этажей
        for (int i = 0; i < building.getQuantityFloors(); i++) {
            stream.writeInt(building.getFloorByNumber(i).getQuantitySpaces()); //количество квартир на этажах
            for (int j = 0; j < building.getFloorByNumber(i).getQuantitySpaces(); j++) {
                stream.writeInt(building.getFloorByNumber(i).getSpaceByNumber(j).getNumberOfRooms()); //количество комнат у определённой квартиры
                stream.writeDouble(building.getFloorByNumber(i).getSpaceByNumber(j).getLivingSpace()); //площадь определённой комнаты на этаже
            }
        }
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream stream = new DataInputStream(in);
        int floorCount = stream.readInt();
        Floor[] a = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            a[i] = createFloor(stream.readInt());//количество квартир на этаже
            for (int j = 0; j < a[i].getQuantitySpaces(); j++) {
                a[i].getSpaceByNumber(j).setNumberOfRooms(stream.readInt());
                a[i].getSpaceByNumber(j).setLivingSpace(stream.readDouble());
            }
        }
        return createBuilding(a);
    }

    public static void writeBuilding(Building building, Writer out) throws IOException // символьные
    {
        PrintWriter writer = new PrintWriter(out);
        String output = "";
        output += building.getQuantityFloors();

        for (int i = 0; i < building.getQuantityFloors(); i++) {
            output += " ";
            output += building.getFloorByNumber(i).getQuantitySpaces(); //количество квартир на этажах
            for (int j = 0; j < building.getFloorByNumber(i).getQuantitySpaces(); j++) {
                output += " ";
                output += building.getFloorByNumber(i).getSpaceByNumber(j).getNumberOfRooms(); //количество комнат у определённой квартиры
                output += " ";
                output += building.getFloorByNumber(i).getSpaceByNumber(j).getLivingSpace(); //площадь определённой комнаты на этаже
            }
        }
        writer.println(output);
    }

    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();

        int floorCount = (int) tokenizer.nval;

        Floor[] a = new Floor[floorCount];

        for (int i = 0; i < floorCount; i++) {
            tokenizer.nextToken();
            a[i]= createFloor((int) tokenizer.nval);//количество квартир на этаже
            for (int j = 0; j < a[i].getQuantitySpaces(); j++) {
                tokenizer.nextToken();
                a[i].getSpaceByNumber(j).setNumberOfRooms((int) tokenizer.nval);
                tokenizer.nextToken();
                a[i].getSpaceByNumber(j).setLivingSpace((double) tokenizer.nval);
            }
        }
        return createBuilding(a);
    }

    public static Building readBuilding(Scanner in) throws IOException {
        int floorCount = in.nextInt();
        Floor[] a = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) { //количество этажей
            int numberOfSpaces = in.nextInt();
            a[i]=createFloor(numberOfSpaces); //количество помещений на этаже
            for (int j = 0; j < numberOfSpaces; j++) {
                int numberRooms = in.nextInt();
                a[i].getSpaceByNumber(j).setNumberOfRooms(numberRooms);
                in.useLocale(Locale.UK);
                double area= in.nextDouble();
                a[i].getSpaceByNumber(j).setLivingSpace(area);
            }
        }
        return createBuilding(a);
    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(building);
    }

    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        return (Building) objectInputStream.readObject();
    }

    public static void writeBuildingFormat(Building building, Writer out) {
        Formatter formatter = new Formatter(out);
        formatter.format("%d ", building.getQuantityFloors());

        for (int i = 0; i < building.getQuantityFloors(); i++) {
            formatter.format("%2d ", building.getFloorByNumber(i).getQuantitySpaces());
            for (int j = 0; j < building.getFloorByNumber(i).getQuantitySpaces(); j++) {
                formatter.format("%2d ", building.getFloorByNumber(i).getSpaceByNumber(j).getNumberOfRooms());
                formatter.format("%.1f", building.getFloorByNumber(i).getSpaceByNumber(j).getLivingSpace());
            }
            formatter.format(" ");
        }
    }

    public static Space[] sortSpaceByArea(Floor floor) { //CompareTo 1
        Space[] array = floor.getArray();
        Space buf;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < (array.length - i); j++) {
                if (array[j - 1].compareTo(array[j]) >0) {
                    buf = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = buf;
                }
            }
        }
        return array;
    }

    public static Floor[] sortFloorBySpaces(Building building) {//CompareTo 2
        Floor[] array = building.getArrayFloors();
        Floor buf;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < (array.length - i); j++) {
                if (array[j - 1].compareTo(array[j]) > 0) {
                    buf = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = buf;
                }
            }
        }
        return array;
    }
    public static <T extends Comparable<T>> T[] sortCompareToParameter(T[] objects) { //CompareTo параметризованный
        for (int i = 0; i < objects.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < objects.length; j++) {
                if (objects[j].compareTo(objects[minIndex]) < 0)
                    minIndex = j;
            }
            T swapBuf = objects[i];
            objects[i] = objects[minIndex];
            objects[minIndex] = swapBuf;
        }
        return objects;
    }

    public static Floor[] sortFloorComparator(Floor[] objects) { //с компаратором 1
        ComparatorFloors comparatorFloors = new ComparatorFloors();
        for (int i = 0; i < objects.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < objects.length; j++) {
                if (comparatorFloors.compare(objects[j], objects[minIndex]) < 0)
                    minIndex = j;
            }
            Floor swapBuf = objects[i];
            objects[i] = objects[minIndex];
            objects[minIndex] = swapBuf;
        }
        return objects;
    }
    public static Space[] sortSpacesComparator(Space[] objects) { //с компаратором 2
        ComparatorSpaces comparatorSpaces = new ComparatorSpaces();
        for (int i = 0; i < objects.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < objects.length; j++) {
                if (comparatorSpaces.compare(objects[j], objects[minIndex]) < 0)
                    minIndex = j;
            }
            Space swapBuf = objects[i];
            objects[i] = objects[minIndex];
            objects[minIndex] = swapBuf;
        }
        return objects;
    }

    public static <T> T[] sortFloorComparator(T[] objects, Comparator<T> comparator) { //Параметризованный с компаратором
        T buf;
        for (int i = 0; i < objects.length; i++) {
            for (int j = 1; j < (objects.length - i); j++) {
                if (comparator.compare(objects[j - 1], objects[j]) > 0) {
                    buf = objects[j - 1];
                    objects[j - 1] = objects[j];
                    objects[j] = buf;
                }
            }
        }
        return objects;
    }
    Floor synchronizedFloor (Floor floor)
    {
        return new SynchronizedFloor(floor);
    }
}

