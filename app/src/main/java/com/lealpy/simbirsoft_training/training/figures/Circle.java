package com.lealpy.simbirsoft_training.training.figures;

public class Circle implements Shape {

    private double diameter;

    Circle(double diameter) throws Exception {
        if (diameter <= 0) {
            throw new Exception("Диаметр должен быть положительным");
        }
        else {
            this.diameter = diameter;
        }
    }

    @Override
    public double perimeter() {
        return Math.PI * diameter;
    }

    @Override
    public double area() {
        return Math.PI * diameter * diameter / 4;
    }
}