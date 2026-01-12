package com.store.main;

import com.store.models.*;
import com.store.util.IOHandler;
import com.store.views.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        initSystem(); // Инициализация данных
        showLogin();
    }

    public void showLogin() {
        LoginView loginView = new LoginView();
        Scene scene = new Scene(loginView.getRoot(), 800, 600); // Большое окно
        stage.setScene(scene);
        stage.setTitle("Electronics Store Management System");
        stage.show();

        loginView.getLoginBtn().setOnAction(e -> {
            User user = authenticate(loginView.getUsername(), loginView.getPassword());
            if (user != null) {
                if (user instanceof Administrator) showAdmin();
                else if (user instanceof Manager) showManager();
                else if (user instanceof Cashier) showCashier();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Credentials").show();
            }
        });
    }

    private void showAdmin() {
        stage.setScene(new Scene(new AdminView().getRoot(this::showLogin), 900, 600));
    }

    private void showManager() {
        stage.setScene(new Scene(new ManagerView().getRoot(this::showLogin), 900, 600));
    }

    private void showCashier() {
        stage.setScene(new Scene(new CashierView().getRoot(this::showLogin), 900, 600));
    }

    private User authenticate(String u, String p) {
        ArrayList<User> users = IOHandler.readList("users.bin");
        for (User user : users) {
            if (user.getUsername().equals(u) && user.getPassword().equals(p)) return user;
        }
        return null;
    }

    private void initSystem() {
        // Создаем Админа, если база пустая
        ArrayList<User> users = IOHandler.readList("users.bin");
        if (users.isEmpty()) {
            users.add(new Administrator("Arystan", "Ashkey", "admin", "123", "a@s.com", "777", 5000));
            // Добавим сразу менеджера для теста
            users.add(new Manager("Ilias", "Manager", "manager", "123", "m@s.com", "888", 3000));
            // Добавим кассира
            users.add(new Cashier("Aziz", "Cashier", "cashier", "123", "c@s.com", "999", 2000));
            IOHandler.saveList(users, "users.bin");
        }

        // Создаем товары, если склад пуст
        ArrayList<Product> products = IOHandler.readList("products.bin");
        if (products.isEmpty()) {
            products.add(new Product("iPhone 15 Pro", "Mobile", 10, 1200));
            products.add(new Product("MacBook Air", "Laptop", 2, 999)); // Мало товара! Будет красным
            IOHandler.saveList(products, "products.bin");
        }
    }

    public static void main(String[] args) { launch(args); }
}