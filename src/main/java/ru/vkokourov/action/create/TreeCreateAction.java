package ru.vkokourov.action.create;

import ru.vkokourov.entities.plant.Tree;
import ru.vkokourov.map.Map;

public class TreeCreateAction extends CreateAction {

    public TreeCreateAction(Map map) {
        super(map);
    }

    @Override
    public void create(int amount) {
        for (int i = 0; i < amount; i++) {
            new Tree(map, map.getCoordinatesRandomEmptySquare());
        }
    }
}
