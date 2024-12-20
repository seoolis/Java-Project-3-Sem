package org.example;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Генерация случайных данных для продуктов
        List<Product> generatedProducts = ProductHandler.generateProducts(30);

        // Запись данных в файл
        String filePath = "products.txt";
        ProductHandler.writeFile(generatedProducts, filePath);
        System.out.println("Продукты успешно записаны в файл: " + filePath);

        // Чтение данных из файла
        List<Product> readProducts = ProductHandler.readFile(filePath);
        System.out.println("Продукты успешно прочитаны из файла:");

        // Выводим данные продуктов в консоль
        for (Product product : readProducts) {
            System.out.println(product);
        }

        // Подсчитываем количество продуктов по категориям
        Map<String, Integer> categoryCounts = new HashMap<>();
        for (Product product : readProducts) {
            categoryCounts.put(product.getCategory(), categoryCounts.getOrDefault(product.getCategory(), 0) + 1);
        }

        // Подсчитываем количество продуктов по весу (график 2)
        Map<String, Integer> weightRanges = new HashMap<>();
        for (Product product : readProducts) {
            String weightCategory = getWeightCategory(product.getWeight());
            weightRanges.put(weightCategory, weightRanges.getOrDefault(weightCategory, 0) + 1);
        }

        // Подсчитываем количество продуктов по цене (график 3)
        Map<String, Integer> priceRanges = new HashMap<>();
        for (Product product : readProducts) {
            String priceCategory = getPriceCategory(product.getPrice());
            priceRanges.put(priceCategory, priceRanges.getOrDefault(priceCategory, 0) + 1);
        }

        // Создаем и отображаем графики на одном окне
        SwingUtilities.invokeLater(() -> {
            Graph graph = new Graph(categoryCounts, weightRanges, priceRanges);
            graph.setSize(1200, 800);  // Размер окна с графиками
            graph.setLocationRelativeTo(null);  // Центрируем окно на экране
            graph.setVisible(true);  // Делаем окно видимым
        });
    }

    // Определение категории веса для продуктов
    private static String getWeightCategory(double weight) {
        if (weight < 1) {
            return "Легкие (<1 кг)";
        } else if (weight < 3) {
            return "Средние (1-3 кг)";
        } else {
            return "Тяжелые (>3 кг)";
        }
    }

    // Определение категории цены для продуктов
    private static String getPriceCategory(double price) {
        if (price < 5) {
            return "Дешевые (<1)";
        } else if (price < 10) {
            return "Средние (<10)";
        } else {
            return "Дорогие (>10)";
        }
    }
}