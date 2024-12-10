//package com.example.projectwithgui;
//
//import javafx.animation.PauseTransition;
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
////this class get categories and products under each category from category manager and display them
//public class UserInterface {
//
//    private VBox productDisplayArea;
//    private ComboBox<String> categoryFilter;
//
//    public void show(Stage primaryStage) {
//        HBox topMenuBar = new HBox(10);
//        topMenuBar.setPadding(new Insets(10));
//        topMenuBar.setStyle("-fx-background-color: #e6e1e1; -fx-border-color: #b5b2b2; -fx-border-width: 1;");
//        Button cancelButton = new Button("Cancel");
//        cancelButton.setOnAction(e -> {
//            Main m1 = new Main();
//            m1.start(primaryStage);
//        });
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
//        Button cartButton = new Button("Cart");
//        cartButton.setOnAction(e -> showCart());
//        Button accountButton = new Button("Account");
//        accountButton.setOnAction(e -> {
//            //functionality still need to be described
//            System.out.println("Account button clicked");
//        });
//
//        //make regions to add blank space in between
//        Region leftSpacer = new Region();
//        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
//        Region rightSpacer = new Region();
//        HBox.setHgrow(rightSpacer, Priority.ALWAYS);
//        topMenuBar.getChildren().addAll(cancelButton, categoryFilter, leftSpacer, cartButton, accountButton);
//        // Product Display Area for each product
//        productDisplayArea = new VBox(10);
//        productDisplayArea.setPadding(new Insets(10));
//        ScrollPane scrollPane = new ScrollPane(productDisplayArea);
//        scrollPane.setFitToWidth(true);
//        VBox mainLayout = new VBox(10);
//        mainLayout.getChildren().addAll(topMenuBar, scrollPane);
//        //firstly it displays all products
//        loadAllProducts(mainLayout);
////        private void loadAllProducts(VBox mainLayout) {
////            productDisplayArea.getChildren().clear();
////            List<Product> allProducts = CategoryManager.getAllProducts();
////            for (Product product : allProducts) {
////                productDisplayArea.getChildren().add(createProductBox(product, mainLayout));
////            }
////        }
//
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
//        productBox.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1; -fx-background-color: #ffffff;");
//        productBox.setAlignment(Pos.CENTER_LEFT);
//        ImageView productImage = new ImageView();
//        productImage.setFitWidth(130);
//        productImage.setFitHeight(130);
//
//        // Use absolute path for image loading as if we use relative it does not work
//        try {
//            String imagePath = product.getImage(); // Get the image path from product
//            if (imagePath != null && !imagePath.isEmpty()) {
//                File imageFile = new File(imagePath);
//                if (imageFile.exists()) {
//                    productImage.setImage(new Image("file:" + imageFile.getAbsolutePath())); //to get whole path
//                } else {
//                    productImage.setImage(new Image("file:placeholder.png"));
//                }
//            } else {
//                productImage.setImage(new Image("file:placeholder.png")); // Placeholder image if no image path
//            }
//        } catch (Exception e) {
//            productImage.setImage(new Image("file:placeholder.png"));
//        }
//        //vbox to store each products details
//        VBox productDetails = new VBox(5);
//        Label nameLabel = new Label("Product Name: " + product.getName());
//        Label priceLabel = new Label("Price: RS_" + product.getPrice());
//        Label quantityLabel = new Label("Quantity: " + product.getQuantity());
//        Label discountLabel = new Label("Discount: " + product.getDiscount() + "%");
//        Label descriptionLabel = new Label("Description: " + product.getDescription());
//        productDetails.getChildren().addAll(nameLabel, priceLabel, quantityLabel, discountLabel, descriptionLabel);
//
//        // Add to Cart Button
//        Button addToCartButton = new Button("Add to Cart");
//        addToCartButton.setOnAction(e -> {
//            Cart c = App.getCart(); // Assuming a global cart instance accessible via App
//            c.addProductToCart(new Product(product.getName(), product.getPrice())); // Add name & price only
//            if (mainLayout != null) {
//                showNotification("Product added to cart", mainLayout);
//            }
//        });
//
//        VBox imageAndButton = new VBox(10, productImage, addToCartButton);
//        imageAndButton.setAlignment(Pos.CENTER);
//
//        // Add components to product box
//        productBox.getChildren().addAll(imageAndButton, productDetails);
//
//        return productBox;
//    }
//
//    private void showCart() {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Cart");
//        alert.setHeaderText(null);
//        alert.setContentText("Cart functionality is under development.");
//        alert.showAndWait();
//    }
//
//        //error giving code
//
//    private void showNotification(String message, VBox mainLayout) {
//
//        Label notificationLabel = new Label(message);
//        notificationLabel.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px; -fx-font-size: 14px;");
//        notificationLabel.setAlignment(Pos.CENTER);
//        notificationLabel.setMaxWidth(Double.MAX_VALUE);
//
//        mainLayout.getChildren().add(0, notificationLabel);
//
//        PauseTransition pause = new PauseTransition(Duration.seconds(1));
//        pause.setOnFinished(e -> mainLayout.getChildren().remove(notificationLabel));
//        pause.play();
//    }
//}
//








