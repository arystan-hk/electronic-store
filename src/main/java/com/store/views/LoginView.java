package com.store.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginView {
    private GridPane root;
    private TextField userField;
    private PasswordField passField;
    private Button loginBtn;

    public LoginView() {
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        Label sceneTitle = new Label("Electronics Store Login");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        root.add(sceneTitle, 0, 0, 2, 1);

        root.add(new Label("Username:"), 0, 1);
        userField = new TextField();
        root.add(userField, 1, 1);

        root.add(new Label("Password:"), 0, 2);
        passField = new PasswordField();
        root.add(passField, 1, 2);

        loginBtn = new Button("Sign In");
        root.add(loginBtn, 1, 4);
    }

    public GridPane getRoot() { return root; }
    public String getUsername() { return userField.getText(); }
    public String getPassword() { return passField.getText(); }
    public Button getLoginBtn() { return loginBtn; }
}