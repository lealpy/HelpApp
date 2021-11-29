package com.lealpy.simbirsoft_training.training.classes_block.online_store;

public class Salesman {

    public Product addProduct(
            String name,
            String description,
            double price,
            int quantity
    ) {
        return new Product(
                name,
                description,
                price,
                quantity
        );
    }

    public void registerSale (Buyer buyer, Product product, int quantity) {
        if(!buyer.isInBlacklist) {
            product.sellProduct(quantity);
        }
    }

    public void putOnBlacklist(Buyer buyer) {
        buyer.isInBlacklist = true;
    }

}