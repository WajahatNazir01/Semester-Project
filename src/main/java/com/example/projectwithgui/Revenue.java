package com.example.projectwithgui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Revenue {

    // Method to show the total revenue in a small scene
    public void show() {
        double totalRevenue = getTotalRevenueFromFile(); //getting form file
        String revenueMessage = String.format("Total Revenue: Rs_%.2f", totalRevenue);
        Label revenueLabel = new Label(revenueMessage);
        revenueLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        VBox layout = new VBox(10);
        layout.getChildren().add(revenueLabel);

        Scene revenueScene = new Scene(layout, 400, 300);
        Stage revenueStage = new Stage();
        revenueStage.setTitle("Total Revenue");
        revenueStage.setScene(revenueScene);
        revenueStage.show();
    }
    private double getTotalRevenueFromFile() {
        double totalRevenue = 0.0;
        try (BufferedReader reader = new BufferedReader(new FileReader("revenue.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Revenue from Order")) {
                    String revenueStr = line.split(":")[1].trim();
                    revenueStr = revenueStr.replace("Rs_", "").trim();
                    try {
                        totalRevenue += Double.parseDouble(revenueStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing revenue: " + revenueStr);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return totalRevenue;
    }
}
