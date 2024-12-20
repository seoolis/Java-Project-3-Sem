package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Graph extends JFrame {
    private final int DEFAULT_PADDING = 15;

    public Graph(Map<String, Integer> categoryCounts, Map<String, Integer> weightRanges, Map<String, Integer> priceRanges) {
        init(categoryCounts, weightRanges, priceRanges);
    }

    private void init(Map<String, Integer> categoryCounts, Map<String, Integer> weightRanges, Map<String, Integer> priceRanges) {
        // Создаем контейнер для всех графиков
        JPanel chartPanelContainer = new JPanel();
        chartPanelContainer.setLayout(new GridLayout(2, 2)); // Сетка 2x2 для графиков

        // Создаем графики для каждого набора данных
        JFreeChart categoryChart = createChart(createDataset(categoryCounts), "Количество по категориям");
        JFreeChart weightChart = createChart(createDataset(weightRanges), "Количество по весу");
        JFreeChart priceChart = createChart(createDataset(priceRanges), "Количество по цене");

        // Создаем панели для каждого графика
        ChartPanel categoryChartPanel = new ChartPanel(categoryChart);
        ChartPanel weightChartPanel = new ChartPanel(weightChart);
        ChartPanel priceChartPanel = new ChartPanel(priceChart);

        // Добавляем панели с графиками в контейнер
        chartPanelContainer.add(categoryChartPanel);
        chartPanelContainer.add(weightChartPanel);
        chartPanelContainer.add(priceChartPanel);

        // Добавляем контейнер с графиками на окно
        add(chartPanelContainer, BorderLayout.CENTER);

        // Настроим параметры окна
        pack();
        setTitle("Графики продуктов");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Метод для создания набора данных для графика
    private CategoryDataset createDataset(Map<String, Integer> map) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        map.forEach((key, value) -> {
            dataset.setValue(value, "Категория", key);
        });
        return dataset;
    }

    // Метод для создания графика
    private JFreeChart createChart(CategoryDataset dataset, String title) {
        return ChartFactory.createBarChart(
                title,       // Заголовок графика
                "Категория", // Ось X
                "Количество",// Ось Y
                dataset      // Данные
        );
    }
}
