package com.store.views;

import com.store.models.Product;
import com.store.util.IOHandler;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Date;

public class CashierView {
    public VBox getRoot(Runnable logoutAction) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f4f4f4;");

        Label title = new Label("POS TERMINAL");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #2980b9;");

        // Загружаем товары из файла
        ArrayList<Product> products = IOHandler.readList("products.bin");
        ComboBox<Product> productBox = new ComboBox<>(FXCollections.observableArrayList(products));
        productBox.setPromptText("Select Product");
        productBox.setMaxWidth(300);

        TextField qtyField = new TextField();
        qtyField.setPromptText("Quantity");
        qtyField.setMaxWidth(300);

        TextArea receiptArea = new TextArea();
        receiptArea.setEditable(false);
        receiptArea.setMaxWidth(400);

        Button payBtn = new Button("PROCESS PAYMENT & PRINT");
        payBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");

        payBtn.setOnAction(e -> {
            Product selected = productBox.getValue();
            if (selected != null && !qtyField.getText().isEmpty()) {
                int qty = Integer.parseInt(qtyField.getText());
                if (qty <= selected.getQuantity()) {
                    double total = qty * selected.getPrice();
                    String bill = "=== RECEIPT ===\nDate: " + new Date() +
                            "\nItem: " + selected.getName() +
                            "\nQty: " + qty +
                            "\nTOTAL: $" + total + "\n===============";

                    receiptArea.setText(bill);
                    IOHandler.saveBill(bill, "Receipt_" + System.currentTimeMillis() + ".txt");

                    // Обновляем количество на складе (Логика реального магазина!)
                    selected.setQuantity(selected.getQuantity() - qty);
                    IOHandler.saveList(products, "products.bin"); // Сохраняем изменение
                    new Alert(Alert.AlertType.INFORMATION, "Sale Complete!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Not enough stock! Available: " + selected.getQuantity()).show();
                }
            }
        });

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> logoutAction.run());

        layout.getChildren().addAll(title, productBox, qtyField, payBtn, receiptArea, logoutBtn);
        return layout;
    }
}