package ru.vkokourov.action;

import ru.vkokourov.map.Map;

public abstract class Action {

    protected final Map map;

    public Action(Map map) {
        this.map = map;
    }
}
