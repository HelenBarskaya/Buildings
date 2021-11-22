package com.company.buildings.net.server.sequental;

import com.company.buildings.*;
import com.company.buildings.dwelling.*;
import com.company.buildings.factories.DwellingFactory;
import com.company.buildings.factories.HotelFactory;
import com.company.buildings.factories.OfficeFactory;
import com.company.buildings.office.*;
import com.company.interfaces.*;

import java.io.*;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

public class BinaryServer {
    private static int buildingAssessment(Building building) {
        int price = 0;
        for (Floor f : building)
            price += f.getTotalArea();
        if (building instanceof Dwelling) return price * 1000;
        else if (building instanceof OfficeBuilding) return price * 1500;
        return price * 2000;
    }

    public static void main(String[] args) throws IOException {

        try(ServerSocket server = new ServerSocket(8000)) {
            System.out.println("Сервер создан!!!");
            try(Socket socket = server.accept()) {
                System.out.println("Клиент подключен!!!");
                try (
                        Scanner inText = new Scanner(socket.getInputStream());
                        PrintWriter outText = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    while(inText.hasNextLine()) {
                        String type= inText.nextLine();
                        System.out.println("Получен тип: " + type);
                        if (type.contains("OfficeBuilding")) Buildings.setBuildingFactory(new OfficeFactory());
                        else if (type.contains("Dwelling")) Buildings.setBuildingFactory(new DwellingFactory());
                        else Buildings.setBuildingFactory(new HotelFactory());
                        Building building = Buildings.readBuilding(inText);
                        System.out.println("Создан объект: ");
                        System.out.println(building.toString());
                        System.out.println("Возвращаем стоимость: " + buildingAssessment(building));
                        outText.println(buildingAssessment(building));
                        System.out.println(inText.nextLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
