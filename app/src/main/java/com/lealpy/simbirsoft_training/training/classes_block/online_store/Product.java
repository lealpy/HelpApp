package com.lealpy.simbirsoft_training.training.classes_block.online_store;

public class Product {

    private String name;
    private String description;
    private double price;
    private int quantity;

    Product(
            String name,
            String description,
            double price,
            int quantity
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public void sellProduct(int soldQuantity) {
        this.quantity -= soldQuantity;
    }

    public void replenishStocks(int replenishedQuantity) {
        this.quantity += replenishedQuantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

}