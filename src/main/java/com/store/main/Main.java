package com.store.main;

import com.store.models.Administrator;
import com.store.models.User;
import com.store.util.IOHandler;
import com.store.views.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Сначала создаем тестового админа, если файла еще нет
        ArrayList<User> users = (ArrayList<User>) IOHandler.readUsers("users.bin");
        if (users.isEmpty()) {
            users.add(new Administrator("Arystan", "Ashkey", "admin", "123", "a@s.com", "123", 5000));
            IOHandler.saveUsers(users, "users.bin");
        }

        // 2. Загружаем интерфейс логина
        LoginView loginView = new LoginView();
        Scene scene = new Scene(loginView.getRoot(), 400, 300);

        // 3. Логика кнопки входа
        loginView.getLoginBtn().setOnAction(e -> {
            String inputUser = loginView.getUsername();
            String inputPass = loginView.getPassword();

            boolean found = false;
            ArrayList<User> allUsers = (ArrayList<User>) IOHandler.readUsers("users.bin");

            for (User u : allUsers) {
                if (u.getUsername().equals(inputUser) && u.getPassword().equals(inputPass)) {
                    found = true;
                    System.out.println("Login successful! Role: " + u.getClass().getSimpleName());
                    // Тут будет переключение на окно админа/менеджера/кассира
                    break;
                }
            }

            if (!found) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username or Password!");
                alert.showAndWait();
            }
        });

        primaryStage.setTitle("Electronics Store - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}