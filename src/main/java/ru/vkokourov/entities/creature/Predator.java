package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.plant.Grass;

public class Predator extends Creature {
    public static final int SATIETY_VALUE = 10;

    public Predator(Map map, Coordinates coordinates) {
        super(map, coordinates);
        map.addEntity(this);
        speed = 3;
        amountOfMoves = speed;
        maxHunger = 30;
        hp = 20;
        food = Herbivore.class;
    }

    @Override
    public void getSatiety() {
        if (hunger < SATIETY_VALUE) {
            hunger = 0;
        } else {
            hunger -= SATIETY_VALUE;
        }
    }
}
