package com.store.models;
import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String category;
    private int quantity;
    private double price;

    public Product(String name, String category, int quantity, double price) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    // Сеттер для изменения количества при продаже
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() { return name + " ($" + price + ")"; } // Для выпадающего списка
}