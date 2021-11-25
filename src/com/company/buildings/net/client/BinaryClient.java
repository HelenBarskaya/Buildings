package com.company.buildings.net.client;

import com.company.buildings.Buildings;
import com.company.buildings.dwelling.*;
import com.company.buildings.office.*;
import com.company.interfaces.*;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BinaryClient {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("192.168.0.10", 8800);

        try (
                Scanner file1 = new Scanner(new FileReader("file1.txt"));
                Scanner file2 = new Scanner(new FileReader("file2.txt"));
        ) {
            try (PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                 Scanner in = new Scanner(client.getInputStream())) {
                while (file1.hasNextLine()&& file2.hasNextLine()) {
                    String info=file1.nextLine();
                    String type=file2.nextLine();
                    System.out.println("Передаем тип объекта:" + type);
                    out.println(type);
                    System.out.println("Передаем информацию об объекте:" + info);
                    out.println(info);
                    //Считываем здание и отправляем на сервер
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


