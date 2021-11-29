package com.lealpy.simbirsoft_training.training.figures;

public class Square implements Shape {

    private double length;

    Square (double length) throws Exception {

        if(length <= 0) {
            throw new Exception("Сторона квадрата должна быть положительной");
        }
        else {
            this.length = length;
        }
    }

    @Override
    public double perimeter() {
        return 4 * length;
    }

    @Override
    public double area() {
        return length * length;
    }
}