package com.example.projectwithgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class UserInterface {

    private VBox productDisplayArea;
    private ComboBox<String> categoryFilter;
    private Cart cart; // Cart instance

    public UserInterface() {
        cart = new Cart(); // Initialize cart
    }

    private String loggedInUserName;

    // Constructor to accept the logged-in user's name
    public UserInterface(String userName) {
        this.loggedInUserName = userName;
    }
    public void show(Stage primaryStage) {
        // Top Menu Bar
        HBox topMenuBar = new HBox(10);
        topMenuBar.setPadding(new Insets(10));
        topMenuBar.setStyle("-fx-background-color: #e6e1e1; -fx-border-color: #b5b2b2; -fx-border-width: 1;");
        topMenuBar.setAlignment(Pos.CENTER_RIGHT);

        // Category Filter ComboBox
        categoryFilter = new ComboBox<>();
        categoryFilter.getItems().addAll("View All Categories");
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

        // Cart and Logout Buttons
        Button cartButton = new Button("Cart");
        cartButton.setOnAction(e -> showCart());

        Button logout = new Button("Logout");
        logout.setOnAction(e->{
            UserLoginOptions ulo = new UserLoginOptions(primaryStage);
            ulo.show();
        });

        // Add components to the top menu bar
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topMenuBar.getChildren().addAll(new Label("Filter by Category:"), categoryFilter, spacer, cartButton, logout);

        // Product Display Area
        productDisplayArea = new VBox(10);
        productDisplayArea.setPadding(new Insets(10));
        ScrollPane scrollPane = new ScrollPane(productDisplayArea);
        scrollPane.setFitToWidth(true);

        // Load all products initially
        loadAllProducts();

        // Main Layout
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(topMenuBar, scrollPane);

        // Scene and Stage
        Scene scene = new Scene(mainLayout, 1550, 670);
        primaryStage.setTitle("Welcome to Our Shopping Cart");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void logout(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("You have successfully logged out.");
        alert.showAndWait();

        UserLoginOptions userLogin = new UserLoginOptions(primaryStage);
        try {
            userLogin.show(); // Pass the current stage
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadAllProducts() {
        productDisplayArea.getChildren().clear();
        List<Product> allProducts = CategoryManager.getAllProducts();
        for (Product product : allProducts) {
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
        productBox.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1; -fx-background-color: #ffffff;");
        productBox.setAlignment(Pos.CENTER_LEFT);

        ImageView productImage = new ImageView();
        productImage.setFitWidth(130);
        productImage.setFitHeight(130);

        try {
            String imagePath = product.getImage(); // Get the image path from product
            if (imagePath != null && !imagePath.isEmpty()) {
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    productImage.setImage(new Image("file:" + imageFile.getAbsolutePath())); // Load from any location
                } else {
                    productImage.setImage(new Image("placeholder.png")); // Placeholder image
                }
            } else {
                productImage.setImage(new Image("placeholder.png")); // Placeholder image if no image path
            }
        } catch (Exception e) {
            productImage.setImage(new Image("placeholder.png")); // Placeholder image in case of error
        }

        VBox productDetails = new VBox(5);
        Label nameLabel = new Label("Product Name: " + product.getName());
        Label priceLabel = new Label("Price: RS_" + product.getPrice());
        Label quantityLabel = new Label("Quantity: " + product.getQuantity());
        Label discountLabel = new Label("Discount: " + product.getDiscount() + "%");
        Label descriptionLabel = new Label("Description: " + product.getDescription());
        productDetails.getChildren().addAll(nameLabel, priceLabel, quantityLabel, discountLabel, descriptionLabel);

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(e -> addToCart(product));
        VBox imageAndButton = new VBox(10, productImage, addToCartButton);
        imageAndButton.setAlignment(Pos.CENTER);

        productBox.getChildren().addAll(imageAndButton, productDetails);
        return productBox;
    }

    //    private void addToCart(Product product) {
//        cart.addProduct(product);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Add to Cart");
//        alert.setHeaderText(null);
//        alert.setContentText(product.getName() + " has been added to your cart.");
//        alert.showAndWait();
//    }
    private void addToCart(Product product) {
        cart.addProduct(product);

        // Create a styled alert dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("🛒 Cart Update");
        alert.setHeaderText("Success! Item Added to Cart 🎉");
        alert.setContentText("The product \"" + product.getName() + "\" has been successfully added to your cart.\n\n" +
                "Cart Total: " + cart.getProducts().size() + " items.");

        // Add a custom graphic (e.g., a shopping cart icon)
        ImageView cartIcon = new ImageView(new Image("cart_icon.png")); // Ensure this path points to an actual image
        cartIcon.setFitWidth(50);
        cartIcon.setFitHeight(50);
        alert.setGraphic(cartIcon);

        // Apply a custom style to the alert
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-background-color: #f8f8f8;");
        dialogPane.lookup(".header-panel").setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        alert.showAndWait();
    }

//    private void showCart() {
//        Stage cartStage = new Stage();
//        cartStage.setTitle("Your Cart");
//
//        VBox layout = new VBox(10);
//        layout.setPadding(new Insets(10));
//
//        ListView<String> cartListView = new ListView<>();
//        for (Product product : cart.getCartItems()) {
//            cartListView.getItems().add(product.getName() + " - $" + product.getPrice());
//        }
//
//        Button removeButton = new Button("Remove Selected");
//        removeButton.setOnAction(e -> {
//            String selectedItem = cartListView.getSelectionModel().getSelectedItem();
//            if (selectedItem != null) {
//                cart.getCartItems().removeIf(product -> selectedItem.contains(product.getName()));
//
//                cartListView.getItems().remove(selectedItem);
//            }
//        });
//
//        Button checkoutButton = new Button("Checkout");
//        checkoutButton.setOnAction(e -> {
//            double total = cart.calculateTotal();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Checkout");
//            alert.setHeaderText("Checkout Summary");
//            alert.setContentText("Total Price: $" + total);
//            alert.showAndWait();
//            cart.clearCart();
//            cartListView.getItems().clear();
//        });
//
//        HBox buttonBox = new HBox(10, removeButton, checkoutButton);
//        layout.getChildren().addAll(new Label("Cart Items:"), cartListView, buttonBox);
//
//        Scene cartScene = new Scene(layout, 400, 300);
//        cartStage.setScene(cartScene);
//        cartStage.show();
//    }


    private void showCart() {
        Stage cartStage = new Stage();
        cartStage.setTitle("Shopping Cart");

        // Main Layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(15));
        mainLayout.setStyle("-fx-background-color: #f7f7f7;"); // Light gray background for the entire cart window

        // Left: Cart Items Section
        VBox cartItemsLayout = new VBox(20);
        cartItemsLayout.setPadding(new Insets(20));
        cartItemsLayout.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 2; -fx-background-color: #ffffff; -fx-background-radius: 10;");
        Label cartHeader = new Label("Shopping Cart");
        cartHeader.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #333;");

        // Grid for Cart Items
        GridPane cartGrid = new GridPane();
        cartGrid.setHgap(15);
        cartGrid.setVgap(15);
        cartGrid.setPadding(new Insets(15));
        cartGrid.setStyle("-fx-background-color: #fdfdfd; -fx-background-radius: 5;");

        // Headers
        Label productHeader = new Label("Product Details");
        Label quantityHeader = new Label("Quantity");
        Label priceHeader = new Label("Price");
        Label totalHeader = new Label("Total");
        Label actionHeader = new Label("Actions"); // New column for the "Remove" button

        // Style Headers
        String headerStyle = "-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #444;";
        productHeader.setStyle(headerStyle);
        quantityHeader.setStyle(headerStyle);
        priceHeader.setStyle(headerStyle);
        totalHeader.setStyle(headerStyle);
        actionHeader.setStyle(headerStyle);

        cartGrid.add(productHeader, 0, 0);
        cartGrid.add(quantityHeader, 1, 0);
        cartGrid.add(priceHeader, 2, 0);
        cartGrid.add(totalHeader, 3, 0);
        cartGrid.add(actionHeader, 4, 0); // Add the "Actions" header

        // Add cart items
        int row = 1;
        for (Product product : cart.getCartItems()) {
            Label productLabel = new Label(product.getName());
            productLabel.setStyle("-fx-text-fill: #333; -fx-font-size: 14px;");

            Label priceLabel = new Label("RS" + product.getPrice());
            priceLabel.setStyle("-fx-text-fill: #555;");

            Label totalLabel = new Label("RS" + (product.getPrice() * product.getQuantity()));
            totalLabel.setStyle("-fx-text-fill: #555;");

            // Quantity controls
            HBox quantityControls = new HBox(10);
            Button minusButton = new Button("-");
            Label quantityLabel = new Label(String.valueOf(product.getQuantity()));
            Button plusButton = new Button("+");

            minusButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");
            plusButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");
            quantityLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #444;");

            minusButton.setOnAction(e -> {
                if (product.getQuantity() > 1) {
                    product.setQuantity(product.getQuantity() - 1);
                    quantityLabel.setText(String.valueOf(product.getQuantity()));
                    totalLabel.setText("$" + (product.getPrice() * product.getQuantity()));
                    updateOrderSummary(cartItemsLayout); // Update summary
                }
            });

            plusButton.setOnAction(e -> {
                product.setQuantity(product.getQuantity() + 1);
                quantityLabel.setText(String.valueOf(product.getQuantity()));
                totalLabel.setText("$" + (product.getPrice() * product.getQuantity()));
                updateOrderSummary(cartItemsLayout); // Update summary
            });

            quantityControls.getChildren().addAll(minusButton, quantityLabel, plusButton);

            // "Remove" Button for each product
            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;");
            removeButton.setOnAction(e -> {
                cart.getCartItems().remove(product); // Remove the selected product
                showCart(); // Reload the cart
                cartStage.close();
            });

            // Add to grid
            cartGrid.add(productLabel, 0, row);
            cartGrid.add(quantityControls, 1, row);
            cartGrid.add(priceLabel, 2, row);
            cartGrid.add(totalLabel, 3, row);
            cartGrid.add(removeButton, 4, row); // Add "Remove" button in the Actions column

            row++;
        }

        cartItemsLayout.getChildren().addAll(cartHeader, cartGrid);

        // Right: Order Summary Section
        VBox orderSummaryLayout = new VBox(20);
        orderSummaryLayout.setPadding(new Insets(20));
        orderSummaryLayout.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 2; -fx-background-color: #ffffff; -fx-background-radius: 10;");

        Label summaryHeader = new Label("Order Summary");
        summaryHeader.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Label itemsLabel = new Label("Items: " + cart.getCartItems().size());
        itemsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

        Label shippingLabel = new Label("Shipping: $5.00");
        shippingLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

        Label totalCostLabel = new Label("Total Cost: $" + cart.calculateTotal());
        totalCostLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Button checkoutButton = new Button("Checkout");
        checkoutButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

//        checkoutButton.setOnAction(e -> {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Checkout");
//            alert.setHeaderText(null);
//            alert.setContentText("Thank you for your purchase!");
//            alert.showAndWait();
//            cart.clearCart(); // Clear cart after checkout
//            cartStage.close();
//        });

        checkoutButton.setOnAction(e -> {
            // Open the new checkout interface
            showCheckout(); // Call the new checkout method
            cartStage.close(); // Optionally close the cart stage
        });


        orderSummaryLayout.getChildren().addAll(summaryHeader, itemsLabel, shippingLabel, totalCostLabel, checkoutButton);

        // Assemble Layout
        mainLayout.setLeft(cartItemsLayout);
        mainLayout.setRight(orderSummaryLayout);

        Scene cartScene = new Scene(mainLayout, 900, 600);
        cartStage.setScene(cartScene);
        cartStage.show();
    }

    private void updateOrderSummary(VBox cartItemsLayout) {
        // Logic to dynamically update the summary when cart changes
    }


    private void showCheckout() {
        Stage checkoutStage = new Stage();
        checkoutStage.setTitle("Express Checkout");

        // Main Layout
        GridPane mainLayout = new GridPane();
        mainLayout.setPadding(new Insets(20));
        mainLayout.setHgap(20);
        mainLayout.setVgap(20);
        mainLayout.setStyle("-fx-background-color: #f7f7f7;");

        // Section 1: Billing Address
        VBox billingSection = new VBox(10);
        billingSection.setPadding(new Insets(10));
        billingSection.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e0e0e0; -fx-border-width: 2; -fx-background-radius: 10;");
        Label billingHeader = new Label("1. Billing Address");
        billingHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        TextField addressField = new TextField();
        addressField.setPromptText("Billing Address");
        TextField cityField = new TextField();
        cityField.setPromptText("City");
        ComboBox<String> countryBox = new ComboBox<>();
        countryBox.getItems().addAll("Pakistan", "USA", "UK", "India");
        countryBox.setPromptText("Country");

        billingSection.getChildren().addAll(billingHeader, firstNameField, lastNameField, emailField, phoneField, addressField, cityField, countryBox);

        // Section 2: Shipping Method
        VBox shippingSection = new VBox(10);
        shippingSection.setPadding(new Insets(10));
        shippingSection.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e0e0e0; -fx-border-width: 2; -fx-background-radius: 10;");
        Label shippingHeader = new Label("2. Shipping Method");
        shippingHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        RadioButton regularShipping = new RadioButton("Regular (8-21 days) - FREE");
        RadioButton expressShipping = new RadioButton("Express (3-6 days) - $10");
        ToggleGroup shippingGroup = new ToggleGroup();
        regularShipping.setToggleGroup(shippingGroup);
        expressShipping.setToggleGroup(shippingGroup);
        regularShipping.setSelected(true);

        shippingSection.getChildren().addAll(shippingHeader, regularShipping, expressShipping);

        // Section 3: Payment Method
        VBox paymentSection = new VBox(10);
        paymentSection.setPadding(new Insets(10));
        paymentSection.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e0e0e0; -fx-border-width: 2; -fx-background-radius: 10;");
        Label paymentHeader = new Label("3. Payment Method");
        paymentHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        RadioButton creditCardOption = new RadioButton("Pay online...");
        RadioButton paypalOption = new RadioButton("Cash on delivery...");
        ToggleGroup paymentGroup = new ToggleGroup();
        creditCardOption.setToggleGroup(paymentGroup);
        paypalOption.setToggleGroup(paymentGroup);
        creditCardOption.setSelected(true);

        TextField cardNumberField = new TextField();
        cardNumberField.setPromptText("Card Number");
        TextField expiryField = new TextField();
        expiryField.setPromptText("Expiration Date (MM/YY)");
        TextField cvvField = new TextField();
        cvvField.setPromptText("CVV");

        paymentSection.getChildren().addAll(paymentHeader, creditCardOption, paypalOption, cardNumberField, expiryField, cvvField);

        // Section 4: Review Your Order
        VBox reviewSection = new VBox(10);
        reviewSection.setPadding(new Insets(10));
        reviewSection.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e0e0e0; -fx-border-width: 2; -fx-background-radius: 10;");
        Label reviewHeader = new Label("4. Review Your Order");
        reviewHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label orderSummaryLabel = new Label("Order Summary:");
        Label subtotalLabel = new Label("Subtotal: $" + cart.calculateTotal());
        Label shippingCostLabel = new Label("Shipping: $10");
        Label totalCostLabel = new Label("Grand Total: $" + (cart.calculateTotal() + 10));

        Button placeOrderButton = new Button("Place Order");
        placeOrderButton.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        placeOrderButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText(null);
            alert.setContentText("Thank you! Your order has been placed successfully.");
            alert.showAndWait();
            cart.clearCart(); // Clear the cart
            checkoutStage.close();// Close the checkout window


        });

        reviewSection.getChildren().addAll(reviewHeader, orderSummaryLabel, subtotalLabel, shippingCostLabel, totalCostLabel, placeOrderButton);

        // Layout
        mainLayout.add(billingSection, 0, 0);
        mainLayout.add(shippingSection, 1, 0);
        mainLayout.add(paymentSection, 0, 1);
        mainLayout.add(reviewSection, 1, 1);

        // Scene and Stage
        Scene checkoutScene = new Scene(mainLayout, 900, 600);
        checkoutStage.setScene(checkoutScene);
        checkoutStage.show();
    }

}