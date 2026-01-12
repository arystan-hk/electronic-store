package com.store.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginView {
    private VBox root;
    private TextField userField;
    private PasswordField passField;
    private Button loginBtn;

    public LoginView() {
        // Фон с градиентом
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #2c3e50, #4ca1af);");

        // Карточка логина (белая по центру)
        VBox card = new VBox(15);
        card.setMaxWidth(350);
        card.setPadding(new Insets(40));
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");

        Label title = new Label("SYSTEM LOGIN");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#2c3e50"));

        userField = new TextField(); userField.setPromptText("Username");
        userField.setStyle("-fx-background-radius: 5; -fx-padding: 10; -fx-border-color: #bdc3c7; -fx-border-radius: 5;");

        passField = new PasswordField(); passField.setPromptText("Password");
        passField.setStyle("-fx-background-radius: 5; -fx-padding: 10; -fx-border-color: #bdc3c7; -fx-border-radius: 5;");

        loginBtn = new Button("SIGN IN");
        loginBtn.setMaxWidth(Double.MAX_VALUE);
        loginBtn.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 12; -fx-background-radius: 5; -fx-cursor: hand;");

        card.getChildren().addAll(title, userField, passField, loginBtn);
        root.getChildren().add(card);
    }

    public VBox getRoot() { return root; }
    public String getUsername() { return userField.getText(); }
    public String getPassword() { return passField.getText(); }
    public Button getLoginBtn() { return loginBtn; }
}