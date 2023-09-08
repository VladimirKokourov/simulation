package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.plant.Grass;

public class Herbivore extends Creature {

    public Herbivore(Map map, Coordinates coordinates) {
        super(map, coordinates);
        hunger = 4;
        speed = 2;
        amountOfMoves = speed;
        maxHunger = 20;
        satiety = 8;
        maxAge = 15;
        reproduceAge = 3;
        reproduceHunger = 10;
        food = Grass.class;
    }

    @Override
    public void reproduce() {
        super.reproduce();
        if (map.getNeighbours(coordinates).stream().anyMatch(c -> map.isTypeOfEntityOnTheCoordinates(Herbivore.class, c))) {
            map.getNeighbours(coordinates).stream()
                    .filter(map::isEmptySquare)
                    .findFirst()
                    .ifPresent(reproduceCoordinates -> new Herbivore(map, reproduceCoordinates));
        }
    }
}
