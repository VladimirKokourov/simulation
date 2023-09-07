package ru.vkokourov;

import ru.vkokourov.entities.Alive;
import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.entities.other.Rock;
import ru.vkokourov.entities.plant.Grass;
import ru.vkokourov.entities.plant.Tree;

public class Simulation {

    private final Map map;
    private final Renderer renderer;
    private int countTurn;
    private boolean isGameOver;

    public Simulation() {
        map = new Map(30, 10);
        renderer = new Renderer(map);
        countTurn = 0;
        isGameOver = false;
    }

    public void init() {
        for (int i = 0; i < 15; i++) {
            new Grass(map, map.getCoordinatesRandomEmptySquare());
            new Rock(map, map.getCoordinatesRandomEmptySquare());
            new Tree(map, map.getCoordinatesRandomEmptySquare());
        }
        for (int i = 0; i < 10; i++) {
            new Herbivore(map, map.getCoordinatesRandomEmptySquare());
        }
        for (int i = 0; i < 2; i++) {
            new Predator(map, map.getCoordinatesRandomEmptySquare());
        }
        renderer.render();
    }

    public void makeTurn() {
        for (Alive aliveEntity : map.getAliveEntities()) {
            aliveEntity.grow();
        }

        for (Herbivore herbivore : map.getAllHerbivores()) {
            herbivore.makeMove();
        }
        for (Predator predator : map.getAllPredators()) {
            predator.makeMove();
        }
        renderer.render();
        countTurn++;
        System.out.println("Turn " + countTurn);
        System.out.println(map.getAllPredators().size());
        System.out.println(map.getAllHerbivores().size());
        if (!map.isTypeOfEntityExist(Herbivore.class) || !map.isTypeOfEntityExist(Predator.class)) {
            isGameOver = true;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
