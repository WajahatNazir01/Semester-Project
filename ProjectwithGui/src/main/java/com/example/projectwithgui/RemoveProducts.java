//package com.example.projectwithgui;
//
//import javafx.animation.PauseTransition;
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.io.File;
//import java.util.List;
//
//public class RemoveProducts{
//
//    private VBox productDisplayArea;
//    private ComboBox<String> categoryFilter;
//
//    public void show(Stage primaryStage) {
//        // Top Menu Bar
//        HBox topMenuBar = new HBox(10);
//        topMenuBar.setPadding(new Insets(10));
//        topMenuBar.setStyle("-fx-background-color: #e6e1e1; -fx-border-color: #b5b2b2; -fx-border-width: 1;");
//
//        // Cancel Button
//        Button cancelButton = new Button("Cancel");
//        cancelButton.setOnAction(e -> {
//            Main m1 = new Main();
//            m1.start(primaryStage);
//        });
//
//        // Category Filter ComboBox
//        categoryFilter = new ComboBox<>();
//        categoryFilter.getItems().addAll("Categories");
//        categoryFilter.getItems().addAll(CategoryManager.getCategories());
//        categoryFilter.setValue("Categories");
//        categoryFilter.setOnAction(e -> {
//            String selectedCategory = categoryFilter.getValue();
//            if ("Categories".equals(selectedCategory)) {
//                //loadAllProducts();
//            } else {
//                filterProductsByCategory(selectedCategory);
//            }
//        });
//
//
//        // Create spacer regions
//        Region leftSpacer = new Region();
//        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
//
//        Region rightSpacer = new Region();
//        HBox.setHgrow(rightSpacer, Priority.ALWAYS);
//
//        // Add components to the top menu bar
//        topMenuBar.getChildren().addAll(cancelButton, categoryFilter, leftSpacer);
//
//        // Product Display Area
//        productDisplayArea = new VBox(10);
//        productDisplayArea.setPadding(new Insets(10));
//        ScrollPane scrollPane = new ScrollPane(productDisplayArea);
//        scrollPane.setFitToWidth(true);
//
//        // Main Layout
//        VBox mainLayout = new VBox(10);
//        mainLayout.getChildren().addAll(topMenuBar, scrollPane);
//
//        // Load all products initially
//        loadAllProducts(mainLayout);
//
//        // Scene and Stage
//        Scene scene = new Scene(mainLayout, 1550, 670);
//        primaryStage.setTitle("Welcome to Our Shopping Cart");
//        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(true);
//        primaryStage.show();
//    }
//
//    private void loadAllProducts(VBox mainLayout) {
//        productDisplayArea.getChildren().clear();
//        List<Product> allProducts = CategoryManager.getAllProducts();
//        for (Product product : allProducts) {
//            productDisplayArea.getChildren().add(createProductBox(product, mainLayout));
//        }
//    }
//
//    private void filterProductsByCategory(String category) {
//        productDisplayArea.getChildren().clear();
//
//        if (category != null) {
//            List<Product> products = CategoryManager.getProductsForCategory(category);
//
//            if (!products.isEmpty()) {
//                for (Product product : products) {
//                    productDisplayArea.getChildren().add(createProductBox(product, null));
//                }
//            } else {
//                productDisplayArea.getChildren().add(new Label("No products found in this category."));
//            }
//        }
//    }
//
//    private HBox createProductBox(Product product, VBox mainLayout) {
//        HBox productBox = new HBox(10);
//        productBox.setPadding(new Insets(10));
//        productBox.setStyle("-fx-border-color: rgba(177,171,171,0.72); -fx-border-width: 1; -fx-background-color: #ffffff;");
//        productBox.setAlignment(Pos.CENTER_LEFT);
//
//        // Product Image
//        ImageView productImage = new ImageView();
//        productImage.setFitWidth(100);
//        productImage.setFitHeight(100);
//
//        // Use absolute path for image loading
//        try {
//            String imagePath = product.getImage(); // Get the image path from product
//            if (imagePath != null && !imagePath.isEmpty()) {
//                File imageFile = new File(imagePath);
//                if (imageFile.exists()) {
//                    productImage.setImage(new Image("file:" + imageFile.getAbsolutePath())); // Load from any location
//                } else {
//                    productImage.setImage(new Image("file:placeholder.png")); // Placeholder image
//                }
//            } else {
//                productImage.setImage(new Image("file:placeholder.png")); // Placeholder image if no image path
//            }
//        } catch (Exception e) {
//            productImage.setImage(new Image("file:placeholder.png")); // Placeholder image in case of error
//        }
//
//        // Product Details
//        VBox productDetails = new VBox(5);
//        Label nameLabel = new Label("Product Name: " + product.getName());
//        Label priceLabel = new Label("Price: RS_" + product.getPrice());
//        Label quantityLabel = new Label("Quantity: " + product.getQuantity());
//        Label discountLabel = new Label("Discount: " + product.getDiscount() + "%");
//        Label descriptionLabel = new Label("Description: " + product.getDescription());
//        productDetails.getChildren().addAll(nameLabel, priceLabel, quantityLabel, discountLabel, descriptionLabel);
//
//        // Add to Cart Button
////        Button RemoveProduct = new Button("Remove product");
////        RemoveProduct.setOnAction(e -> {
////            boolean isRemoved = CategoryManager.removeProduct(product);
////            if (isRemoved) {
////                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product removed successfully.", ButtonType.OK);
////                alert.showAndWait();
////                filterProductsByCategory(categoryFilter.getValue()); // Refresh the display
////            } else {
////                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to remove product. Check logs for details.", ButtonType.OK);
////                alert.showAndWait();
////            }
////        });
//        Button removeProduct = new Button("Remove product");
//        removeProduct.setOnAction(e -> {
//            boolean isRemoved = CategoryManager.removeProduct(product);
//            if (isRemoved) {
//                showNotification("Product removed successfully.", mainLayout);
//                filterProductsByCategory(categoryFilter.getValue()); // Refresh the display
//            } else {
//                showNotification("Failed to remove product. Try again later.", mainLayout);
//            }
//        });
//
//
//        VBox imageAndButton = new VBox(10, productImage, removeProduct);
//        imageAndButton.setAlignment(Pos.CENTER);
//
//        // Add components to product box
//        productBox.getChildren().addAll(imageAndButton, productDetails);
//
//        return productBox;
//    }
//
//
//
//    private void showNotification(String message, VBox mainLayout) {
//        // Create the notification label
//        Label notificationLabel = new Label(message);
//        notificationLabel.setStyle("-fx-background-color: #cc1717; -fx-text-fill: white; -fx-padding: 10px; -fx-font-size: 14px;");
//        notificationLabel.setAlignment(Pos.CENTER);
//        notificationLabel.setMaxWidth(Double.MAX_VALUE);
//
//        // Add the notification label to the top of the main layout
//        mainLayout.getChildren().add(0, notificationLabel);
//
//        // Create a transition to remove the notification after 1 second
//        PauseTransition pause = new PauseTransition(Duration.seconds(1));
//        pause.setOnFinished(e -> mainLayout.getChildren().remove(notificationLabel));
//        pause.play();
//    }
//}
//
