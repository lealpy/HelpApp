package com.lealpy.simbirsoft_training.training.classes_block;

    /*
      III

      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */

public class Triangle {

    private double xA, xB, xC, yA, yB, yC, sideAB, sideBC, sideCA, angleA, angleB, angleC;

    Triangle(double xA, double yA, double xB, double yB, double xC, double yC) {
        this.xA = xA;
        this.xB = xB;
        this.xC = xC;
        this.yA = yA;
        this.yB = yB;
        this.yC = yC;

        this.sideAB = Math.sqrt(Math.pow((xB - xA), 2) + Math.pow((yB - yA), 2));
        this.sideBC = Math.sqrt(Math.pow((xB - xC), 2) + Math.pow((yB - yC), 2));
        this.sideCA = Math.sqrt(Math.pow((xB - xA), 2) + Math.pow((yB - yA), 2));

        this.angleA = Math.acos((sideAB * sideAB + sideCA * sideCA - sideBC * sideBC) / (2 * sideAB * sideCA));
        this.angleB = Math.acos((sideAB * sideAB + sideBC * sideBC - sideCA * sideCA) / (2 * sideAB * sideBC));
        this.angleC = Math.acos((sideBC * sideBC + sideCA * sideCA - sideAB * sideAB) / (2 * sideBC * sideCA));
    }

    public double getArea() {
        return 0.5 * sideAB * sideBC * Math.sin(angleB);
    }

    public double getPerimeter() {
        return sideAB + sideBC + sideCA;
    }

    public String getCentroidCoordinates() {
        double x = (xA + xB + xC) / 3;
        double y = (yA + yB + yC) / 3;
        return "Точка пересечения медиан треугольника (" + x + "; " + y + ")";
    }
}