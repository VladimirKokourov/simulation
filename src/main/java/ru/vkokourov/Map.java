package ru.vkokourov;

import ru.vkokourov.entities.Entity;
import ru.vkokourov.entities.plant.Grass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {

    private final int weight;
    private final int height;
    private final HashMap<Coordinates, Entity> entities;

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

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates, getEntity(coordinates));
    }

    public List<Coordinates> getNeighbours(Coordinates coordinates) {
        List<Coordinates> neighbours = new ArrayList<>();
        int x = coordinates.x();
        int y = coordinates.y();
        neighbours.add(new Coordinates(x, y + 1));
        neighbours.add(new Coordinates(x + 1, y));
        neighbours.add(new Coordinates(x, y - 1));
        neighbours.add(new Coordinates(x - 1, y));

        return neighbours.stream().filter(this::isCoordinatesExist).toList();
    }

    public boolean isCoordinatesExist(Coordinates coordinates) {
        int x = coordinates.x();
        int y = coordinates.y();
        return x > 0 && x <= weight && y > 0 && y <= height;
    }

    public boolean isEmptySquare(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public boolean isTypeOfEntityExist(Class<?> entityClass) {
        return entities.values().stream()
                .anyMatch(entity -> entity.getClass() == entityClass);
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }
}
