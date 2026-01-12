package com.store.models;

import java.io.Serializable;

// Serializable нужен, чтобы сохранять объект в бинарный файл
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L; // Для корректной десериализации

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private double salary;

    public User(String name, String surname, String username, String password, String email, String phone, double salary) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
    }

    // Геттеры и сеттеры (обязательно для инкапсуляции!)
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    // ... добавь остальные геттеры для других полей
}