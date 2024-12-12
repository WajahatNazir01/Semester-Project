
package com.example.projectwithgui;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//most important class consists of all method to take products from files etc
public class CategoryManager {
    private static final List<String> categories = new ArrayList<>();
    private static final String CATEGORY_FILE = "categories.txt";
    //load all existing categories
    static {
        loadCategories();
    }

    public static void addCategory(String categoryName) {
        if (!categories.contains(categoryName)) {
            categories.add(categoryName);
            saveCategories();
            createCategoryFile(categoryName);//for creating file for every category
        } else {
            System.out.println("Category already exists!");
        }
    }

    public static List<String> getCategories() {
        return categories;
    }

    public static List<Product> getProductsForCategory(String category) {
        List<Product> products = new ArrayList<>();
        String fileName = category.replaceAll("\\s+", "_") + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                String name = "", description = "", image = "";
                double price = 0.0;
                int quantity = 0;
                double discount = 0.0;

                for (String field : fields) {
                    String[] keyValue = field.split(": ", 2);
                    if (keyValue.length < 2) continue;

                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    switch (key) {
                        case "Product Name":
                            name = value;
                            break;
                        case "Price":
                            price = Double.parseDouble(value);
                            break;
                        case "Quantity":
                            quantity = Integer.parseInt(value);
                            break;
                        case "Discount":
                            discount = Double.parseDouble(value.replace("%", ""));
                            break;
                        case "Description":
                            description = value;
                            break;
                        case "Image":
                            image = value.replace("Image Selected: ", "").trim();
                            break;
                    }
                }

                if (!name.isEmpty()) {
                    products.add(new Product(name, price, quantity, discount, description, image, category));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file for category " + category + ": " + e.getMessage());
        }

        return products;
    }


    // Save categories to a file
    private static void saveCategories() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CATEGORY_FILE))) {
            for (String category : categories) {
                writer.write(category);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving categories: " + e.getMessage());
        }
    }

    // Load categories from a file
    private static void loadCategories() {
        File file = new File(CATEGORY_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    categories.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error loading categories: " + e.getMessage());
            }
        }
    }
    public static List<Product> getAllProducts() {
        // Combine all products from all categories into a single list to display as userinterface starts
        List<Product> allProducts = new ArrayList<>();
        for (String category : getCategories()) {
            allProducts.addAll(getProductsForCategory(category));
        }
        return allProducts;
    }


    // Create a file for each category to display their products
    private static void createCategoryFile(String categoryName) {
        String fileName = categoryName.replaceAll("\\s+", "_") + ".txt";
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created for category: " + file.getName());
                }
            } catch (IOException e) {
                System.err.println("Error creating category file: " + e.getMessage());
            }
        }
    }

//    public static boolean removeProduct(Product product) {
//        if (product == null || product.getCategory() == null || product.getName() == null) {
//            System.err.println("Invalid product or category provided.");
//            return false;
//        }
//
//        String category = product.getCategory();
//        String fileName = category.replaceAll("\\s+", "_") + ".txt";
//        File file = new File(fileName);
//        boolean productRemoved = false;
//
//        if (!file.exists()) {
//            System.out.println("Category file not found: " + fileName);
//            return false;
//        }
//
//        List<String> updatedLines = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            boolean productFound = false;
//
//            while ((line = reader.readLine()) != null) {
//                if (line.contains("Product Name: " + product.getName().trim()) &&
//                        line.contains("Price: " + product.getPrice()) &&
//                        line.contains("Quantity: " + product.getQuantity())) {
//                    productFound = true; // Mark product as found
//                } else {
//                    updatedLines.add(line); // Keep this line
//                }
//            }
//
//            if (productFound) {
//                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//                    for (String updatedLine : updatedLines) {
//                        writer.write(updatedLine);
//                        writer.newLine();
//                    }
//                    productRemoved = true; // Mark product as successfully removed
//                } catch (IOException e) {
//                    System.err.println("Error writing updated category file: " + e.getMessage());
//                    return false;
//                }
//            } else {
//                System.out.println("Product not found in the category file.");
//            }
//
//        } catch (IOException e) {
//            System.err.println("Error reading category file: " + e.getMessage());
//            return false;
//        }
//
//        return productRemoved;
//    }

    public static boolean removeProduct(Product product) {
        if (product == null || product.getCategory() == null || product.getName() == null) {
            System.err.println("Invalid product or category provided.");
            return false;
        }

        String productName = product.getName().trim();
        String productPrice = String.valueOf(product.getPrice()).trim();
        String productQuantity = String.valueOf(product.getQuantity()).trim();
        boolean productRemoved = false;

        // Iterate through all category files
        List<String> categories = getCategories(); // Assuming this method returns all categories
        for (String category : categories) {
            String fileName = category.replaceAll("\\s+", "_") + ".txt";
            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("Category file not found: " + fileName);
                continue; // Skip to the next category if the file does not exist
            }

            List<String> updatedLines = new ArrayList<>();
            boolean productFound = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Check if the current line represents the product to be removed
                    if (line.contains("Product Name: " + productName) &&
                            line.contains("Price: " + productPrice) &&
                            line.contains("Quantity: " + productQuantity)) {
                        productFound = true; // Mark product as found
                    } else {
                        updatedLines.add(line); // Keep this line
                    }
                }

                // If the product was found and removed, overwrite the file with updated lines
                if (productFound) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        for (String updatedLine : updatedLines) {
                            writer.write(updatedLine);
                            writer.newLine();
                        }
                        productRemoved = true; // Mark product as successfully removed
                        System.out.println("Product removed successfully from category: " + category);
                    } catch (IOException e) {
                        System.err.println("Error writing updated category file: " + e.getMessage());
                        return false;
                    }
                } else {
                    System.out.println("Product not found in category: " + category);
                }

            } catch (IOException e) {
                System.err.println("Error reading category file: " + e.getMessage());
                return false;
            }
        }

        return productRemoved;
    }
}





