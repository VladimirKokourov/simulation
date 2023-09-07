package ru.vkokourov.entities.plant;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;

public class Grass extends Plant {
    public Grass(Map map, Coordinates coordinates) {
        super(map, coordinates);
        maxAge = 10;
    }

    @Override
    public void grow() {
        age++;
        if (age >= maxAge) {
            reproduce();
        }
    }

    @Override
    public void reproduce() {
        death();
        new Tree(map, coordinates);
    }
}
