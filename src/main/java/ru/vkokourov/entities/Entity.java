package ru.vkokourov.entities;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.entities.creature.Predator;

public abstract class Entity {
    protected final Map map;
    protected Coordinates coordinates;
    protected int age;

    public Entity(Map map, Coordinates coordinates) {
        this.map = map;
        this.coordinates = coordinates;
        map.addEntity(this);
        age = 0;
    }

    public void death() {
        map.removeEntity(coordinates);
    }

    public Predator castPredator() {
        if (this instanceof Predator) {
            return (Predator) this;
        } else {
            throw new RuntimeException("Cast " + this.getClass().getSimpleName() + ".class Exception");
        }
    }

    public Herbivore castHerbivore() {
        if (this instanceof Herbivore) {
            return (Herbivore) this;
        } else {
            throw new RuntimeException("Cast " + this.getClass().getSimpleName() + ".class Exception");
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + " [" + coordinates.x()
                + ", " + coordinates.y()
                + "]";
    }
}
