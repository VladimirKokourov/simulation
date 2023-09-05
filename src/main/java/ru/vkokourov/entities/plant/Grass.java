package ru.vkokourov.entities.plant;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;

public class Grass extends Plant {
    public Grass(Map map, Coordinates coordinates) {
        super(map, coordinates);
        map.addEntity(this);
    }
}
