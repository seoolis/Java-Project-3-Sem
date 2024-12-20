package org.example;

// Класс для фруктов, наследующий от Product
public class Fruit extends Product {

    // Дополнительное поле, которое имеет смысл только для фруктов
    private boolean isTropical;

    // Конструктор
    public Fruit(String name, double weight, double price, boolean isTropical) {
        super(name, "Fruit", weight, price);  // Вызываем конструктор родительского класса
        this.isTropical = isTropical;
    }

    // Геттер и сеттер для поля isTropical
    public boolean isTropical() {
        return isTropical;
    }

    public void setTropical(boolean tropical) {
        isTropical = tropical;
    }

    // Реализация абстрактного метода displayProductInfo
    @Override
    public void displayProductInfo() {
        System.out.println("Fruit: " + getName() + ", Weight: " + getWeight() + "kg, Price per kg: " + getPrice() + "$, Tropical: " + (isTropical ? "Yes" : "No"));
    }
}
