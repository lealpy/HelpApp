package com.lealpy.simbirsoft_training.training.classes_block;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

    /*
      II

      Создать класс, содержащий динамический массив и количество элементов в нем.
      Добавить конструктор, который выделяет память под заданное количество элементов.
      Добавить методы, позволяющие заполнять массив случайными числами,
      переставлять в данном массиве элементы в случайном порядке, находить количество
      различных элементов в массиве, выводить массив на экран.
     */

class RandomArrayList {

    private ArrayList<Integer> arrayList;
    private int numberOfElements;

    RandomArrayList(int numberOfElements) {
        this.numberOfElements = numberOfElements;
        arrayList = new ArrayList(numberOfElements);
        fillArrayList();
    }

    private void fillArrayList() {
        for (int i = 0; i < numberOfElements; i++) {
            Random random = new Random();
            arrayList.add(i, random.nextInt(100));
        }
    }

    public void mixArrayList() {
        Collections.shuffle(arrayList);
    }

    public int getNumberOfDifferentElements() {
        return new HashSet<Integer>(arrayList).size();
    }

    public void printArrayList() {
        arrayList.forEach((element) -> Log.i("Information", element.toString()));
    }

}