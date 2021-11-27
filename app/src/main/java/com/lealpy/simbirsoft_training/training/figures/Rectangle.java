package com.lealpy.simbirsoft_training.training.figures;

public class Rectangle implements Shape {

    private double width;
    private double length;

    Rectangle(
            double width,
            double length
    ) throws Exception {
        if (width <= 0 || length <= 0) {
            throw new Exception("Стороны прямоугольника должны быть положительными");
        }
        else
        {
            this.width = width;
            this.length = length;
        }
    }

    @Override
    public double perimeter() {
        return 2 * (width + length);
    }

    @Override
    public double area() {
        return width * length;
    }
}