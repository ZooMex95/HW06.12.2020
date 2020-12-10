package ru.homework.framework.product;

import java.util.ArrayList;
import java.util.List;

public class Product {
    public static List<Product> listOfProducts = new ArrayList<>();
    private String name;
    private int price;
    private boolean warranty;

    public Product(String name, int price, boolean warranty) {
        this.name = name;
        this.price = price;
        this.warranty = warranty;
        listOfProducts.add(this);
    }

    public String getName() {
        return name.toLowerCase();
    }

    public int getPrice() {
        return price;
    }

    public boolean isWarranty() {
        return warranty;
    }

}
