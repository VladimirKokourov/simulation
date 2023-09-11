package ru.vkokourov.entities;

import ru.vkokourov.map.Coordinates;
import ru.vkokourov.map.Map;

public abstract class Entity {

    protected final Map map;
    protected Coordinates coordinates;
    protected int age;
    protected int maxAge;

    public Entity(Map map, Coordinates coordinates) {
        this.map = map;
        this.coordinates = coordinates;
        map.addEntity(this);
        age = 0;
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
                + "[" + printNumber(coordinates.x())
                + "," + printNumber(coordinates.y())
                + "]"
                + " AGE-" + printNumber(age)
                + "/" + maxAge;
    }

    protected String printNumber(int number) {
        if (number > 9) {
            return String.valueOf(number);
        } else {
            return " " + number;
        }
    }
}
