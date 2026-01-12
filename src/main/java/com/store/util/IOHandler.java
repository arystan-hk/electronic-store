package com.store.util;

import java.io.*;
import java.util.ArrayList;

public class IOHandler {
    // Универсальный метод чтения
    public static <T> ArrayList<T> readList(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<T>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>(); // Возвращаем пустой список, если файла нет
        }
    }

    // Универсальный метод записи
    public static <T> void saveList(ArrayList<T> list, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveBill(String content, String filename) {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}