package com.lealpy.simbirsoft_training.training;

import java.util.Arrays;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringsTraining {

    /**
     * Метод по созданию строки,
     * состоящей из нечетных символов
     * входной строки в том же порядке
     * (нумерация символов идет с нуля)
     *
     * @param text строка для выборки
     * @return новая строка из нечетных
     * элементов строки text
     */
    public String getOddCharacterString(String text) {
        char[] symbols = text.toCharArray();
        int lastIndex = text.length() - 1;
        String oddLetters = "";

        for (int i = 1; i <= lastIndex; i += 2) {
            oddLetters = oddLetters + symbols[i];
        }

        return oddLetters;
    }

    /**
     * Метод для определения количества
     * символов, идентичных последнему
     * в данной строке
     *
     * @param text строка для выборки
     * @return массив с номерами символов,
     * идентичных последнему. Если таких нет,
     * вернуть пустой массив
     */
    public int[] getArrayLastSymbol(String text) {
        int[] arrayLastSymbol = new int[] {};

        if(text.length() == 0) return arrayLastSymbol; //////////////////////////////

        char[] symbols = text.toCharArray();
        char lastSymbol = symbols[symbols.length - 1];

        for (int i = 0; i < symbols.length - 1; i++) {
            if(symbols[i] == lastSymbol) {
                arrayLastSymbol = Arrays.copyOf(arrayLastSymbol, arrayLastSymbol.length + 1);
                arrayLastSymbol[arrayLastSymbol.length - 1] = i;
            }
        }

        return arrayLastSymbol;
    }

    /**
     * Метод по получению количества
     * цифр в строке
     *
     * @param text строка для выборки
     * @return количество цифр в строке
     */
    public int getNumbersCount(String text) {
        char[] symbols = text.toCharArray();
        int numbersCount = 0;

        for (char symbol : symbols) {
            if(Character.isDigit(symbol)) {
                numbersCount++;
            }
        }

        return numbersCount;
    }

    /**
     * Дан текст. Заменить все цифры
     * соответствующими словами.
     *
     * @param text текст для поиска и замены
     * @return текст, где цифры заменены словами
     */
    public String replaceAllNumbers(String text) {
        text = text.replaceAll("0", "zero");
        text = text.replaceAll("1", "one");
        text = text.replaceAll("2", "two");
        text = text.replaceAll("3", "three");
        text = text.replaceAll("4", "four");
        text = text.replaceAll("5", "five");
        text = text.replaceAll("6", "six");
        text = text.replaceAll("7", "seven");
        text = text.replaceAll("8", "eight");
        text = text.replaceAll("9", "nine");

        return text;
    }

    /**
     * Метод должен заменить заглавные буквы
     * на прописные, а прописные на заглавные
     *
     * @param text строка для изменения
     * @return измененная строка
     */
    public String capitalReverse(String text) {
        char[] symbols = text.toCharArray();

        for(int i = 0; i < symbols.length; i ++) {
            if(Character.isLowerCase(symbols[i])) {
                symbols[i] = Character.toUpperCase(symbols[i]);
            }
            else {
                symbols[i] = Character.toLowerCase(symbols[i]);
            }
        }

        return new String(symbols);
    }
}