package ru.homework.framework.product;

import javax.print.attribute.standard.PrinterURI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Product {
    public static List<Product> listOfProducts = new ArrayList<>();
    private String name;
    private int price;
    private boolean warranty;
    private String description;

    public Product(String name, int price, boolean warranty) {
        this.name = name;
        this.price = price;
        this.warranty = warranty;
        listOfProducts.add(this);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isWarranty() {
        return warranty;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setWarranty(boolean warranty) {
        this.warranty = warranty;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
