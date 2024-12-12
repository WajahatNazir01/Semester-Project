package com.example.projectwithgui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContactAdmin {

    public void show(Stage primaryStage) {
        // Create the UI components
        Label header = new Label("Live Chat with Admin");
        TextField emailField = new TextField();
        emailField.setPromptText("Your Email");

        PasswordField passwordField = new PasswordField();  // Password field for user to enter Gmail password
        passwordField.setPromptText("Your Gmail Password");

        TextArea queryArea = new TextArea();
        queryArea.setPromptText("Enter your query...");

        Button sendButton = new Button("Send Query");

//        // TextArea for displaying admin's response
//        TextArea replyArea = new TextArea();
//        replyArea.setEditable(false);
//        replyArea.setPromptText("Admin's reply will appear here...");

        // Button action to send email
        sendButton.setOnAction(event -> {
            String email = emailField.getText();
            String query = queryArea.getText();
            String password = passwordField.getText();  // Get the password from the password field

            if (!email.isEmpty() && !query.isEmpty() && !password.isEmpty()) {
                // Send email and display success message
                boolean emailSent = sendEmail(email, password, query);
                if (emailSent) {
                    showAlert("Success", "Your query has been sent to the admin! Check your mail for further actions.");
                } else {
                    showAlert("Error", "There was an issue sending your query. Please try again.");
                }
            } else {
                showAlert("Error", "Please fill out all fields.");
            }
        });

        // Layout setup
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(header, emailField, passwordField, queryArea, sendButton);

        // Scene setup
        Scene scene = new Scene(layout, 400, 350);
        primaryStage.setTitle("Live Chat with Admin");
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(null);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to send email and display reply
    private boolean sendEmail(String userEmail, String userPassword, String userQuery) {
        // Using JavaMail API to send email
        String to = "wajahatnazirwaraich01@gmail.com";  // Admin's email
        String from = userEmail;  // Use the user's email (entered by the user)
        String host = "smtp.gmail.com";        // Gmail SMTP server

        // Setup the email properties
        java.util.Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");

        // Get the session object
        javax.mail.Session session = javax.mail.Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(from, userPassword);  // Use the user's password
                    }
                });

        try {
            // Create a default MimeMessage object
            javax.mail.Message message = new javax.mail.internet.MimeMessage(session);
            message.setFrom(new javax.mail.internet.InternetAddress(from));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(to));
            message.setSubject("Query from: " + userEmail);
            message.setText("User Email: " + userEmail + "\n\n" + "Query: " + userQuery);

            // Send the message
            javax.mail.Transport.send(message);
            System.out.println("Message sent successfully to admin.");

            // Simulate a response from the admin
//            replyArea.setText("Admin's reply: Thank you for your query! We will get back to you shortly.");

            return true; // Return true if the email was sent successfully

        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false if there was an error sending the email
        }
    }

    // Method to show alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
