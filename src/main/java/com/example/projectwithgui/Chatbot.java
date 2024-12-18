//package com.example.projectwithgui;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class Chatbot {
//    private GeminiAPIHandler geminiAPIHandler;
//    public Chatbot() {
//        String apiKey = "AIzaSyAE_5jufrjZW01mJ6O7Dgz7P6eKJvYzCCM";
//        this.geminiAPIHandler = new GeminiAPIHandler(apiKey);
//    }
//
//    public String getChatbotResponse(String prompt) {
//        try {
//
//            return geminiAPIHandler.getRawResponse(prompt);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Error: Unable to get a response from the API.";
//        }
//    }
//
//    public void start(Stage primaryStage) {
//        TextArea chatArea = new TextArea();
//        chatArea.setStyle("-fx-background-color: #bfab8a; -fx-text-fill: black;");
//        chatArea.setEditable(false);
//        chatArea.setWrapText(true);
//        chatArea.setPrefHeight(610);
//        ScrollPane scrollPane = new ScrollPane(chatArea);
//        scrollPane.setFitToWidth(true);
//        // scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Always show the vertical scrollbar
//        TextField userInput = new TextField();
//        userInput.setStyle("-fx-background-color: #d5a862;" +
//                "-fx-text-fill: black;");
//        userInput.setPrefWidth(450);
//        userInput.setPromptText("Enter prompt");
//        Button sendButton = new Button("Enter");
//        sendButton.setStyle("-fx-background-color: #d88f1e;");
//        sendButton.setOnAction(e -> {
//            String prompt = userInput.getText();
//            if (!prompt.isEmpty()) {
//                chatArea.appendText("You: " + prompt + "\n");
//                //get raw repsonce as i dont have necessary dependency
//                String response = getChatbotResponse(prompt);
//                chatArea.appendText("Chatbot: " + response + "\n");
//                chatArea.appendText("\n");
//                chatArea.setScrollTop(Double.MAX_VALUE);
//                userInput.clear();
//            }
//        });
//        Button backButton = new Button("Back");
//        backButton.setStyle("-fx-background-color: #d88f1e;");
//        backButton.setOnAction(e -> {
//            UserInterface ui = new UserInterface();
//            ui.show(primaryStage);
//        });
//
//        HBox inputBox = new HBox(10, userInput, sendButton, backButton);
//
//        VBox layout = new VBox(10, scrollPane, inputBox);
//        Scene scene = new Scene(layout, 560, 650);
//
//        primaryStage.setTitle("Chatbot");
//        primaryStage.setScene(scene);
//        primaryStage.centerOnScreen();
//        primaryStage.show();
//    }
//
//
//}
package com.example.projectwithgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Chatbot {
    private GeminiAPIHandler geminiAPIHandler;

    public Chatbot() {
        String apiKey = "AIzaSyAE_5jufrjZW01mJ6O7Dgz7P6eKJvYzCCM";
        this.geminiAPIHandler = new GeminiAPIHandler(apiKey);
    }

    public String getChatbotResponse(String prompt) {
        try {
            // Use the formatted response instead of raw JSON
            return geminiAPIHandler.getResponse(prompt);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to get a response from the API.";
        }
    }

    public void start(Stage primaryStage) {
        TextArea chatArea = new TextArea();
        chatArea.setStyle("-fx-background-color: #bfab8a; -fx-text-fill: black;");
        chatArea.setEditable(false);
        chatArea.setWrapText(true);
        chatArea.setPrefHeight(610);

        ScrollPane scrollPane = new ScrollPane(chatArea);
        scrollPane.setFitToWidth(true);

        TextField userInput = new TextField();
        userInput.setStyle("-fx-background-color: #d5a862; -fx-text-fill: black;");
        userInput.setPrefWidth(450);
        userInput.setPromptText("Enter prompt");

        Button sendButton = new Button("Enter");
        sendButton.setStyle("-fx-background-color: #d88f1e;");
        sendButton.setOnAction(e -> {
            String prompt = userInput.getText();
            if (!prompt.isEmpty()) {
                chatArea.appendText("You: " + prompt + "\n");
                String response = getChatbotResponse(prompt); // Get the formatted response
                chatArea.appendText(response + "\n"); // Display the chatbot response
                chatArea.appendText("\n");
                chatArea.setScrollTop(Double.MAX_VALUE);
                userInput.clear();
            }
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #d88f1e;");
        backButton.setOnAction(e -> {
            UserInterface ui = new UserInterface();
            ui.show(primaryStage);
        });

        HBox inputBox = new HBox(10, userInput, sendButton, backButton);
        VBox layout = new VBox(10, scrollPane, inputBox);

        Scene scene = new Scene(layout, 560, 650);
        primaryStage.setTitle("Chatbot");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
