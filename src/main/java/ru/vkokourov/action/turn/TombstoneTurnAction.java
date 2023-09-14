package ru.vkokourov.action.turn;

import ru.vkokourov.entities.other.Tombstone;
import ru.vkokourov.map.Map;

public class TombstoneTurnAction extends TurnAction {

    public TombstoneTurnAction(Map map) {
        super(map);
    }

    @Override
    public void makeTurn() {
        for (Tombstone tombstone : map.getAllTombstones()) {
            tombstone.destroy();
        }
    }
}
