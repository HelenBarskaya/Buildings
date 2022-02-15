package com.company;

import com.company.exceptions.*;
import com.company.graphicalInterface.MainFrame;

import javax.swing.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws InexchangeableSpacesException, IOException {
       /* Flat flat1 = new Flat();
        Flat flat2 = new Flat(300);
        Flat[] arrayFlats1 = new Flat[]{flat1, flat2};
        DwellingFloor floor1 = new DwellingFloor(arrayFlats1);
        DwellingFloor floor2 = new DwellingFloor(3);
        Flat flat3 = new Flat(4, 230);
        Flat flat4 = new Flat(3, 53);
        Flat[] arrayFlats2 = new Flat[]{flat3, flat4};
        DwellingFloor floor3 = new DwellingFloor(arrayFlats2);

        DwellingFloor[] floors = new DwellingFloor[]{floor1, floor2, floor3};
        Dwelling house = new Dwelling(floors);
        //Создание дома на 3 этажа на 2,3,2 квартиры

        System.out.println("ПОИСК КВАРТИРЫ ПО НОМЕРУ. Данные квартиры 0:");
        Flat f = house.getFlatByNumber(0);
        if (f != null)
            System.out.println("Площадь: " + f.getLivingSpace() + ", количество комнат: " + f.getNumberOfRooms());
        else System.out.println("Такой квартиры нет");
        System.out.println("Данные квартиры 6:");
        f = house.getFlatByNumber(6);
        if (f != null)
            System.out.println("Площадь: " + f.getLivingSpace() + ", количество комнат: " + f.getNumberOfRooms());
        else System.out.println("Такой квартиры нет");
        Flat[] sortFlats = house.sortFlats();
        System.out.println();
        System.out.println("СОРТИРОВКА МАССИВА КВАРТИР. Площади квартир в порядке убывания:");
        for (int i = 0; i < sortFlats.length; i++) {
            System.out.print(sortFlats[i].getLivingSpace() + ", ");
        }
        System.out.println();
        System.out.println("ЛУЧШАЯ КВАРТИРА:");
        f = house.getBestSpace();
        System.out.println("Площадь: " + f.getLivingSpace() + ", количество комнат: " + f.getNumberOfRooms());
        System.out.println();
        System.out.println("ПОЛУЧЕНИЕ ДАННЫХ. Общее количество этажей: " + house.getTotalNumberFloors());
        System.out.println("Общее количество квартир: " + house.getTotalNumberFlats());
        System.out.println("Общее количество комнат: " + house.getTotalNumberRooms());
        System.out.println("Общая площадь: " + house.getTotalArea());
        System.out.println();

        System.out.println("ДОБАВЛЕНИЕ КВАРТИРЫ ПО НОМЕРУ. Данные квартиры 4:");
        f = house.getFlatByNumber(4);
        System.out.println("Площадь: " + f.getLivingSpace() + ", количество комнат: " + f.getNumberOfRooms());
        house.addFlatByNumber(4, new Flat(3, 100));
        System.out.println("Добавим квартиру 4, данные новой квартиры 4:");
        f = house.getFlatByNumber(4);
        System.out.println("Площадь: " + f.getLivingSpace() + ", количество комнат: " + f.getNumberOfRooms());
        System.out.println("Данные квартиры 5:");
        f = house.getFlatByNumber(5);
        System.out.println("Площадь: " + f.getLivingSpace() + ", количество комнат: " + f.getNumberOfRooms());
        System.out.println("Общее количество этажей: " + house.getTotalNumberFloors());
        System.out.println("Общее количество квартир: " + house.getTotalNumberFlats());
        System.out.println("Общее количество комнат: " + house.getTotalNumberRooms());
        System.out.println("Общая площадь: " + house.getTotalArea());
        System.out.println();

        System.out.println("УДАЛЕНИЕ КВАРТИРЫ ПО НОМЕРУ. Удалим квартиру 4.");
        house.deleteFlatByNumber(4);
        System.out.println("Данные новой квартиры 4:");
        f = house.getFlatByNumber(4);
        System.out.println("Площадь: " + f.getLivingSpace() + ", количество комнат: " + f.getNumberOfRooms());
        System.out.println("Общее количество этажей: " + house.getTotalNumberFloors());
        System.out.println("Общее количество квартир: " + house.getTotalNumberFlats());
        System.out.println("Общее количество комнат: " + house.getTotalNumberRooms());
        System.out.println("Общая площадь: " + house.getTotalArea());
        System.out.println();*/
       // OfficeFloor floor = new OfficeFloor(5);
       // Office newOffice = new Office(555);
        /*for(int i=0; i<5; i++)
        {
            System.out.println("Номер офиса: "+i+", количество комнат: "+floor.getOfficeByNumber(i).getNumberOfRooms()+", площадь: "+floor.getOfficeByNumber(i).getLivingSpace()+", адрес в памяти:"+floor.getOfficeByNumber(i));
        }
        System.out.println();
        floor.addOfficeByNumber(5,new Office());
        for(int i=0; i<6; i++)
        {
            System.out.println("Номер офиса: "+i+", количество комнат: "+floor.getOfficeByNumber(i).getNumberOfRooms()+", площадь: "+floor.getOfficeByNumber(i).getLivingSpace()+", адрес в памяти:"+floor.getOfficeByNumber(i));
        }
        System.out.println();
        floor.deleteOfficeByNumber(5);
        for(int i=0; i<5; i++)
        {
            System.out.println("Номер офиса: "+i+", количество комнат: "+floor.getOfficeByNumber(i).getNumberOfRooms()+", площадь: "+floor.getOfficeByNumber(i).getLivingSpace()+", адрес в памяти:"+floor.getOfficeByNumber(i));
        }
        System.out.println(floor.getQuantityRooms());*/


       /* Floor floor = new OfficeFloor(5);
        Floor floor1 = new OfficeFloor(3);
        Floor floor2 = new OfficeFloor(4);
        Space newOffice = new Office(555);
        Building building = new OfficeBuilding(floor, floor1, floor2);//трехэтажное здание
        System.out.println(building.getQuantityFloors());
        System.out.println(building.getQuantitySpaces());
        System.out.println(building.getTotalArea());
        Floor[] floors = building.getArrayFloors();
        for (int i = 0; i < floors.length; i++) {
            System.out.println("Этаж " + i + ". Количество комнат: " + floors[i].getQuantitySpaces() + ", общая площадь: " + floors[i].getTotalArea());
        }
        Space office = building.getSpaceByNumber(3);//Получить 8 квартиру
        System.out.println("Квартира 3. Количество комнат: " + office.getNumberOfRooms() + ", площадь: " + office.getLivingSpace());
        building.setFloorByNumber(1, floor);
        office = building.getSpaceByNumber(6);
        System.out.println("Изменили этаж 1. \nКвартира 6. Количество комнат: " + office.getNumberOfRooms() + ", площадь: " + office.getLivingSpace());
        building.setSpaceByNumber(6, newOffice);
        office = building.getSpaceByNumber(6);
        System.out.println("Новая квартира 6. Количество комнат: " + office.getNumberOfRooms() + ", площадь: " + office.getLivingSpace());


        building.addSpaceByNumber(6, new Office());
        office = building.getSpaceByNumber(6);
        System.out.println("Новая (дефолтная) квартира 6. Количество комнат: " + office.getNumberOfRooms() + ", площадь: " + office.getLivingSpace());
        office = building.getSpaceByNumber(7);
        System.out.println("Квартира 7. Количество комнат: " + office.getNumberOfRooms() + ", площадь: " + office.getLivingSpace());
        building.deleteSpaceByNumber(6);
        office = building.getSpaceByNumber(6);
        System.out.println("Удалим квартиру 6, теперь новая квартира 6. Количество комнат: " + office.getNumberOfRooms() + ", площадь: " + office.getLivingSpace());
        Space[] sortedArray = building.sortSpaces();
        System.out.println("Площади по убыванию:");
        for (int i = 0; i < sortedArray.length; i++)
            System.out.println(sortedArray[i].getLivingSpace());
        PlacementExchanger.exchangeFloorRooms(building.getFloorByNumber(0), 0, building.getFloorByNumber(1), 0);
        OutputStream out1 = new FileOutputStream("БайтовыйВывод.txt");
        Buildings.outputBuilding(building, out1);
        out1.close();

        Writer out2 = new FileWriter("ТекстовыйВывод.txt");
        Buildings.writeBuilding(building, out2);
        out2.close();

        InputStream in1 = new FileInputStream("БайтовыйВывод.txt");
        Building res = Buildings.inputBuilding(in1);
        in1.close();

        Writer out3 = new FileWriter("СчиталиБайтыВывелиВФайл.txt");
        Buildings.writeBuilding(res, out3);
        out3.close();

        try (FileOutputStream serializeOut = new FileOutputStream("Сериализация.txt")){
            Buildings.serializeBuilding(building, serializeOut);
        } catch (IOException e) {
            System.out.println("Ошибка!");
        }
        Building deserializeBuilding;
        try (FileInputStream deserialize = new FileInputStream("Сериализация.txt")){
            deserializeBuilding = Buildings.deserializeBuilding(deserialize);
            System.out.println(deserializeBuilding.toString());
            if(deserializeBuilding.equals(building))
                System.out.println("Сериализованный и десериализованный объекты идентичны!");
        } catch (IOException|ClassNotFoundException e) {
            System.out.println("Ошибка!");
        }

        System.out.println("Хеш-код: "+building.hashCode());
        Building cloneBuilding = (Building) building.clone();
        if(cloneBuilding.equals(building))
            System.out.println("Клонированное и исходное здание идентичны!");

        for(int i=0; i<building.getQuantitySpaces(); i++)
        {
            int hash=building.getSpaceByNumber(i).hashCode();
            System.out.println(hash);
        }*/



        //PRACTICE 6

       /* Space flat1 = new Flat();
        Space flat2 = new Flat(300);
        Space[] arrayFlats1 = new Space[]{flat1, flat2};
        Floor floor1 = new DwellingFloor(arrayFlats1);
        Floor floor2 = new DwellingFloor(3);
        Flat flat3 = new Flat(4, 230);
        Flat flat4 = new Flat(3, 53);
        Flat[] arrayFlats2 = new Flat[]{flat3, flat4};
        Floor floor3 = new DwellingFloor(arrayFlats2);
        Floor[] floors = new Floor[]{floor1, floor2, floor3};
        Building house = Buildings.createBuilding(floors);
        //Создание дома на 3 этажа на 2,3,2 квартиры

        Buildings.setBuildingFactory(new OfficeFactory());
        Floor floor00 = new OfficeFloor(5);
        Floor floor11 = new OfficeFloor(3);
        Floor floor22 = new OfficeFloor(4);
        Floor[] officeFloors=new Floor[]{floor00, floor11, floor22};
        Building officeBuilding = Buildings.createBuilding(officeFloors);//трехэтажное офисное здание



        for (Floor a: house) {
            System.out.println(a.getQuantitySpaces());
        }
        System.out.println("\nСОРТИРОВКА ЭТАЖЕЙ CompareTo");
        Floor[] array = Buildings.sortFloorBySpaces(house);
        for(int i=0; i< array.length; i++)
            System.out.println(array[i].getQuantitySpaces());

        System.out.println("\nСОРТИРОВКА ЭТАЖЕЙ ПАРАМЕТРИЗОВАННЫМ CompareTo");
        Floor[] array2 = Buildings.sortCompareToParameter(house.getArrayFloors());
        for(int i=0; i< array2.length; i++)
            System.out.println(array2[i].getQuantitySpaces());

        System.out.println("\nСОРТИРОВКА ПОМЕЩЕНИЙ ПАРАМЕТРИЗОВАННЫМ CompareTo");
        Space[] spaces = Buildings.sortCompareToParameter(arrayFlats2);
        for(int i=0; i< spaces.length; i++)
            System.out.println(spaces[i].getLivingSpace());

        System.out.println("\nСОРТИРОВКА ЭТАЖЕЙ ПАРАМЕТРИЗОВАННЫМ КОМПАРАТОРОМ");
        Floor[] array1 = Buildings.sortFloorComparator(house.getArrayFloors(), new ComparatorFloors());
        for(int i=0; i< array1.length; i++)
            System.out.println(array1[i].getTotalArea());

        System.out.println("\nСОРТИРОВКА ПОМЕЩЕНИЙ ПАРАМЕТРИЗОВАННЫМ КОМПАРАТОРОМ");
        Space[] spaces1 = Buildings.sortFloorComparator(arrayFlats2, new ComparatorSpaces());
        for(int i=0; i< spaces1.length; i++)
            System.out.println(spaces1[i].getNumberOfRooms());
*/


        //PRACTICE 7
        /*Floor floor = Buildings.createFloor(10);
        Repairer repairerThread = new Repairer(floor);
        Cleaner cleanerThread = new Cleaner(floor);
        repairerThread.start();
        cleanerThread.start();

        Floor floor = Buildings.createFloor(10);
        MySemaphore semaphore= new MySemaphore();
        Runnable r1 = new SequentalRepairer(floor,semaphore);
        Thread t1 = new Thread(r1);
        Runnable r2 = new SequentalCleaner(floor, semaphore);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();*/

    }
}
