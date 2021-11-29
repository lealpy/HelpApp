package com.lealpy.simbirsoft_training.training.classes_block.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
      (Продолжение. Начало в файле Customer.java)

      Создать массив объектов данного класса.
      Вывести сведения относительно абонентов, у которых время городских переговоров
      превышает заданное.  Сведения относительно абонентов, которые пользовались
      междугородной связью. Список абонентов в алфавитном порядке.
 */

public class CustomerProgram {

    public void function() {

        Customer ivanovSV = new Customer(
                "Иванов",
                "Сергей",
                "Витальевич",
                "г. Москва, ул. Тверская, д. 13, кв. 2",
                "2434 4353 6546 3245",
                1000.00,
                0,
                250,
                1000
        );
        ivanovSV.makeIntercityCall(23);
        ivanovSV.makeCityCall(1245);

        Customer petrovVA = new Customer(
                "Петров",
                "Василий",
                "Алексеевич",
                "г. Владимир, ул. Ленина, д. 4, кв. 12",
                "4263 2383 6236 9845",
                1290.12,
                0,
                300,
                1200
        );
        petrovVA.makeIntercityCall(202);
        petrovVA.makeCityCall(130);

        Customer sergeevEI = new Customer(
                "Сергеев",
                "Егор",
                "Иванович",
                "г. Новосибирск, ул. Фрунзе, д. 19, кв. 8",
                "5432 4124 1465 2956",
                16.76,
                0,
                0,
                300
        );
        sergeevEI.makeIntercityCall(0);
        sergeevEI.makeCityCall(500);

        //Массив объектов класса:
        Customer[] customers = new Customer[]{ivanovSV, petrovVA, sergeevEI};

        // Сведения относительно абонентов, у которых время городских переговоров превышает заданное:
        Customer[] customersCityOvertime = getCustomersCityOvertime(customers);

        // Сведения относительно абонентов, которые пользовались междугородной связью:
        Customer[] customersUsedIntercity = getCustomersUsedIntercity(customers);

        // Список абонентов в алфавитном порядке:
        List<Customer> sortedCustomers = getSortedCustomers(customers);

    }

    public Customer[] getCustomersCityOvertime(Customer[] customers) {
        ArrayList<Customer> customersCityOvertime = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer.getCurrentMinutesCity() > customer.getDefaultMinutesCity()) {
                customersCityOvertime.add(customer);
            }
        }

        return (Customer[]) customersCityOvertime.toArray();
    }

    public Customer[] getCustomersUsedIntercity(Customer[] customers) {
        ArrayList<Customer> customersUsedIntercity = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer.getCurrentMinutesIntercity() < customer.getDefaultMinutesCity()) {
                customersUsedIntercity.add(customer);
            }
        }

        return (Customer[]) customersUsedIntercity.toArray();
    }

    public List<Customer> getSortedCustomers(Customer[] customers) {
        ArrayList<Customer> sortedCustomersArrayList = new ArrayList<>(Arrays.asList(customers));
        sortedCustomersArrayList.sort(new CustomerSurnameComparator());
        return sortedCustomersArrayList;
    }

}