package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;

public class Predator extends Creature {

    public Predator(Map map, Coordinates coordinates) {
        super(map, coordinates);
        speed = 3;
        amountOfMoves = speed;
        maxHunger = 30;
        satiety = 10;
        food = Herbivore.class;
    }
}
