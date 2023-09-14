package ru.vkokourov.action.create;

import ru.vkokourov.entities.plant.Grass;
import ru.vkokourov.map.Map;

public class GrassCreateAction extends CreateAction {

    public GrassCreateAction(Map map) {
        super(map);
    }

    @Override
    public void create(int amount) {
        for (int i = 0; i < amount; i++) {
            new Grass(map, map.getCoordinatesRandomEmptySquare());
        }
    }
}
