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
    //same styling and scene continues here
    public void show() {

        Image backgroundImage = new Image("orangebg.png");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(1550);
        backgroundView.setFitHeight(720);
        backgroundView.setPreserveRatio(false);
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e ->{
            Main main = new Main();
            main.start(stage);
        });
       styleButton(cancel);

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

        VBox buttonLayout = new VBox(30, newUserButton, existingUserButton,cancel);
        buttonLayout.setAlignment(javafx.geometry.Pos.CENTER);
        buttonLayout.setPadding(new Insets(300,0,0,0));

        // adding backgorund image to layout with buttons
        StackPane root = new javafx.scene.layout.StackPane();
        root.getChildren().addAll(backgroundView, buttonLayout);

        //creating main scene
        Scene scene = new Scene(root, 1550, 670);
        stage.setScene(scene);
        stage.setTitle("User Login Options");
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(null);
        stage.setFullScreen(true);

    }

    //style buttons method
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

