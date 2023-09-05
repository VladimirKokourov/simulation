package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.Entity;

import java.util.Stack;

public class Predator extends Creature {
    public Predator(Map map, Coordinates coordinates) {
        super(map, coordinates);
    }

    @Override
    public void makeMove() {

    }

    @Override
    protected Stack<Coordinates> findPathToFood() {
        return null;
    }
}
