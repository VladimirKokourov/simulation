package ru.vkokourov.action.turn;

import ru.vkokourov.entities.Alive;
import ru.vkokourov.map.Map;

public class AliveTurnAction extends TurnAction {

    public AliveTurnAction(Map map) {
        super(map);
    }

    @Override
    public void makeTurn() {
        for (Alive aliveEntity : map.getAliveEntities()) {
            aliveEntity.grow();
        }
    }
}
