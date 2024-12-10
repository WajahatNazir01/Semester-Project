package com.example.projectwithgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//
//public class ShowAdminMenu {
//    public void show(Stage stage) {
//
//        VBox root = new VBox(20);
//        root.setPadding(new Insets(20,0,0,0));
//
//        MenuBar menuBar = createMenuBar(stage);
//        root.getChildren().add(menuBar);
//
//        GridPane gridPane = new GridPane();
//        gridPane.setHgap(90); // Horizontal gap between buttons
//        gridPane.setVgap(90); // Vertical gap between buttons
//        gridPane.setAlignment(Pos.CENTER);
//
//        // Create buttons with images
//        Button categoriesButton = createImageButton("categories.png");
//        Button viewSalesButton = createImageButton("view_sales.png");
//        Button addCategoryButton = createImageButton("add_category.png");
//        Button productsManagementButton = createImageButton("products_management.png");
//        Button viewRevenueButton = createImageButton("view_revenue.png");
//        Button customersDatabaseButton = createImageButton("customers_database.png");
//
//        categoriesButton.setOnAction(e -> {
//            Categories categories = new Categories();
//            categories.show(stage);
//        });
//        viewSalesButton.setOnAction(e -> {
//            Sales sales = new Sales();
//            sales.show(stage);
//        });
//        addCategoryButton.setOnAction(e -> {
//            AddNewCategory addNewCategory = new AddNewCategory();
//            addNewCategory.show(stage);
//        });
//        productsManagementButton.setOnAction(e -> { // Most important button that handles all the
//            ProductsManagement productsManagement = new ProductsManagement();
//            productsManagement.show(stage);
//        });
//        customersDatabaseButton.setOnAction(e -> {
//            CustomersDatabase cdb = new CustomersDatabase();
//            cdb.show(stage);
//
//        });
//
//        // Add buttons to the grid
//        gridPane.add(categoriesButton, 0, 0);
//        gridPane.add(viewSalesButton, 1, 0);
//        gridPane.add(addCategoryButton, 2, 0);
//        gridPane.add(productsManagementButton, 0, 1);
//        gridPane.add(viewRevenueButton, 1, 1);
//        gridPane.add(customersDatabaseButton, 2, 1);
//
//        // Add the GridPane to the VBox
//        root.getChildren().add(gridPane);
//
//        // Create a scene and set it on the stage
//        Scene scene = new Scene(root, 1500, 670);
//        stage.setScene(scene);
//        stage.setTitle("Admin Menu");
//        stage.setFullScreen(true);
//        stage.show();
//    }
//
//    private MenuBar createMenuBar(Stage stage) {
//        // Create a MenuBar
//        MenuBar menuBar = new MenuBar();
//
//        // Create "File" menu with "Back to Main" option
//        Menu fileMenu = new Menu("Options");
//        MenuItem back = new MenuItem("Back to Main Menu");
//
//        // Action for "Back to Main Menu"
//        back.setOnAction(e -> {
//            Main main = new Main();
//            main.start(stage); // Navigate back to the Main class
//        });
//
//        fileMenu.getItems().add(back);
//        menuBar.getMenus().add(fileMenu);
//
//        return menuBar;
//    }
//
//    private Button createImageButton(String imagePath) {
//        Image image = new Image(imagePath); // Load the image
//        ImageView imageView = new ImageView(image);
//        imageView.setFitWidth(200);
//        imageView.setFitHeight(200); // To make the pictures of a specific size
//        imageView.setPreserveRatio(true);
//
//        Button button = new Button();
//        button.setGraphic(imageView); // Add picture as graphics of button
//        button.setStyle("-fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: #f0f0f0;");
//        button.setEffect(new DropShadow(10, Color.BLACK));
//        button.setOnMouseEntered(e -> button.setEffect(new DropShadow(20, Color.DARKGRAY)));
//        button.setOnMouseExited(e -> button.setEffect(new DropShadow(10, Color.GRAY)));
//
//        button.setPrefSize(250, 250); // Set button size
//        return button;
//    }
//}


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowAdminMenu {
    public void show(Stage stage) {

        VBox root = new VBox(20);
        root.setPadding(new Insets(20, 0, 0, 0));

        // Create a custom MenuBar with only a Cancel button
        root.getChildren().add(createMenuBar(stage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(90); // Horizontal gap between buttons
        gridPane.setVgap(90); // Vertical gap between buttons
        gridPane.setAlignment(Pos.CENTER);

        // Create buttons with images
        Button categoriesButton = createImageButton("categories.png");
        Button viewSalesButton = createImageButton("view_sales.png");
        Button addCategoryButton = createImageButton("add_category.png");
        Button productsManagementButton = createImageButton("products_management.png");
        Button viewRevenueButton = createImageButton("view_revenue.png");
        Button customersDatabaseButton = createImageButton("customers_database.png");

        categoriesButton.setOnAction(e -> {
            Categories categories = new Categories();
            categories.show(stage);
        });
        viewSalesButton.setOnAction(e -> {
            Sales sales = new Sales();
            sales.show(stage);
        });
        addCategoryButton.setOnAction(e -> {
            AddNewCategory addNewCategory = new AddNewCategory();
            addNewCategory.show(stage);
        });
        productsManagementButton.setOnAction(e -> {
            ProductsManagement productsManagement = new ProductsManagement();
            productsManagement.show(stage);
        });
        customersDatabaseButton.setOnAction(e -> {
            CustomersDatabase cdb = new CustomersDatabase();
            cdb.show(stage);
        });

        // Add buttons to the grid
        gridPane.add(categoriesButton, 0, 0);
        gridPane.add(viewSalesButton, 1, 0);
        gridPane.add(addCategoryButton, 2, 0);
        gridPane.add(productsManagementButton, 0, 1);
        gridPane.add(viewRevenueButton, 1, 1);
        gridPane.add(customersDatabaseButton, 2, 1);

        // Add the GridPane to the VBox
        root.getChildren().add(gridPane);

        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 1500, 670);
        stage.setScene(scene);
        stage.setTitle("Admin Menu");
        stage.setFullScreen(true);
        stage.show();
    }

    private VBox createMenuBar(Stage stage) {
        // Create a VBox for the "Menu Bar" with a Cancel button
        VBox menuBar = new VBox();
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-font-size: 16px; -fx-background-color: #656262; -fx-text-fill: #000000;");
        cancelButton.setOnAction(e -> {
            Main main = new Main();
            main.start(stage); // Navigate back to the Main class
        });

        // Add the cancel button to the menu bar
        menuBar.getChildren().add(cancelButton);
        menuBar.setPadding(new Insets(10, 0, 0, 10)); // Set padding for the button

        return menuBar;
    }

    private Button createImageButton(String imagePath) {
        Image image = new Image(imagePath); // Load the image
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200); // To make the pictures of a specific size
        imageView.setPreserveRatio(true);

        Button button = new Button();
        button.setGraphic(imageView); // Add picture as graphics of button
        button.setStyle("-fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: #f0f0f0;");
        button.setEffect(new DropShadow(10, Color.BLACK));
        button.setOnMouseEntered(e -> button.setEffect(new DropShadow(20, Color.DARKGRAY)));
        button.setOnMouseExited(e -> button.setEffect(new DropShadow(10, Color.GRAY)));

        button.setPrefSize(250, 250); // Set button size
        return button;
    }
}
