package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.plant.Grass;

public class Herbivore extends Creature {
    public static final int SATIETY_VALUE = 10;

    public Herbivore(Map map, Coordinates coordinates) {
        super(map, coordinates);
        map.addEntity(this);
        speed = 2;
        amountOfMoves = speed;
        maxHunger = 20;
        hp = 30;
        food = Grass.class;
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
