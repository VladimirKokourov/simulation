package ru.vkokourov.action.create;

import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.map.Map;

public class PredatorCreateAction extends CreateAction {

    public PredatorCreateAction(Map map) {
        super(map);
    }

    @Override
    public void create(int amount) {
        for (int i = 0; i < amount; i++) {
            new Predator(map, map.getCoordinatesRandomEmptySquare());
        }
    }
}
