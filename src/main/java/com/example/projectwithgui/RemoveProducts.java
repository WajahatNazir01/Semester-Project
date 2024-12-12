

package com.example.projectwithgui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveProducts {

    private VBox productDisplayArea;
    private ComboBox<String> categoryFilter;

    public void show(Stage primaryStage) {

        HBox topMenuBar = createTopMenuBar(primaryStage);
        productDisplayArea = new VBox(10);
        productDisplayArea.setPadding(new Insets(10));
        ScrollPane scrollPane = new ScrollPane(productDisplayArea);
        scrollPane.setFitToWidth(true);

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(topMenuBar, scrollPane);

        // Load all products initially
        loadAllProducts();


        Scene scene = new Scene(mainLayout); // No fixed size
        primaryStage.setTitle("Remove Products");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true); // Set to full screen after setting the scene
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(null);
        primaryStage.show();
    }

    private HBox createTopMenuBar(Stage primaryStage) {
        HBox topMenuBar = new HBox(10);
        topMenuBar.setPadding(new Insets(10));
       // topMenuBar.setStyle("-fx-background-color: #e6e1e1;");
        topMenuBar.setStyle("-fx-background-color: #ffc784; -fx-border-color: #fff784; -fx-border-width: 1;");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            ProductsManagement pmf = new ProductsManagement();
            pmf.show(primaryStage);
        });
        backButton.setStyle("-fx-background-color: #d88f1e;");
        categoryFilter = new ComboBox<>();
        categoryFilter.getItems().addAll("View All Categories");
        categoryFilter.setStyle("-fx-background-color: #d88f1e");
        categoryFilter.getItems().addAll(CategoryManager.getCategories());
        categoryFilter.setValue("View All Categories");
        categoryFilter.setOnAction(e -> {
            String selectedCategory = categoryFilter.getValue();
            if ("View All Categories".equals(selectedCategory)) {
                loadAllProducts();
            } else {
                filterProductsByCategory(selectedCategory);
            }
        });


        topMenuBar.getChildren().addAll(backButton, categoryFilter);
        return topMenuBar;
    }

    private void loadAllProducts() {
        productDisplayArea.getChildren().clear();
        List<Product> allProducts = CategoryManager.getAllProducts();
        for (Product product : allProducts) {
            productDisplayArea.getChildren().add(createProductBox(product));
        }
    }

    private void loadProductsByCategory(String category) {
        productDisplayArea.getChildren().clear();
        List<Product> products = CategoryManager.getProductsForCategory(category);
        for (Product product : products) {
            productDisplayArea.getChildren().add(createProductBox(product));
        }
    }

    private void filterProductsByCategory(String category) {
        productDisplayArea.getChildren().clear();
        List<Product> products = CategoryManager.getProductsForCategory(category);
        if (!products.isEmpty()) {
            for (Product product : products) {
                productDisplayArea.getChildren().add(createProductBox(product));
            }
        } else {
            productDisplayArea.getChildren().add(new Label("No products found in this category."));
        }
    }


    private HBox createProductBox(Product product) {
        HBox productBox = new HBox(10);
        productBox.setPadding(new Insets(10));
        productBox.setStyle("-fx-border-color: rgba(177,171,171,0.72); -fx-border-width: 1; -fx-background-color: #ffffff;");
        productBox.setAlignment(Pos.CENTER_LEFT);

        ImageView productImage = new ImageView();
        productImage.setFitWidth(130);
        productImage.setFitHeight(130);
        loadImage(product, productImage);

        VBox productDetails = new VBox(5);
        Label nameLabel = new Label("Product Name: " + product.getName());
        Label priceLabel = new Label("Price: RS_" + product.getPrice());
        Label quantityLabel = new Label("Quantity: " + product.getQuantity());
        productDetails.getChildren().addAll(nameLabel, priceLabel, quantityLabel);

        Button removeProduct = new Button("Remove Product");
        removeProduct.setStyle("-fx-background-color: #d88f1e; -fx-text-fill: BlACK");
        removeProduct.setOnAction(e -> {

            productDisplayArea.getChildren().remove(productBox);

            boolean isRemoved = removeProductFromFile(product);
            if (isRemoved) {
                showNotification("Product removed successfully.");
            } else {
                showNotification("Failed to remove product from file.");
            }
        });

        VBox imageAndButton = new VBox(10, productImage, removeProduct);
        imageAndButton.setAlignment(Pos.CENTER);

        productBox.getChildren().addAll(imageAndButton, productDetails);
        return productBox;
    }

    private void loadImage(Product product, ImageView productImage) {
        try {
            String imagePath = product.getImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    productImage.setImage(new Image("file:" + imageFile.getAbsolutePath()));
                } else {
                    productImage.setImage(new Image("file:placeholder.png"));
                }
            } else {
                productImage.setImage(new Image("file:placeholder.png"));
            }
        } catch (Exception e) {
            productImage.setImage(new Image("file:placeholder.png"));
        }
    }

    private boolean removeProductFromFile(Product product) {
        if (product == null || product.getCategory() == null || product.getName() == null) {
            return false;
        }

        String productName = product.getName().trim();
        String category = product.getCategory().trim();
        String fileName = category.replaceAll("\\s+", "_") + ".txt";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Category file not found: " + fileName);
            return false;
        }

        List<String> updatedLines = new ArrayList<>();
        boolean productFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the current line corresponds to the product to be removed
                if (line.contains("Product Name: " + productName)) {
                    // If found, mark as found but do not add this line to updatedLines
                    productFound = true;
                    System.out.println("Removing product: " + productName);
                } else {
                    // Keep this line
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (productFound) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
                System.out.println("Product removed from file successfully.");
                return true; // Product successfully removed
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Product not found in the file.");
            return false;
        }
    }

    private void showNotification(String message) {
        // Display notification logic (e.g., using an Alert or a Label)
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }


}