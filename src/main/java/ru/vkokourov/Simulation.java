package ru.vkokourov;

import ru.vkokourov.entities.Rock;
import ru.vkokourov.entities.creature.Creature;
import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.entities.plant.Grass;
import ru.vkokourov.entities.plant.Tree;

public class Simulation {

    private final Map map;
    private final Renderer renderer;
    private int countTurn;

    public Simulation() {
        map = new Map(30, 10);
        renderer = new Renderer(map);
        countTurn = 0;
    }

    public void init() {
        for (int i = 0; i < 20; i++) {
            map.addEntity(new Grass(map, map.getCoordinatesRandomEmptySquare()));
            map.addEntity(new Rock(map, map.getCoordinatesRandomEmptySquare()));
            map.addEntity(new Tree(map, map.getCoordinatesRandomEmptySquare()));
        }
        for (int i = 0; i < 10; i++) {
            map.addEntity(new Herbivore(map, map.getCoordinatesRandomEmptySquare()));
        }
        for (int i = 0; i < 3; i++) {
            map.addEntity(new Predator(map, map.getCoordinatesRandomEmptySquare()));
        }
        renderer.render();
    }

    public void makeTurn() {
        for (Creature creature : map.getAllCreatures()) {
            creature.makeMove();
        }
        renderer.render();
        countTurn++;
        System.out.println("Turn " + countTurn);
    }

    public boolean isGameOver() {
        return !map.isTypeOfEntityExist(Herbivore.class) || !map.isTypeOfEntityExist(Predator.class);
    }
}
