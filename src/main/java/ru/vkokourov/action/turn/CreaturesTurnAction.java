package ru.vkokourov.action.turn;

import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.map.Map;

public class CreaturesTurnAction extends TurnAction {

    public CreaturesTurnAction(Map map) {
        super(map);
    }

    @Override
    public void makeTurn() {
        for (Herbivore herbivore : map.getAllHerbivores()) {
            herbivore.makeMove();
        }
        for (Predator predator : map.getAllPredators()) {
            predator.makeMove();
        }
    }
}
