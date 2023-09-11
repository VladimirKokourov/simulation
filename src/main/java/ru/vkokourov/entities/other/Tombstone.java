package ru.vkokourov.entities.other;

import ru.vkokourov.entities.Entity;
import ru.vkokourov.entities.plant.Grass;
import ru.vkokourov.map.Coordinates;
import ru.vkokourov.map.Map;

public class Tombstone extends Entity {
    public Tombstone(Map map, Coordinates coordinates) {
        super(map, coordinates);
        maxAge = 2;
    }

    public void destroy() {
        age++;
        if (age >= maxAge) {
            map.removeEntity(coordinates);
            new Grass(map, coordinates);
        }
    }
}
