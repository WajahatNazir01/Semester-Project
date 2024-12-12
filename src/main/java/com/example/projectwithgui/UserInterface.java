
package com.example.projectwithgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class UserInterface {
    //this class has three functianltites 1 user interface 2 cart 3 checkout
    private VBox productDisplayArea;
    private ComboBox<String> categoryFilter;
    private Cart cart; // Cart instance

    public UserInterface() {
        cart = new Cart(); // Initialize cart
    }

    public void show(Stage primaryStage) {
        // Top Menu Bar
        HBox topMenuBar = new HBox(10);
        topMenuBar.setPadding(new Insets(10));
        topMenuBar.setStyle("-fx-background-color: #ffc784; -fx-border-color: #fff784; -fx-border-width: 1;");
        topMenuBar.setAlignment(Pos.CENTER_RIGHT);

        // Category Filter ComboBox
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

        // Cart and Logout Buttons
        Button cartButton = new Button("Cart");
        cartButton.setStyle("-fx-background-color: #d88f1e;");
        cartButton.setOnAction(e -> showCart());
        Button logout = new Button("Logout");
        logout.setStyle("-fx-background-color: #d88f1e;");
        logout.setOnAction(e->{
            UserLoginOptions ulo = new UserLoginOptions(primaryStage);
            ulo.show();
        });
        Button conatactAdmin = new Button("Contact Admin");
        conatactAdmin.setStyle("-fx-background-color: #d88f1e;");
        conatactAdmin.setOnAction(e->{
            //code here  under development
            ContactAdmin ca = new ContactAdmin();
            ca.show(primaryStage);
        });
        //to create space betweem buttons
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topMenuBar.getChildren().addAll(new Label("Filter by Category:"), categoryFilter, spacer, conatactAdmin,cartButton, logout);


        productDisplayArea = new VBox(10);
        productDisplayArea.setPadding(new Insets(10));
        ScrollPane scrollPane = new ScrollPane(productDisplayArea);
        scrollPane.setFitToWidth(true);

        // Load all products initially to display without using any filter
        loadAllProducts();

        // Main Layout
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(topMenuBar, scrollPane);
  //      mainLayout.setStyle("-fx-background-color: #5c420d");

        Scene scene = new Scene(mainLayout, 1550, 670);
        primaryStage.setTitle("Welcome to Our Shopping Cart");
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(null);
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
    //method to fileter products by category
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


    //creating product box to display each single product in hbox and then displaying it in scrollpane
    private HBox createProductBox(Product product) {
        HBox productBox = new HBox(10);
        productBox.setPadding(new Insets(10));
        productBox.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1; -fx-background-color: #ffffff;");
        productBox.setAlignment(Pos.CENTER_LEFT);

        ImageView productImage = new ImageView();
        productImage.setFitWidth(130);
        productImage.setFitHeight(130);

        try {
            String imagePath = product.getImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    productImage.setImage(new Image("file:" + imageFile.getAbsolutePath()));//using of absolute method make it loaad from anywhere in system
                } else {
                    productImage.setImage(new Image("placeholder.png")); //it is the image displayed when no pic is givne by amdin
                }
            } else {
                productImage.setImage(new Image("placeholder.png"));
            }
        } catch (Exception e) {
            productImage.setImage(new Image("placeholder.png"));
        }

        VBox productDetails = new VBox(5);
        productDetails.setStyle("-fx-text-fill: #ffffff;");
        Label nameLabel = new Label("Product Name: " + product.getName());
        Label priceLabel = new Label("Price: RS_" + product.getPrice());
        Label quantityLabel = new Label("Quantity: " + product.getQuantity());
        Label discountLabel = new Label("Discount: " + product.getDiscount() + "%");
        Label descriptionLabel = new Label("Description: " + product.getDescription());
        productDetails.getChildren().addAll(nameLabel, priceLabel, quantityLabel, discountLabel, descriptionLabel);

        //for applying consistent styling to all labels
        for (Node node : productDetails.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-text-fill: #000000; -fx-font-size: 14px;");
            }
        }

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setStyle("-fx-background-color: #d88f1e; -fx-text-fill: #000000; -fx-font-size: 14px;");
        addToCartButton.setOnAction(e -> addToCart(product));
        VBox imageAndButton = new VBox(10, productImage, addToCartButton);
        imageAndButton.setAlignment(Pos.CENTER);

        productBox.getChildren().addAll(imageAndButton, productDetails);
        return productBox;
    }



    private void addToCart(Product product) {
        cart.addProduct(product);

        // little bit styling of cart class
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("🛒 Cart Update");
        alert.setHeaderText("Success! Item Added to Cart 🎉");
        alert.setContentText("The product \"" + product.getName() + "\" has been successfully added to your cart.\n\n" +
                "Cart Total: " + cart.getProducts().size() + " items.");


        ImageView cartIcon = new ImageView(new Image("cart_icon.png"));
        cartIcon.setFitWidth(50);
        cartIcon.setFitHeight(50);
        alert.setGraphic(cartIcon);


        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-background-color: #f8f8f8;");
        dialogPane.lookup(".header-panel").setStyle("-fx-background-color: #dd8e13; -fx-text-fill: white;");

        alert.showAndWait();
    }



    //Here we use both borderPane and GridPan bcz first we make borderPane then inside it make Vbox in which we make Grid pane tHIS SAVE US from making a table
    private void showCart() {
        Stage cartStage = new Stage();
        cartStage.setTitle("Shopping Cart");


        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(10));
        mainLayout.setStyle("-fx-background-color: #f7f7f7;");

        VBox cartItemsLayout = new VBox(10);
        cartItemsLayout.setPadding(new Insets(10));
        cartItemsLayout.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 2; -fx-background-color: #ffffff; -fx-background-radius: 10;");
        Label cartHeader = new Label("Shopping Cart");
        cartHeader.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #333;");

        GridPane cartGrid = new GridPane();
        cartGrid.setHgap(10);
        cartGrid.setVgap(10);
        cartGrid.setPadding(new Insets(10));  // Reduced padding
        cartGrid.setStyle("-fx-background-color: #ffdc97; -fx-background-radius: 5; -fx-border-color:A68A51FF; -fx-border-width: 2;");

        Label productHeader = new Label("Product Details");
        Label priceHeader = new Label("Price");
        Label totalHeader = new Label("Total");


        String headerStyle = "-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #000000;";
        productHeader.setStyle(headerStyle);
        priceHeader.setStyle(headerStyle);
        totalHeader.setStyle(headerStyle);


        cartGrid.add(productHeader, 0, 0);
        cartGrid.add(priceHeader, 2, 0);
        cartGrid.add(totalHeader, 3, 0);


        int row = 1;
        for (Product product : cart.getCartItems()) {
            Label productLabel = new Label(product.getName());
            productLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px;");


            Label priceLabel = new Label("Rs_" + product.getPrice());
            priceLabel.setStyle("-fx-text-fill: #000000;");

            Label totalLabel = new Label("Rs_" + (product.getPrice()));
            totalLabel.setStyle("-fx-text-fill: #000000;");

            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-background-color: #d88f1e; -fx-text-fill: #000000; -fx-font-size: 14px;");
            removeButton.setOnAction(e -> {
                cart.getCartItems().remove(product);
                showCart(); //to refresh the cart after removal or addition of new products
                cartStage.close();
            });

            cartGrid.add(productLabel, 0, row);
            cartGrid.add(priceLabel, 2, row);
            cartGrid.add(totalLabel, 3, row);
            cartGrid.add(removeButton, 4, row);

            row++;
        }

        cartItemsLayout.getChildren().addAll(cartHeader, cartGrid);

        VBox orderSummaryLayout = new VBox(10);
        orderSummaryLayout.setPadding(new Insets(10));
        orderSummaryLayout.setStyle("-fx-border-color: #a68a51; -fx-border-width: 2; -fx-background-color: #ffdc97; -fx-background-radius: 10;");

        Label summaryHeader = new Label("Order Summary");
        summaryHeader.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333;");


        double totalCost = 0;
        for (Product product : cart.getCartItems()) {
          //  totalCost += product.getPrice() * product.getQuantity();   as still the logic for quntity is under development
            totalCost += product.getPrice();
        }

        Label itemsLabel = new Label("Items: " + cart.getCartItems().size());
        itemsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

        Label totalCostLabel = new Label("Total Cost: Rs_" + totalCost);
        totalCostLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Button checkoutButton = new Button("Checkout");
        checkoutButton.setStyle("-fx-background-color: #d88f1e; -fx-text-fill: #000000; -fx-font-size: 14px; -fx-font-weight: bold;");
        checkoutButton.setOnAction(e -> {
            showCheckout();
            cartStage.close();
        });

        orderSummaryLayout.getChildren().addAll(summaryHeader, itemsLabel, totalCostLabel, checkoutButton);

        //layout setting
        mainLayout.setTop(cartItemsLayout);
        mainLayout.setBottom(orderSummaryLayout);

        Scene cartScene = new Scene(mainLayout, 800, 600);
        cartStage.setScene(cartScene);
        cartStage.show();
    }




