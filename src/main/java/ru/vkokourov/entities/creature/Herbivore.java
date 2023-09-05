package ru.vkokourov.entities.creature;

import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.plant.Grass;

import java.util.*;

public class Herbivore extends Creature {

    public static final int AMOUNT_OF_MOVES = 2;
    public static final int START_HUNGER_VALUE = 0;
    public static final int MAX_HUNGER_VALUE = 30;
    public static final int MAX_HP_VALUE = 20;

    // amount of moves
    private int speed;
    private int hunger;
    private int hp;

    public Herbivore(Map map, Coordinates coordinates) {
        super(map, coordinates);
        map.addEntity(this);
        hunger = START_HUNGER_VALUE;
        hp = MAX_HP_VALUE;
    }

    @Override
    public void makeMove() {
        Stack<Coordinates> pathToFood;
        while (speed > 0) {
            eatGrass();
            pathToFood = findPathToFood();
            if (pathToFood.size() != 0) {
                map.removeEntity(coordinates);
                coordinates = pathToFood.pop();
                map.addEntity(this);
            }

            if (hunger == MAX_HUNGER_VALUE) {
                hp--;
            } else {
                hunger++;
            }

            speed--;
        }
        speed = AMOUNT_OF_MOVES;
    }

    private void eatGrass() {
        var grassCoordinates = map.getNeighbours(coordinates).stream()
                .filter(c -> map.getEntity(c) instanceof Grass)
                .findFirst();
        grassCoordinates.ifPresent(map::removeEntity);
    }

    @Override
    protected Stack<Coordinates> findPathToFood() {

        Stack<Coordinates> pathToFood = new Stack<>();
        if (!map.isTypeOfEntityExist(Grass.class)) {
            return pathToFood;
        }

        LinkedList<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> checked = new HashSet<>();
        HashMap<Coordinates, Coordinates> parents = new HashMap<>();
        Coordinates food = null;

        queue.add(coordinates);
        checked.add(coordinates);
        while (!queue.isEmpty()) {
            Coordinates current = queue.removeLast();
            if (map.getEntity(current) instanceof Grass) {
                food = current;
                break;
            }
            for (Coordinates neighbour : map.getNeighbours(current)) {
                if (!checked.contains(neighbour) && (map.isEmptySquare(neighbour) || map.getEntity(neighbour) instanceof Grass)) {
                    checked.add(neighbour);
                    parents.put(neighbour, current);
                    queue.addFirst(neighbour);
                }
            }
        }

        if (food == null) {
            return pathToFood;
        }

        Coordinates child = food;
        Coordinates parent = parents.get(child);
        while (parent != coordinates) {
            pathToFood.push(parent);
            child = parent;
            parent = parents.get(child);
        }

        return pathToFood;
    }
}
