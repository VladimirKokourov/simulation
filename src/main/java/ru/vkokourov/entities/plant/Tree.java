package ru.vkokourov.entities.plant;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.Alive;

public class Tree extends Plant implements Alive {
    public Tree(Map map, Coordinates coordinates) {
        super(map, coordinates);
        maxAge = 30;
    }

    @Override
    public void grow() {
        age++;
        if (age >= maxAge) {
            death();
        } else if (age % 9 == 0) {
            reproduce();
        }
    }

    @Override
    public void reproduce() {
        map.getNeighbours(coordinates).stream()
                .filter(map::isEmptySquare)
                .findFirst()
                .ifPresent(reproduceCoordinates -> new Grass(map, reproduceCoordinates));
    }
}
