package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.Entity;

import java.util.Stack;

public abstract class Creature extends Entity {
    public Creature(Map map, Coordinates coordinates) {
        super(map, coordinates);
    }

    public abstract void makeMove();

    protected abstract Stack<Coordinates> findPathToFood();
}
