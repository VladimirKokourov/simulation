package ru.vkokourov.action.create;

import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.map.Map;

public class HerbivoreCreateAction extends CreateAction {

    public HerbivoreCreateAction(Map map, int amount) {
        super(map, amount);
    }

    @Override
    public void create() {
        for (int i = 0; i < amount; i++) {
            new Herbivore(map, map.getCoordinatesRandomEmptySquare());
        }
    }
}
