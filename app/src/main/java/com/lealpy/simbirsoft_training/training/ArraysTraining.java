package com.lealpy.simbirsoft_training.training;

import java.util.Arrays;

/**
 * Набор тренингов по работе с массивами в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ArraysTrainingTest.
 */
public class ArraysTraining {

    /**
     * Метод должен сортировать входящий массив
     * по возрастранию пузырьковым методом
     *
     * @param valuesArray массив для сортировки
     * @return отсортированный массив
     */
    public int[] sort(int[] valuesArray) {
        for(int i = valuesArray.length - 1 ; i > 0 ; i--) {
            for(int j = 0 ; j < i ; j++) {
                if(valuesArray[j] > valuesArray[j + 1]) {
                    int currentElement = valuesArray[j];
                    valuesArray[j] = valuesArray[j + 1];
                    valuesArray[j + 1] = currentElement;
                }
            }
        }
        return valuesArray;
    }

    /**
     * Метод должен возвращать максимальное
     * значение из введенных. Если входящие числа
     * отсутствуют - вернуть 0
     *
     * @param values входящие числа
     * @return максимальное число или 0
     */
    public int maxValue(int... values) {
        if (values.length == 0) return 0;
        return Arrays.stream(values).max().getAsInt();
    }

    /**
     * Переставить элементы массива
     * в обратном порядке
     *
     * @param array массив для преобразования
     * @return входящий массив в обратном порядке
     */
    public int[] reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int currentElement = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = currentElement;
        }
        return array;
    }

    /**
     * Метод должен вернуть массив,
     * состоящий из чисел Фибоначчи
     *
     * @param numbersCount количество чисел Фибоначчи,
     *                     требуемое в исходящем массиве.
     *                     Если numbersCount < 1, исходный
     *                     массив должен быть пуст.
     * @return массив из чисел Фибоначчи
     */
    public int[] fibonacciNumbers(int numbersCount) {
        if(numbersCount < 1) return new int[]{};

        int[] array = new int[numbersCount];
        for(int i = 0; i < array.length; i++) {
            if(i < 2) {
                array[i] = 1;
            }
            else {
                array[i] = array[i - 2] + array[i - 1];
            }
        }
        return array;
    }

    /**
     * В данном массиве найти максимальное
     * количество одинаковых элементов.
     *
     * @param array массив для выборки
     * @return количество максимально встречающихся
     * элементов
     */
    public int maxCountSymbol(int[] array) {
        if (array.length == 0) return 0;

        sort(array);
        int counter = 1;
        int maxValue = 1;
        for (int i = 0; i < array.length - 1; i++) {
            if(array[i] == array[i + 1]) {
                counter ++;
                if(counter > maxValue) maxValue = counter;
            }
            else {
                if(counter > maxValue) maxValue = counter;
                counter = 1;
            }
        }
        return maxValue;
    }
}