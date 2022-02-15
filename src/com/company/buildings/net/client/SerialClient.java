package com.company.buildings.net.client;

import com.company.buildings.Buildings;
import com.company.buildings.factories.DwellingFactory;
import com.company.buildings.factories.HotelFactory;
import com.company.buildings.factories.OfficeFactory;
import com.company.interfaces.Building;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SerialClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9900);

        try (
                Scanner file1 = new Scanner(new FileReader("file1.txt"));
                Scanner file2 = new Scanner(new FileReader("file2.txt"));
        ) {
            try (OutputStream out = client.getOutputStream();
                 Scanner in = new Scanner(client.getInputStream())) {
                while (file1.hasNextLine()&& file2.hasNextLine()) {
                    String type=file2.nextLine();
                    if (type.contains("OfficeBuilding")) Buildings.setBuildingFactory(new OfficeFactory());
                    else if (type.contains("Dwelling")) Buildings.setBuildingFactory(new DwellingFactory());
                    else Buildings.setBuildingFactory(new HotelFactory());
                    Building building = Buildings.readBuilding(file1); //Восстанавливаем объект из файла

                    System.out.println("Передаем объект:\n"+building.toString());
                    Buildings.serializeBuilding(building, out);
                    //Отправляем один объект на сервер
                    System.out.println("Получаем стоимость: ");
                    System.out.println(in.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
    }
}
