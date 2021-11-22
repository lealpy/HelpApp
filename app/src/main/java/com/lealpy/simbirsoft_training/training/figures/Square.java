package com.lealpy.simbirsoft_training.training.figures;

public class Square implements Shape {

    double length;

    Square (double length) {
        this.length = length;
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