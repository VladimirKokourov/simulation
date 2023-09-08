package ru.vkokourov;

import ru.vkokourov.entities.Entity;

public class Renderer {

    public static final String SYMBOL_FOR_EMPTY_SQUARE = "囗";
    public static final String SYMBOL_FOR_ROCK = "圞";
    public static final String SYMBOL_FOR_TREE = "孕";
    public static final String SYMBOL_FOR_GRASS = "灬";
    public static final String SYMBOL_FOR_HERBIVORE = "丅";
    public static final String SYMBOL_FOR_PREDATOR = "乂";
    public static final String SYMBOL_FOR_TOMBSTONE = "土";

    private final Map map;

    public Renderer(Map map) {
        this.map = map;
    }

    public void render() {
        for (int y = map.getHeight(); y >= 1; y--) {
            StringBuilder sb = new StringBuilder();
            for (int x = 1; x <= map.getWeight(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (map.isEmptySquare(coordinates)) {
                    sb.append(getSpriteForEmptySquare());
                } else {
                    sb.append(selectSpriteForEntity(map.getEntity(coordinates)));
                }
            }
            System.out.println(sb);
        }
    }

    private String selectSpriteForEntity(Entity entity) {
        if (entity == null) {
            return SYMBOL_FOR_EMPTY_SQUARE;
        }
        return switch (entity.getClass().getSimpleName()) {
            case "Rock" -> SYMBOL_FOR_ROCK;
            case "Tree" -> SYMBOL_FOR_TREE;
            case "Grass" -> SYMBOL_FOR_GRASS;
            case "Herbivore" -> SYMBOL_FOR_HERBIVORE;
            case "Predator" -> SYMBOL_FOR_PREDATOR;
            case "Tombstone" -> SYMBOL_FOR_TOMBSTONE;
            default -> SYMBOL_FOR_EMPTY_SQUARE;
        };
    }

    private String getSpriteForEmptySquare() {
        return SYMBOL_FOR_EMPTY_SQUARE;
    }
}
