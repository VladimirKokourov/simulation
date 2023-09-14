package ru.vkokourov.action.create;

import ru.vkokourov.entities.plant.Tree;
import ru.vkokourov.map.Map;

public class TreeCreateAction extends CreateAction {

    public TreeCreateAction(Map map, int amount) {
        super(map, amount);
    }

    @Override
    public void create() {
        for (int i = 0; i < amount; i++) {
            new Tree(map, map.getCoordinatesRandomEmptySquare());
        }
    }
}
