package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.Entity;

public abstract class Creature extends Entity {
    public Creature(Map map, Coordinates coordinates) {
        super(map, coordinates);
    }
}