//
//    private void updateOrderSummary(VBox cartItemsLayout) {
//        // Logic to dynamically update the summary when cart changes
//    }


    private void showCheckout() {
        Stage checkoutStage = new Stage();
        checkoutStage.setTitle("Checkout");

        GridPane mainLayout = new GridPane();
        mainLayout.setPadding(new Insets(10));
        mainLayout.setHgap(20);
        mainLayout.setVgap(20);
        mainLayout.setStyle("-fx-background-color: #f5f5f5;");
        //hbox for billing address
        VBox billingSection = new VBox(10);
        billingSection.setPadding(new Insets(15));
        billingSection.setStyle("-fx-background-color: #ffdc97; -fx-border-color: #a68a51; -fx-border-width: 1; -fx-background-radius: 8;");
        billingSection.setPrefWidth(400);
        Label billingHeader = new Label("1. Billing Address");
        billingHeader.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
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
        //section for selecting payment method
        VBox paymentSection = new VBox(10);
        paymentSection.setPadding(new Insets(15));
        paymentSection.setStyle("-fx-background-color: #ffdc97; -fx-border-color: #a68a51; -fx-border-width: 1; -fx-background-radius: 8;");
        paymentSection.setPrefWidth(400);
        Label paymentHeader = new Label("2. Payment Method");
        paymentHeader.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        RadioButton payOnlineOption = new RadioButton("Pay Online");
        RadioButton cashOnDeliveryOption = new RadioButton("Cash on Delivery");
        ToggleGroup paymentGroup = new ToggleGroup();
        payOnlineOption.setToggleGroup(paymentGroup);
        cashOnDeliveryOption.setToggleGroup(paymentGroup);
        cashOnDeliveryOption.setSelected(true);
        //make the functionality of online payment radiobutton
        payOnlineOption.setOnAction(e -> {
            if (payOnlineOption.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Online Payment");
                alert.setHeaderText(null);
                alert.setContentText("This part is under development. Please choose another payment method.");
                alert.showAndWait();
                cashOnDeliveryOption.setSelected(true); // Automatically select Cash on Delivery as Pay Online is under development
            }
        });

        paymentSection.getChildren().addAll(paymentHeader, payOnlineOption, cashOnDeliveryOption);

        HBox sectionsLayout = new HBox(20);
        sectionsLayout.getChildren().addAll(billingSection, paymentSection);
        //third section for order review before payment
        HBox reviewSection = new HBox(20);
        reviewSection.setPadding(new Insets(15));
        reviewSection.setStyle("-fx-background-color: #ffdc97; -fx-border-color: #a68a51; -fx-border-width: 1; -fx-background-radius: 8;");
        reviewSection.setPrefWidth(800);
        Label reviewHeader = new Label("3. Review Your Order");
        reviewHeader.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        //Label orderSummaryLabel = new Label("Order Summary:");
        double totalBill = cart.calculateTotal();
        Label subtotalLabel = new Label("Subtotal: Rs_" + totalBill);
        Label shippingCostLabel = new Label("Shipping: Rs_500");
        Label totalCostLabel = new Label("Grand Total: Rs_" + (totalBill + 500));
        Button placeOrderButton = new Button("Place Order");
        placeOrderButton.setStyle("-fx-background-color: #d88f1e; -fx-text-fill: #000000; -fx-font-size: 14px; -fx-font-weight: bold;");
        placeOrderButton.setOnAction(e -> {
           //collecting checkiut data to store it in file
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String city = cityField.getText();
            String country = countryBox.getValue();
            String paymentMethod = cashOnDeliveryOption.isSelected() ? "Cash on Delivery" : "Pay Online";
            double grandTotal = totalBill + 500; //default shipping fee

            // storing the data in text file
            try (PrintWriter writer = new PrintWriter(new FileWriter("orderDetails.txt", true))) {
                writer.println("----- Order Details -----");
                writer.println("Customer Name: " + firstName + " " + lastName);
                writer.println("Email: " + email);
                writer.println("Phone: " + phone);
                writer.println("Address: " + address);
                writer.println("City: " + city);
                writer.println("Country: " + country);
                writer.println("Payment Method: " + paymentMethod);
                writer.println("Subtotal: Rs_" + totalBill);
                writer.println("Shipping: Rs_500");
                writer.println("Grand Total: Rs_" + grandTotal);
                writer.println("\n");
                //storing revenue in seoerate file to show admin when needed
                try (PrintWriter revenueWriter = new PrintWriter(new FileWriter("revenue.txt", true))) {
                    revenueWriter.println("Revenue from Order: Rs_" + grandTotal);
                    revenueWriter.println("Total Revenue So Far: Rs_" + getTotalRevenue());
                    revenueWriter.println("\n");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            //alert for successful order
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText(null);
            alert.setContentText("Thank you! Your order has been placed successfully.");
            alert.showAndWait();

            cart.clearCart();
            checkoutStage.close();
        });

        reviewSection.getChildren().addAll(reviewHeader, subtotalLabel, shippingCostLabel, totalCostLabel, placeOrderButton);
        mainLayout.add(sectionsLayout, 0, 0);
        mainLayout.add(reviewSection, 0, 1);

        Scene checkoutScene = new Scene(mainLayout, 900, 600);
        checkoutStage.setScene(checkoutScene);
        checkoutStage.show();
    }

    // A method to read the total revenue from the file (for calculating total revenue so far)
    private double getTotalRevenue() {
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