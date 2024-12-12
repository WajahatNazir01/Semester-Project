package com.example.projectwithgui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddNewCategory {

    public void show(Stage stage) {

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        Button addCategoryButton = new Button("Add New Category");
        styleButton(addCategoryButton);
        Button backButton = new Button("Back to Admin Menu");
        styleButton(backButton);
        VBox categoryBox = new VBox(10);
        categoryBox.setAlignment(Pos.CENTER);
        addCategoryButton.setOnAction(e -> {
            VBox inputBox = new VBox(10);
            inputBox.setAlignment(Pos.CENTER);

            Label inputLabel = new Label("Enter Category Name:");
            TextField categoryField = new TextField();
            categoryField.setPromptText("Category Name");
            categoryField.setPrefWidth(250);
            HBox hBox = new HBox(10);
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().addAll(inputLabel, categoryField);

            Button confirmButton = new Button("Add Category");
            styleButton(confirmButton);
            //functionalities
            confirmButton.setOnAction(ev -> {
                String categoryName = categoryField.getText().trim();
                if (!categoryName.isEmpty()) {
                    CategoryManager.addCategory(categoryName);

                    Button newCategoryButton = new Button(categoryName);
                    styleButton(newCategoryButton);
                    newCategoryButton.setOnAction(categoryEvent -> {
                        System.out.println("Selected category: " + categoryName);
                    });

                    categoryBox.getChildren().add(newCategoryButton);

                    vBox.getChildren().remove(inputBox);
                } else {
                    System.out.println("Please enter a category name.");
                }
            });

            inputBox.getChildren().addAll(hBox, confirmButton);
            vBox.getChildren().add(1, inputBox);
        });

        // Action to return to Admin Menu
        backButton.setOnAction(e -> {
            ShowAdminMenu adminMenu = new ShowAdminMenu();
            adminMenu.show(stage);
        });

        // Add components to the main layout
        vBox.getChildren().addAll(addCategoryButton, categoryBox, backButton);

        // Create and set the scene
        Scene scene = new Scene(vBox, 1550, 670);
        stage.setScene(scene);
        vBox.setStyle("-fx-background-color: #fdd880;");
        stage.setTitle("Add New Category");
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(null);
        stage.setFullScreen(true);
        stage.show();
    }

    // Method to style buttons
    private void styleButton(Button button) {
        button.setFont(new javafx.scene.text.Font("Didot", 16));
        button.setStyle("-fx-background-color: #d88f1e; -fx-text-fill: white; -fx-padding: 10 20; "
                + "-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #8c5d15; -fx-border-width: 2;");
        button.setEffect(new DropShadow());
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #8c5d15; -fx-text-fill: white; "
                + "-fx-padding: 10 20; -fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #d88f1e; -fx-border-width: 2;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #d88f1e; -fx-text-fill: white; "
                + "-fx-padding: 10 20; -fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #8c5d15; -fx-border-width: 2;"));
    }

}
