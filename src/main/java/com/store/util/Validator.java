package com.store.util;

public class Validator {
    public static boolean isValidPassword(String password) {
        // Пример регулярного выражения для безопасности
        return password.matches("^(?=.*[0-9]).{8,}$");
    }
}