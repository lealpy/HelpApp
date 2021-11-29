package com.lealpy.simbirsoft_training.training.classes_block;

    /*
      I

      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию, которая находит наибольшее
      значение из этих двух переменных.
     */

public class Numbers {

    private int num1;
    private int num2;

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum1(int num) {
        this.num1 = num;
    }

    public void setNum2(int num) {
        this.num2 = num;
    }

    public int getSum() {
        return num1 + num2;
    }

    public int getMax() {
        return Math.max (num1, num2);
    }
}
