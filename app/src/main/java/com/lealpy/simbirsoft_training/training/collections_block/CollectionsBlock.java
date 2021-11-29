package com.lealpy.simbirsoft_training.training.collections_block;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Набор тренингов по работе со списками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * Исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        ArrayList<T> unitedList = new ArrayList<>();
        unitedList.addAll(firstList);
        unitedList.addAll(secondList);
        unitedList.sort(Collections.reverseOrder());
        return unitedList;
    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(@NonNull List<T> inputList) {
        ArrayList<T> outputList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            outputList.add(inputList.get(i));
            for (int k = 0; k < i; k++) {
                outputList.add(inputList.get(k));
            }
        }

        return outputList;
    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        HashSet firstSet = new HashSet(firstList);
        HashSet secondSet = new HashSet(secondList);
        return firstSet.equals(secondSet);
    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(@NonNull List<T> inputList, int n) {
        if(inputList.isEmpty()) return inputList;

        ArrayList<T> list = new ArrayList<>(inputList);
        int k = n % list.size();

        if(n >= 0) {
            for (int i = 0; i < list.size(); i++) {
                if (k < inputList.size() - i) {
                    list.set(i + k, inputList.get(i));
                }
                else {
                    list.set(k - (inputList.size() - 1 - i) - 1, inputList.get(i));
                }
            }
        }
        else {
            k = -k;
            for (int i = 0; i < list.size(); i++) {
                if (k <= i) {
                    list.set(i - k, inputList.get(i));
                }
                else {
                    list.set(inputList.size() + i - k, inputList.get(i));
                }
            }
        }

        return list;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */
    public List<String> collectionTask4(@NonNull List<String> inputList, @NonNull String a,
                                        @NonNull String b) {
        if(inputList == null || a == null || b == null) throw new NullPointerException();

        for (int i = 0; i < inputList.size(); i++) {
            if(inputList.get(i) == a) {
                inputList.set(i, b);
            }
        }

        return inputList;
    }

}