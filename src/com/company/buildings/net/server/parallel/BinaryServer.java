package com.company.buildings.net.server.parallel;

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
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BinaryServer {
    public static void main(String[] args) throws IOException {
        try (
                ServerSocket server = new ServerSocket(8800)) {
            System.out.println("Сервер создан!!!");
            ExecutorService pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new Evaluation(server.accept()));
            }
        }
    }

    private static class Evaluation implements Runnable //Задача
    {
        private Socket socket = new Socket();

        public Evaluation(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Соединение: " + socket);
            try {
                Scanner inText = new Scanner(socket.getInputStream());
                PrintWriter outText = new PrintWriter(socket.getOutputStream(), true);
                while (inText.hasNextLine()) {
                    String type = inText.nextLine();
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
                    } catch (BuildingUnderArrestException exception) {
                        outText.println(exception.printMessage());
                    } finally {
                        System.out.println(inText.nextLine());
                    }
                    System.out.println("Сокет закрыт!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String buildingAssessment(Building building) throws BuildingUnderArrestException {
        try {
            if (!isArrested()) {
                int price = 0;
                for (Floor f : building)
                    price += f.getTotalArea();
                if (building instanceof Dwelling) return price * 1000 + "";
                else if (building instanceof OfficeBuilding) return price * 1500 + "";
                return price * 2000 + "";
            } else throw new BuildingUnderArrestException();
        } catch (BuildingUnderArrestException exception) {
            return exception.printMessage();
        }
    }

    private static boolean isArrested() {
        if ((int) (Math.random() * 10) == 0)
            return true;
        else return false;
    }
}
