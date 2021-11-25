package com.company.buildings.net.server.sequental;

import com.company.buildings.Buildings;
import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.factories.DwellingFactory;
import com.company.buildings.factories.HotelFactory;
import com.company.buildings.factories.OfficeFactory;
import com.company.buildings.office.OfficeBuilding;
import com.company.exceptions.BuildingUnderArrestException;
import com.company.interfaces.Building;
import com.company.interfaces.Floor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SerialServer {
    private static String buildingAssessment(Building building) throws BuildingUnderArrestException {
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

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try(ServerSocket server = new ServerSocket(9900)) {
            System.out.println("Сервер создан!!!");
            try(Socket socket = server.accept()) {
                System.out.println("Клиент подключен!!!");
                try (
                        InputStream inText = socket.getInputStream();
                        PrintWriter outText = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    while(socket.isConnected()) {
                        Building building = Buildings.deserializeBuilding(inText);
                        System.out.println("Получен объект: ");
                        System.out.println(building.toString());
                        try {
                            String price = buildingAssessment(building);
                            System.out.println("Возвращаем стоимость: " + price);
                            outText.println(price);
                        } catch (BuildingUnderArrestException exception)
                        {
                            outText.println(exception.printMessage());
                        }
                    }
                } catch (IOException e) {
                    socket.close();
                }
            }
        }
    }
}
