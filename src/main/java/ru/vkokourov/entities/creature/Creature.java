package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.Alive;
import ru.vkokourov.entities.Entity;
import ru.vkokourov.entities.other.Tombstone;

import java.util.Stack;

public abstract class Creature extends Entity implements Alive {

    protected int amountOfMoves;
    protected int speed;
    protected int hunger;
    protected int maxHunger;
    protected int satiety;
    protected Class<? extends Entity> food;

    public Creature(Map map, Coordinates coordinates) {
        super(map, coordinates);
        hunger = 5;
    }

    public void makeMove() {
        if (!map.isTypeOfEntityExist(food)) {
            return;
        }
        amountOfMoves = speed;
        while (amountOfMoves > 0) {
            // decide priority goal for going
            Class<? extends Entity> goal = isReadyToReproduce() ? this.getClass() : food;

            Stack<Coordinates> pathToGoal = map.findPath(coordinates, goal);

            if (pathToGoal.size() != 0) {
                map.removeEntity(coordinates);
                coordinates = pathToGoal.pop();
                map.addEntity(this);
            } else if (isReadyToReproduce()) {
                reproduce();
            } else {
                eat(food);
            }
            amountOfMoves--;
        }
    }

    private boolean isReadyToReproduce() {
        return age > 5 && hunger < 10;
    }

    protected void eat(Class<? extends Entity> foodClazz) {
        var foodCoordinates = map.getNeighbours(coordinates).stream()
                .filter(c -> map.isTypeOfEntityOnTheCoordinates(foodClazz, c))
                .findFirst()
                .orElse(null);
        if (foodCoordinates != null) {
            Alive food = (Alive) map.getEntity(foodCoordinates);
            food.death();
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
    public void grow() {
        hunger += speed;
        age++;
        if (age >= maxAge || hunger >= maxHunger) {
            death();
        }
    }

    @Override
    public void death() {
        map.removeEntity(coordinates);
        new Tombstone(map, coordinates);
    }
}
