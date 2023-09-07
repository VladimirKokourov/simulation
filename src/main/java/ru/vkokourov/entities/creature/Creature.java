package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.Entity;
import ru.vkokourov.entities.other.Tombstone;

import java.util.Stack;

public abstract class Creature extends Entity {

    protected int amountOfMoves;
    protected int speed;
    protected int hunger;
    protected int maxHunger;
    protected int satiety;
    protected Class<? extends Entity> food;

    public Creature(Map map, Coordinates coordinates) {
        super(map, coordinates);
        hunger = 0;
    }

    public void makeMove() {
        hunger++;
        if (hunger >= maxHunger) {
            death();
            return;
        }
        if (!map.isTypeOfEntityExist(food)) {
            return;
        }
        amountOfMoves = speed;
        while (amountOfMoves > 0) {
            Stack<Coordinates> pathToFood = map.findPath(coordinates, food);
            if (pathToFood.size() != 0) {
                map.removeEntity(coordinates);
                coordinates = pathToFood.pop();
                map.addEntity(this);
            } else {
                eat(food);
            }
            amountOfMoves--;
        }
    }

    protected void eat(Class<? extends Entity> food) {
        var foodCoordinates = map.getNeighbours(coordinates).stream()
                .filter(c -> map.isTypeOfEntityOnTheCoordinates(food, c))
                .findFirst()
                .orElse(null);
        if (foodCoordinates != null) {
            map.getEntity(foodCoordinates).death();
            getSatiety();
        }
    }

    public void getSatiety() {
        if (hunger < satiety) {
            hunger = 0;
        } else {
            hunger -= satiety;
        }
    }

    @Override
    public void death() {
        super.death();
        map.addEntity(new Tombstone(map, coordinates));
    }
}
