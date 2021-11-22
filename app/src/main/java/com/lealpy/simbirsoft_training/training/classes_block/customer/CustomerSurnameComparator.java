package com.lealpy.simbirsoft_training.training.classes_block.customer;

import java.util.Comparator;

public class CustomerSurnameComparator implements Comparator<Customer>  {

    @Override
    public int compare(Customer a, Customer b) {
        return a.getSurname().compareTo(b.getSurname());
    }

}