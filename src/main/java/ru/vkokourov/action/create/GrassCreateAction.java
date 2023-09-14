package ru.vkokourov.action.create;

import ru.vkokourov.entities.plant.Grass;
import ru.vkokourov.map.Map;

public class GrassCreateAction extends CreateAction {

    public GrassCreateAction(Map map, int amount) {
        super(map, amount);
    }

    @Override
    public void create() {
        for (int i = 0; i < amount; i++) {
            new Grass(map, map.getCoordinatesRandomEmptySquare());
        }
    }
}
