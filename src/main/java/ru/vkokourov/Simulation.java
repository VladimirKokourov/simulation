package ru.vkokourov;

import ru.vkokourov.entities.Alive;
import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.entities.other.Rock;
import ru.vkokourov.entities.other.Tombstone;
import ru.vkokourov.entities.plant.Grass;
import ru.vkokourov.entities.plant.Tree;

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
        System.out.println();
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
        StringBuilder sb = new StringBuilder();
        sb.append("Turn:").append(countTurn).append(" ");
        sb.append("Predators:").append(map.getAllPredators().size()).append(" ");
        sb.append("Herbivores:").append(map.getAllHerbivores().size()).append(" ");
        System.out.println(sb);
        renderer.render();

        if (!map.isTypeOfEntityExist(Herbivore.class) || !map.isTypeOfEntityExist(Predator.class)) {
            isGameOver = true;
        }
        System.out.println();
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
