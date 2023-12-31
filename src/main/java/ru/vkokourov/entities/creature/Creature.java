package ru.vkokourov.entities.creature;

import ru.vkokourov.entities.Alive;
import ru.vkokourov.entities.Entity;
import ru.vkokourov.entities.other.Tombstone;
import ru.vkokourov.map.Coordinates;
import ru.vkokourov.map.Map;

import java.util.Stack;

public abstract class Creature extends Entity implements Alive {

    protected int amountOfMoves;
    protected int speed;
    protected int hunger;
    protected int maxHunger;
    protected int satiety;
    protected int reproduceAge;
    protected int reproduceHunger;
    protected Class<? extends Entity> food;

    public Creature(Map map, Coordinates coordinates) {
        super(map, coordinates);
    }

    public void makeMove() {
        amountOfMoves = speed;
        while (amountOfMoves > 0) {
            hunger++;
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
        if (age >= maxAge || hunger >= maxHunger) {
            death();
        }
    }

    protected boolean isReadyToReproduce() {
        return age > reproduceAge && hunger < reproduceHunger;
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
        age++;
    }

    @Override
    public void death() {
        map.removeEntity(coordinates);
        new Tombstone(map, coordinates);
    }

    @Override
    public String toString() {
        return super.toString()
                + " HUNGER-" + printNumber(hunger)
                + "/" + maxHunger;
    }
}
