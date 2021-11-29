package com.lealpy.simbirsoft_training.training.classes_block;

    /*
      III

      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */

import android.graphics.Point;

import java.util.Objects;

public class Triangle {

    private double sideAB;
    private double sideBC;
    private double sideCA;
    private double angleA;
    private double angleB;
    private double angleC;
    private FigurePoint pointA;
    private FigurePoint pointB;
    private FigurePoint pointC;

    class FigurePoint {
        private double x;
        private double y;

        FigurePoint (double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX () {return x;}

        public double getY () {return y;}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FigurePoint that = (FigurePoint) o;
            return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
        }
    }

    Triangle(double xA, double yA, double xB, double yB, double xC, double yC) throws Exception {

        this.pointA = new FigurePoint(xA, yA);
        this.pointB = new FigurePoint(xB, yB);
        this.pointC = new FigurePoint(xC, yC);

        if (pointA.equals(pointB) || pointA.equals(pointC) || pointB.equals(pointC)) {
            throw new Exception("Вершины треугольника не могут совпадать");
        }

        else {
            this.sideAB = Math.sqrt(Math.pow((xB - xA), 2) + Math.pow((yB - yA), 2));
            this.sideBC = Math.sqrt(Math.pow((xB - xC), 2) + Math.pow((yB - yC), 2));
            this.sideCA = Math.sqrt(Math.pow((xB - xA), 2) + Math.pow((yB - yA), 2));

            this.angleA = Math.acos((sideAB * sideAB + sideCA * sideCA - sideBC * sideBC) / (2 * sideAB * sideCA));
            this.angleB = Math.acos((sideAB * sideAB + sideBC * sideBC - sideCA * sideCA) / (2 * sideAB * sideBC));
            this.angleC = Math.acos((sideBC * sideBC + sideCA * sideCA - sideAB * sideAB) / (2 * sideBC * sideCA));
        }
    }

    public double getArea() {
        return 0.5 * sideAB * sideBC * Math.sin(angleB);
    }

    public double getPerimeter() {
        return sideAB + sideBC + sideCA;
    }

    public String getCentroidCoordinates() {
        double x = (pointA.getX() + pointB.getX() + pointC.getX()) / 3;
        double y = (pointA.getY() + pointB.getY() + pointC.getY()) / 3;
        return "Точка пересечения медиан треугольника (" + x + "; " + y + ")";
    }
}