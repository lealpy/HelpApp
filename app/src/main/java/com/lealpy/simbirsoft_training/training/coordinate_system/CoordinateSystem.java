package com.lealpy.simbirsoft_training.training.coordinate_system;

import static com.lealpy.simbirsoft_training.training.coordinate_system.Direction.DOWN;
import static com.lealpy.simbirsoft_training.training.coordinate_system.Direction.LEFT;
import static com.lealpy.simbirsoft_training.training.coordinate_system.Direction.RIGHT;
import static com.lealpy.simbirsoft_training.training.coordinate_system.Direction.UP;

public class CoordinateSystem {

    public void program() {

        int[] coordinates = new int[] {0, 0};
        coordinates = doStep(coordinates, UP);

        Direction[] directions = new Direction[] {UP, UP, LEFT, DOWN, LEFT, DOWN, DOWN, RIGHT, RIGHT, DOWN, RIGHT};
        coordinates = doSeveralSteps(directions);

    }

    public int[] doStep(int[] coordinates, Direction direction) {
        switch (direction) {
            case UP :
                coordinates[1] = coordinates[1] + 1;
                break;
            case RIGHT :
                coordinates[0] = coordinates[0] + 1;
                break;
            case DOWN :
                coordinates[1] = coordinates[1] - 1;
                break;
            case LEFT :
                coordinates[0] = coordinates[0] - 1;
                break;
        }
        return coordinates;
    }

    public int[] doSeveralSteps(Direction[] directions) {

        int[] location = new int[] {0, 0};

        for (Direction direction : directions) {
            doStep(location, direction);
        }

        return location;
    }

}