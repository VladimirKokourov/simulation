package ru.vkokourov;

import ru.vkokourov.entities.Entity;

import java.util.HashMap;

public class Map {

    private final int weight;
    private final int height;
    private HashMap<Coordinates, Entity> entities;

    public Map(int weight, int height) {
        this.weight = weight;
        this.height = height;
        entities = new HashMap<>();
    }

    public void addEntity(Entity entity) {
        Coordinates coordinates = entity.getCoordinates();
        if (entities.containsKey(coordinates)) {
            entities.replace(coordinates, entity);
        } else {
            entities.put(coordinates, entity);
        }
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public boolean removeEntity(Coordinates coordinates) {
        return entities.remove(coordinates, getEntity(coordinates));
    }

    public boolean isEmptySquare(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }
}
