package ru.vkokourov.simulation;

import ru.vkokourov.action.create.*;
import ru.vkokourov.action.turn.*;
import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.map.Map;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<CreateAction> createActions = new ArrayList<>();
    private final List<TurnAction> turnActions = new ArrayList<>();

    private Renderer renderer;
    private Map map;
    private int countTurn;
    private boolean isGameOver;

    public Simulation() {
        addAllCreateActions();
        addAllTurnActions();
    }

    public void init() {
        map = new Map(30, 12);
        renderer = new Renderer(map);
        isGameOver = false;
        countTurn = 0;

        createActions.forEach(CreateAction::create);

        renderer.render();
    }

    public void makeTurn() {
        countTurn++;

        turnActions.forEach(TurnAction::makeTurn);

        renderer.printCountTurn(countTurn);
        renderer.render();

        if (!map.isTypeOfEntityExist(Herbivore.class)) {
            System.out.println("Predators WIN!");
            isGameOver = true;
        } else if (!map.isTypeOfEntityExist(Predator.class)) {
            System.out.println("Herbivores WIN!");
            isGameOver = true;
        }
    }

    private void addAllCreateActions() {
        createActions.add(new RockCreateAction(map, 12));
        createActions.add(new TreeCreateAction(map, 20));
        createActions.add(new GrassCreateAction(map, 25));
        createActions.add(new HerbivoreCreateAction(map, 12));
        createActions.add(new PredatorCreateAction(map, 2));
    }

    private void addAllTurnActions() {
        turnActions.add(new AliveTurnAction(map));
        turnActions.add(new HerbivoreTurnAction(map));
        turnActions.add(new PredatorTurnAction(map));
        turnActions.add(new TombstoneTurnAction(map));
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
