package com.store.views;

import com.store.models.*;
import com.store.util.IOHandler;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import java.util.ArrayList;

public class AdminView {
    private TableView<User> table = new TableView<>();

    public BorderPane getRoot(Runnable logoutAction) {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #ecf0f1;");

        // HEADER
        HBox header = new HBox(20);
        Label title = new Label("Admin Dashboard");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        Button logoutBtn = new Button("Logout");
        logoutBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        logoutBtn.setOnAction(e -> logoutAction.run());
        header.getChildren().addAll(title, logoutBtn);
        layout.setTop(header);

        // TABLE
        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> userCol = new TableColumn<>("Username");
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role")); // Использует getRole()

        table.getColumns().addAll(nameCol, userCol, roleCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadTableData();
        layout.setCenter(table);

        // FORM (Внизу)
        HBox form = new HBox(10);
        form.setPadding(new Insets(15, 0, 0, 0));

        TextField nameIn = new TextField(); nameIn.setPromptText("Name");
        TextField userIn = new TextField(); userIn.setPromptText("Username");
        TextField passIn = new TextField(); passIn.setPromptText("Password");
        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Manager", "Cashier");
        roleBox.setValue("Manager");

        Button addBtn = new Button("Add User");
        addBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");

        addBtn.setOnAction(e -> {
            ArrayList<User> users = IOHandler.readList("users.bin");
            if (roleBox.getValue().equals("Manager")) {
                users.add(new Manager(nameIn.getText(), "S", userIn.getText(), passIn.getText(), "m@s.com", "00", 3000));
            } else {
                users.add(new Cashier(nameIn.getText(), "S", userIn.getText(), passIn.getText(), "c@s.com", "00", 2000));
            }
            IOHandler.saveList(users, "users.bin");
            loadTableData(); // Обновить таблицу
            nameIn.clear(); userIn.clear(); passIn.clear();
        });

        form.getChildren().addAll(nameIn, userIn, passIn, roleBox, addBtn);
        layout.setBottom(form);

        return layout;
    }

    private void loadTableData() {
        ArrayList<User> users = IOHandler.readList("users.bin");
        table.setItems(FXCollections.observableArrayList(users));
    }
}