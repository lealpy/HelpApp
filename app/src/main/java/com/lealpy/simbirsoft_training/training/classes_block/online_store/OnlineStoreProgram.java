package com.lealpy.simbirsoft_training.training.classes_block.online_store;


/*
  VII

  Задача на взаимодействие между классами. Разработать систему «Интернет-магазин».
  Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
  Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
 */

import java.util.ArrayList;

public class OnlineStoreProgram {

    public void program() {

        ArrayList<Product> products = new ArrayList<>();

        Salesman salesman = new Salesman();

        products.add(
            salesman.addProduct(
            "Книга Kotlin. Программирование для профессионалов",
            "Kotlin — язык программирования со статической типизацией, который взяла на вооружение Google в ОС Android.",
            1059.50,
            42
            )
        );

        Buyer buyer1 = new Buyer();

        buyer1.buyProduct(
                products.get(0),
                1
        );

        salesman.registerSale(buyer1, products.get(0), 1);

        salesman.putOnBlacklist(buyer1);
    }

}