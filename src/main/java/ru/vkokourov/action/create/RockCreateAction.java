package ru.vkokourov.action.create;

import ru.vkokourov.entities.other.Rock;
import ru.vkokourov.map.Map;

public class RockCreateAction extends CreateAction {

    public RockCreateAction(Map map, int amount) {
        super(map, amount);
    }

    @Override
    public void create() {
        for (int i = 0; i < amount; i++) {
            new Rock(map, map.getCoordinatesRandomEmptySquare());
        }
    }
}
