package com.example.projectwithgui;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class UserLoginOptions {
    private final Stage stage;

    public UserLoginOptions(Stage stage) {
        this.stage = stage;
    }
    //same styling ans scene continues here
    public void show() {

        Image backgroundImage = new Image("pic 11.png");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(1550);
        backgroundView.setFitHeight(670);
        backgroundView.setPreserveRatio(false);

        Button newUserButton = new Button("New User");
        Button existingUserButton = new Button("Existing User");
        styleButton(newUserButton);
        styleButton(existingUserButton);
        newUserButton.setOnAction(e -> {
            NewUserRegistration newUserRegistration = new NewUserRegistration(stage);
            newUserRegistration.show();
        });

        existingUserButton.setOnAction(e -> {
            ExistingUserLogin existingUserLogin = new ExistingUserLogin(stage);
            existingUserLogin.show();
        });

        VBox buttonLayout = new VBox(30, newUserButton, existingUserButton);
        buttonLayout.setAlignment(javafx.geometry.Pos.CENTER);
        buttonLayout.setPadding(new Insets(300,0,0,0));

        // adding backgorund image to layout with buttons
        StackPane root = new javafx.scene.layout.StackPane();
        root.getChildren().addAll(backgroundView, buttonLayout);

        // Creating  scene
        Scene scene = new Scene(root, 1550, 670);
        stage.setScene(scene);
        stage.setTitle("User Login Options");
        stage.setFullScreen(true);

    }

    // Method to style buttons
    private void styleButton(Button button) {
        button.setFont(new Font("Didot", 18));
        button.setStyle("-fx-background-color: #263988; -fx-text-fill: white; -fx-padding: 10 20; "
                + "-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #031b73; -fx-border-width: 2;");
        button.setEffect(new DropShadow());
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #031b73; -fx-text-fill: white; "
                + "-fx-padding: 10 20; -fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #263988; -fx-border-width: 2;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #263988; -fx-text-fill: white; "
                + "-fx-padding: 10 20; -fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #031b73; -fx-border-width: 2;"));
    }
}

