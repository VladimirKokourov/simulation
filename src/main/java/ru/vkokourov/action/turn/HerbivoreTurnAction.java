package ru.vkokourov.action.turn;

import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.map.Map;

public class HerbivoreTurnAction extends TurnAction {

    public HerbivoreTurnAction(Map map) {
        super(map);
    }

    @Override
    public void makeTurn() {
        for (Herbivore herbivore : map.getAllHerbivores()) {
            herbivore.makeMove();
        }
    }
}
