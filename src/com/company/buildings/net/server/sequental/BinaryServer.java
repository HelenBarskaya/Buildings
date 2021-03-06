package com.company.buildings.net.server.sequental;

import com.company.buildings.*;
import com.company.buildings.dwelling.*;
import com.company.buildings.factories.DwellingFactory;
import com.company.buildings.factories.HotelFactory;
import com.company.buildings.factories.OfficeFactory;
import com.company.buildings.office.*;
import com.company.exceptions.BuildingUnderArrestException;
import com.company.interfaces.*;

import java.io.*;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

public class BinaryServer {
    private static String buildingAssessment(Building building) throws BuildingUnderArrestException{
        try {
            if(!isArrested()) {
                int price = 0;
                for (Floor f : building)
                    price += f.getTotalArea();
                if (building instanceof Dwelling) return price * 1000+"";
                else if (building instanceof OfficeBuilding) return price * 1500+"";
                return price * 2000+"";
            }else throw new BuildingUnderArrestException();
        }
        catch (BuildingUnderArrestException exception)
        {
            System.out.println(exception.printMessage());
            return exception.printMessage();
        }
    }
    private static boolean isArrested()
    {
        if((int)(Math.random()*10)==0)
            return true;
        else return false;
    }

    public static void main(String[] args) throws IOException {

        try(ServerSocket server = new ServerSocket(8800)) {
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
                        try {
                            String price = buildingAssessment(building);
                            System.out.println("Возвращаем стоимость: " + price);
                            outText.println(price);
                        } catch (BuildingUnderArrestException exception)
                        {
                            outText.println(exception.printMessage());
                        }
                        finally {
                            System.out.println(inText.nextLine());
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
