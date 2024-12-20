package org.example;

// Абстрактный класс для продукта
public abstract class Product {
    private String name;
    private String category;
    private double weight;
    private double price;

    // Конструктор
    public Product(String name, String category, double weight, double price) {
        this.name = name;
        this.category = category;
        this.weight = weight;
        this.price = price;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Абстрактный метод, который должны реализовать дочерние классы
    public abstract void displayProductInfo();

    // Метод для вычисления стоимости на основе веса
    public double calculateCost() {
        return weight * price;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', category='" + category + "', weight=" + weight + ", price=" + price + '}';
    }
}
