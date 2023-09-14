package ru.vkokourov.simulation;

import ru.vkokourov.action.create.*;
import ru.vkokourov.action.turn.AliveTurnAction;
import ru.vkokourov.action.turn.CreaturesTurnAction;
import ru.vkokourov.action.turn.TombstoneTurnAction;
import ru.vkokourov.action.turn.TurnAction;
import ru.vkokourov.entities.Alive;
import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.entities.other.Tombstone;
import ru.vkokourov.map.Map;

public class Simulation {

    private Renderer renderer;
    private Map map;
    private int countTurn;
    private boolean isGameOver;

    public void init() {
        map = new Map(30, 12);
        renderer = new Renderer(map);
        isGameOver = false;
        countTurn = 0;

        CreateAction createAction;
        createAction = new RockCreateAction(map);
        createAction.create(12);

        createAction = new TreeCreateAction(map);
        createAction.create(20);

        createAction = new GrassCreateAction(map);
        createAction.create(25);

        createAction = new HerbivoreCreateAction(map);
        createAction.create(12);

        createAction = new PredatorCreateAction(map);
        createAction.create(2);

        renderer.render();
    }

    public void makeTurn() {
        countTurn++;

        TurnAction turnAction;
        turnAction = new AliveTurnAction(map);
        turnAction.makeTurn();

        turnAction = new CreaturesTurnAction(map);
        turnAction.makeTurn();

        turnAction = new TombstoneTurnAction(map);
        turnAction.makeTurn();

        System.out.println("Turn:" + countTurn + " " +
                "Predators:" + map.getAllPredators().size() + " " +
                "Herbivores:" + map.getAllHerbivores().size() + " ");
        renderer.render();

        if (!map.isTypeOfEntityExist(Herbivore.class)) {
            System.out.println("Predators WIN!");
            isGameOver = true;
        } else if (!map.isTypeOfEntityExist(Predator.class)) {
            System.out.println("Herbivores WIN!");
            isGameOver = true;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
