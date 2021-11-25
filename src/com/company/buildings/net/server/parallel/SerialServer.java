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
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SerialServer {
    public static void main(String[] args) throws IOException {
        try (
                ServerSocket server = new ServerSocket(9900)) {
            System.out.println("Сервер создан!!!");
            ExecutorService pool = Executors.newFixedThreadPool(20);
            //Создаем пул потоков
            //ExecutorService исполняет асинхронный код в одном или нескольких потоках
            //Пул ограничен 20-ю потоками, чтобы не перегрузить сервер
            try {
                while (true) {
                    pool.execute(new SerialServer.Evaluation(server.accept()));
                    //Метод execute выполняет данную команду в какой-то момент в будущем
                }
            } catch (IOException ex)
            {
                pool.shutdown();
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
                InputStream inText = socket.getInputStream();
                PrintWriter outText = new PrintWriter(socket.getOutputStream(), true);
                while (socket.isConnected()) {
                    Building building = null;
                    try {
                        building = Buildings.deserializeBuilding(inText);
                        System.out.println("Создан объект: ");
                        System.out.println(building.toString());
                    } catch (ClassNotFoundException exception)
                    {
                        System.out.println("Class is not found");
                        outText.println("Error");
                        return;
                    }
                    try {
                        String price = buildingAssessment(building);
                        System.out.println("Возвращаем стоимость: " + price);
                        outText.println(price);
                    } catch (BuildingUnderArrestException exception) {
                        outText.println(exception.printMessage());
                    }
                    System.out.println("Сокет закрыт!");
                }
            } catch (IOException e) {
                try {
                    socket.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
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
            System.out.println(exception.printMessage());
            return exception.printMessage();
        }
    }

    private static boolean isArrested() {
        if ((int) (Math.random() * 10) == 0)
            return true;
        else return false;
    }
}
