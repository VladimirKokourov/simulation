package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.Entity;

import java.util.Stack;

public abstract class Creature extends Entity {

    protected int amountOfMoves;
    protected int speed;
    protected int hunger;
    protected int maxHunger;
    protected int hp;
    protected Class<? extends Entity> food;

    public Creature(Map map, Coordinates coordinates) {
        super(map, coordinates);
        hunger = 0;
    }

    public abstract void getSatiety();

    public void makeMove() {
        if (!map.isTypeOfEntityExist(food)) {
            return;
        }
        while (amountOfMoves > 0) {
            Stack<Coordinates> pathToFood = map.findPath(coordinates, food);
            if (pathToFood.size() != 0) {
                map.removeEntity(coordinates);
                coordinates = pathToFood.pop();
                map.addEntity(this);
            } else {
                eat(food);
            }

            if (hunger == maxHunger) {
                hp--;
            } else {
                hunger++;
            }

            amountOfMoves--;
        }
        amountOfMoves = speed;
    }

    protected void eat(Class<? extends Entity> food) {
        var foodCoordinates = map.getNeighbours(coordinates).stream()
                .filter(c -> map.isTypeOfEntityOnTheCoordinates(food, c))
                .findFirst()
                .orElse(null);
        if (foodCoordinates != null) {
            map.removeEntity(foodCoordinates);
            getSatiety();
        }
    }
}
