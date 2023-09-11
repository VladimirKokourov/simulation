package ru.vkokourov.entities.plant;

import ru.vkokourov.map.Coordinates;
import ru.vkokourov.map.Map;

public class Grass extends Plant {
    public Grass(Map map, Coordinates coordinates) {
        super(map, coordinates);
        maxAge = 15;
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
