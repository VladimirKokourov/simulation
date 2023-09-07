package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;

import java.util.stream.Stream;

public class Predator extends Creature {

    public Predator(Map map, Coordinates coordinates) {
        super(map, coordinates);
        speed = 3;
        amountOfMoves = speed;
        maxHunger = 30;
        satiety = 10;
        maxAge = 40;
        food = Herbivore.class;
    }

    @Override
    public void reproduce() {
        if (map.getNeighbours(coordinates).stream().anyMatch(c -> map.isTypeOfEntityOnTheCoordinates(Predator.class, c))) {
            map.getNeighbours(coordinates).stream()
                    .filter(map::isEmptySquare)
                    .findFirst()
                    .ifPresent(reproduceCoordinates -> new Predator(map, reproduceCoordinates));
        }
    }
}
