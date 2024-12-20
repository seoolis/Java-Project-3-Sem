package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static Connection connection;
    public static Statement statement;

    public static void connectDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        statement = connection.createStatement();
    }

    public static void createTableProduct() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS product (" +
                "name TEXT, " +
                "category TEXT, " +
                "weight REAL, " +
                "price REAL)";
        statement.execute(createTableSQL);
    }

    public static void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO product (name, category, weight, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setDouble(3, product.getWeight());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.executeUpdate();
        }
    }

    public static List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT name, category, weight, price FROM product";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String category = resultSet.getString("category");
            double weight = resultSet.getDouble("weight");
            double price = resultSet.getDouble("price");

            Product product;
            if (category.equals("Vegetable")) {
                product = new Vegetable(name, weight, price, true);  // Пример для овоща
            } else {
                product = new Fruit(name, weight, price, false);  // Пример для фрукта
            }

            products.add(product);
        }

        resultSet.close();
        return products;
    }

    public static void closeDB() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Ошибка закрытия базы данных: " + e.getMessage());
        }
    }
}