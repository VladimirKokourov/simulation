package ru.vkokourov;

import java.util.HashMap;

public class Map {

    private HashMap<Coordinates, Entity> entities;

    public Map() {
        entities = new HashMap<>();
    }

    public Entity addEntity(Coordinates coordinates, Entity entity) {
        if (entities.containsKey(coordinates)) {
            return entities.get(coordinates);
        } else {
            return entities.put(coordinates, entity);
        }
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public boolean removeEntity(Coordinates coordinates) {
        return entities.remove(coordinates, getEntity(coordinates));
    }
}
