package ru.vkokourov.entities.plant;

import ru.vkokourov.entities.Alive;
import ru.vkokourov.entities.Entity;
import ru.vkokourov.map.Coordinates;
import ru.vkokourov.map.Map;

public abstract class Plant extends Entity implements Alive {

    public Plant(Map map, Coordinates coordinates) {
        super(map, coordinates);
    }

    @Override
    public void death() {
        map.removeEntity(coordinates);
    }
}
