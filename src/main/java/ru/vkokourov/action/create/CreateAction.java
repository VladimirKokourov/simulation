package ru.vkokourov.action.create;

import ru.vkokourov.action.Action;
import ru.vkokourov.map.Map;

public abstract class CreateAction extends Action {

    public CreateAction(Map map) {
        super(map);
    }

    public abstract void create(int amount);
}
