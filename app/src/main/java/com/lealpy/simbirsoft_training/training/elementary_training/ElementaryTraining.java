package com.lealpy.simbirsoft_training.training.elementary_training;

import static java.lang.StrictMath.pow;

/**
 * Набор тренингов по работе с примитивными типами java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ElementaryTrainingTest.
 */
public class ElementaryTraining {

    /**
     * Метод должен возвращать среднее значение
     * для введенных параметров
     *
     * @param firstValue  первый элемент
     * @param secondValue второй элемент
     * @return среднее значение для введенных чисел
     */
    public double averageValue(int firstValue, int secondValue) {
        return (firstValue + secondValue) / 2.0;
    }

    /**
     * Пользователь вводит три числа.
     * Произвести манипуляции и вернуть сумму новых чисел
     *
     * @param firstValue  увеличить в два раза
     * @param secondValue уменьшить на три
     * @param thirdValue  возвести в квадрат
     * @return сумма новых трех чисел
     */
    public double complicatedAmount(int firstValue, int secondValue, int thirdValue) {
        return firstValue * 2 + secondValue - 3 + pow(thirdValue, 2);
    }

    /**
     * Метод должен поменять значение в соответствии с условием.
     * Если значение больше 3, то увеличить
     * на 10, иначе уменьшить на 10.
     *
     * @param value число для изменения
     * @return новое значение
     */
    public int changeValue(int value) {
        int result;
        if(value > 3) {
            result = value + 10;
        }
        else {
            result = value - 10;
        }
        return result;
    }

    /**
     * Метод должен менять местами первую
     * и последнюю цифру числа.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10, вернуть
     * то же число
     *
     * @param value число для перестановки
     * @return новое число
     */
    public int swapNumbers(int value) throws Exception {
        if (value > 99999) throw new Exception("Число должно быть максимум пятизначным");
        if(value < 10) return value;

        String str = Integer.toString(value);
        str = str.charAt(str.length() - 1) + str.substring(1, str.length() - 1) + str.charAt(0);
        return Integer.parseInt(str);
    }

    /**
     * Изменить значение четных цифр числа на ноль.
     * Счет начинать с единицы.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10 вернуть
     * то же число.
     *
     * @param value число для изменения
     * @return новое число
     */
    public int zeroEvenNumber(int value) throws Exception {
        if (value > 99999) throw new Exception("Число должно быть максимум пятизначным");
        if(value < 10) return value;

        String str = Integer.toString(value)
                .replaceAll("2", "0")
                .replaceAll("4", "0")
                .replaceAll("6", "0")
                .replaceAll("8", "0");
        return Integer.parseInt(str);
    }
}