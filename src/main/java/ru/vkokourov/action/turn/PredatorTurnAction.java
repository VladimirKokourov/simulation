package ru.vkokourov.action.turn;

import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.map.Map;

public class PredatorTurnAction extends TurnAction {

    public PredatorTurnAction(Map map) {
        super(map);
    }

    @Override
    public void makeTurn() {
        for (Predator predator : map.getAllPredators()) {
            predator.makeMove();
        }
    }
}
