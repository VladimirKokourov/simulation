package ru.vkokourov.action.turn;

import ru.vkokourov.action.Action;
import ru.vkokourov.map.Map;

public abstract class TurnAction extends Action {

    public TurnAction(Map map) {
        super(map);
    }

    public abstract void makeTurn();
}
