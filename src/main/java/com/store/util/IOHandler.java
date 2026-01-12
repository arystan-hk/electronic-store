package com.store.util;

import java.io.*;
import java.util.ArrayList;

public class IOHandler {
    // Сохраняем список пользователей в файл users.bin
    public static void saveUsers(ArrayList<? extends Object> users, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Читаем список пользователей
    public static ArrayList<? extends Object> readUsers(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<? extends Object>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>(); // Если файла нет, возвращаем пустой список
        }
    }
}