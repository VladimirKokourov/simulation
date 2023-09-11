package ru.vkokourov.simulation;

import ru.vkokourov.entities.Alive;
import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.entities.other.Rock;
import ru.vkokourov.entities.other.Tombstone;
import ru.vkokourov.entities.plant.Grass;
import ru.vkokourov.entities.plant.Tree;
import ru.vkokourov.map.Map;

public class Simulation {

    private final Map map;
    private final Renderer renderer;
    private int countTurn;
    private boolean isGameOver;

    public Simulation() {
        map = new Map(30, 12);
        renderer = new Renderer(map);
        countTurn = 0;
        isGameOver = false;
    }

    public void init() {
        for (int i = 0; i < 12; i++) {
            new Rock(map, map.getCoordinatesRandomEmptySquare());
        }
        for (int i = 0; i < 20; i++) {
            new Tree(map, map.getCoordinatesRandomEmptySquare());
        }
        for (int i = 0; i < 25; i++) {
            new Grass(map, map.getCoordinatesRandomEmptySquare());
        }
        for (int i = 0; i < 12; i++) {
            new Herbivore(map, map.getCoordinatesRandomEmptySquare());
        }
        for (int i = 0; i < 2; i++) {
            new Predator(map, map.getCoordinatesRandomEmptySquare());
        }
        renderer.render();
    }

    public void makeTurn() {
        countTurn++;

        for (Alive aliveEntity : map.getAliveEntities()) {
            aliveEntity.grow();
        }
        for (Herbivore herbivore : map.getAllHerbivores()) {
            herbivore.makeMove();
        }
        for (Predator predator : map.getAllPredators()) {
            predator.makeMove();
        }
        for (Tombstone tombstone : map.getAllTombstones()) {
            tombstone.destroy();
        }
        System.out.println("Turn:" + countTurn + " " +
                "Predators:" + map.getAllPredators().size() + " " +
                "Herbivores:" + map.getAllHerbivores().size() + " ");
        renderer.render();

        if (!map.isTypeOfEntityExist(Herbivore.class) || !map.isTypeOfEntityExist(Predator.class)) {
            isGameOver = true;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}