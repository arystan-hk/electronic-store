package com.store.views;

import com.store.models.Product;
import com.store.util.IOHandler;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import java.util.ArrayList;

public class ManagerView {
    private TableView<Product> table = new TableView<>();

    public BorderPane getRoot(Runnable logoutAction) {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #ecf0f1;");

        HBox header = new HBox(20);
        Label title = new Label("Inventory Manager");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> logoutAction.run());
        header.getChildren().addAll(title, logoutBtn);
        layout.setTop(header);

        // TABLE COLUMNS
        TableColumn<Product, String> nameCol = new TableColumn<>("Product");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // КРУТАЯ ФИШКА: Подсветка красным, если мало товара
        qtyCol.setCellFactory(column -> new TableCell<Product, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.toString());
                    if (item < 5) {
                        setStyle("-fx-background-color: #ffcccc; -fx-text-fill: #c0392b; -fx-font-weight: bold;");
                    } else {
                        setStyle("-fx-text-fill: green;");
                    }
                }
            }
        });

        table.getColumns().addAll(nameCol, qtyCol);
        loadData();
        layout.setCenter(table);

        // ADD PRODUCT FORM
        HBox form = new HBox(10);
        form.setPadding(new Insets(15, 0, 0, 0));
        TextField prodName = new TextField(); prodName.setPromptText("Product Name");
        TextField prodQty = new TextField(); prodQty.setPromptText("Qty");
        TextField prodPrice = new TextField(); prodPrice.setPromptText("Price");

        Button addBtn = new Button("Add Stock");
        addBtn.setOnAction(e -> {
            try {
                ArrayList<Product> products = IOHandler.readList("products.bin");
                products.add(new Product(prodName.getText(), "General", Integer.parseInt(prodQty.getText()), Double.parseDouble(prodPrice.getText())));
                IOHandler.saveList(products, "products.bin");
                loadData();
                prodName.clear(); prodQty.clear(); prodPrice.clear();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Invalid input!").show();
            }
        });

        form.getChildren().addAll(prodName, prodQty, prodPrice, addBtn);
        layout.setBottom(form);

        return layout;
    }

    private void loadData() {
        ArrayList<Product> products = IOHandler.readList("products.bin");
        table.setItems(FXCollections.observableArrayList(products));
    }
}