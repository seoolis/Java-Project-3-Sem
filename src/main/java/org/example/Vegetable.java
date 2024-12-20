package org.example;

// Класс для овощей, наследующий от Product
public class Vegetable extends Product {

    // Дополнительное поле, которое имеет смысл только для овощей
    private boolean isOrganic;

    // Конструктор
    public Vegetable(String name, double weight, double price, boolean isOrganic) {
        super(name, "Vegetable", weight, price);  // Вызываем конструктор родительского класса
        this.isOrganic = isOrganic;
    }

    // Геттер и сеттер для поля isOrganic
    public boolean isOrganic() {
        return isOrganic;
    }

    public void setOrganic(boolean organic) {
        isOrganic = organic;
    }

    // Реализация абстрактного метода displayProductInfo
    @Override
    public void displayProductInfo() {
        System.out.println("Vegetable: " + getName() + ", Weight: " + getWeight() + "kg, Price per kg: " + getPrice() + "$, Organic: " + (isOrganic ? "Yes" : "No"));
    }
}

