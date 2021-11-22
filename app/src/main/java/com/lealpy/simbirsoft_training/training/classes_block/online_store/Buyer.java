package com.lealpy.simbirsoft_training.training.classes_block.online_store;

import java.util.Random;

public class Buyer {

    Random random = new Random();

    private int id;
    boolean isInBlacklist = false;

    Buyer() {
        this.id = random.nextInt(2147483647);
    }

    public void buyProduct(Product product, int quantity) {

    }

}