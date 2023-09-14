package ru.vkokourov.action.create;

import ru.vkokourov.action.Action;
import ru.vkokourov.map.Map;

public abstract class CreateAction extends Action {

    protected int amount;

    public CreateAction(Map map, int amount) {
        super(map);
        this.amount = amount;
    }

    public abstract void create();
}
