package ru.vkokourov.entities;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;

public abstract class Entity implements Mortal {
    protected final Map map;
    protected Coordinates coordinates;
    protected int age;

    public Entity(Map map, Coordinates coordinates) {
        this.map = map;
        this.coordinates = coordinates;
        map.addEntity(this);
        age = 0;
    }

    @Override
    public void death() {
        map.addEntity(new Tombstone(map, coordinates));
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
