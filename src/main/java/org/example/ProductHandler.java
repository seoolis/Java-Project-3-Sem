package org.example;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ProductHandler {

    public static List<Product> generateProducts(int count) {
        List<Product> products = new ArrayList<>();
        Random random = new Random();

        // Генерация продуктов со случайным распределением по категориям
        for (int i = 0; i < count; i++) {
            String name = "Product" + i;
            String category = random.nextBoolean() ? "Vegetable" : "Fruit";  // случайное распределение
            double weight = Math.random() * 5 + 0.5;  // случайный вес
            double price = Math.random() * 10 + 1.0; // случайная цена

            // Создаем продукт в зависимости от категории
            Product product;
            if (category.equals("Vegetable")) {
                product = new Vegetable(name, weight, price, true);  // Пример для овоща
            } else {
                product = new Fruit(name, weight, price, false);  // Пример для фрукта
            }

            products.add(product);
        }

        return products;
    }

    // Метод для записи списка продуктов в файл
    public static void writeFile(List<Product> products, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Product product : products) {
                writer.write(productToString(product));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    // Метод для чтения списка продуктов из файла
    public static List<Product> readFile(String path) {
        List<Product> products = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                products.add(parseProduct(line));
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return products;
    }

    // Преобразование объекта Product в строку для записи в файл
    private static String productToString(Product product) {
        // Возвращаем строку вида: name,category,weight,price,additionalField
        StringBuilder sb = new StringBuilder();
        sb.append(product.getName()).append(",");
        sb.append(product.getCategory()).append(",");
        sb.append(product.getWeight()).append(",");
        sb.append(product.getPrice());

        // Для овощей добавляем поле "isOrganic", для фруктов "isTropical"
        if (product instanceof Vegetable) {
            sb.append(",").append(((Vegetable) product).isOrganic());
        } else if (product instanceof Fruit) {
            sb.append(",").append(((Fruit) product).isTropical());
        }

        return sb.toString();
    }

    // Преобразование строки в объект Product
    private static Product parseProduct(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        String category = parts[1];
        double weight = Double.parseDouble(parts[2]);
        double price = Double.parseDouble(parts[3]);

        Product product;
        if (category.equals("Vegetable")) {
            boolean isOrganic = Boolean.parseBoolean(parts[4]);
            product = new Vegetable(name, weight, price, isOrganic);
        } else {
            boolean isTropical = Boolean.parseBoolean(parts[4]);
            product = new Fruit(name, weight, price, isTropical);
        }

        return product;
    }
}