package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.plant.Grass;

public class Herbivore extends Creature {

    public Herbivore(Map map, Coordinates coordinates) {
        super(map, coordinates);
        speed = 2;
        amountOfMoves = speed;
        maxHunger = 20;
        satiety = 5;
        food = Grass.class;
    }
}
