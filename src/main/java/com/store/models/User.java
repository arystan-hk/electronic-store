package com.store.models;
import java.io.Serializable;

public abstract class User implements Serializable {
    private String name, surname, username, password, email, phone;
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

    // Геттеры нужны для TableView
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getRole() { return this.getClass().getSimpleName(); } // Чтобы показывать роль в таблице
}