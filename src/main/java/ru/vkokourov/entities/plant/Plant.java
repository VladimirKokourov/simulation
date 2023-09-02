package ru.vkokourov.entities.plant;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.Entity;

public abstract class Plant extends Entity {

    public Plant(Map map, Coordinates coordinates) {
        super(map, coordinates);
    }
}
