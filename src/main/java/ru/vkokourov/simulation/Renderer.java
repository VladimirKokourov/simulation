package ru.vkokourov.simulation;

import ru.vkokourov.entities.Entity;
import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.map.Coordinates;
import ru.vkokourov.map.Map;

import java.util.List;

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
            sb.append(" | ");
            appendPredators(y, sb);
            sb.append(" | ");
            appendHerbivores(y, sb);
            System.out.println(sb);
        }
        System.out.println();
    }

    private void appendPredators(int y, StringBuilder sb) {
        List<Predator> allPredators = map.getAllPredators();
        if (map.getHeight() - y < allPredators.size()) {
            sb.append(allPredators.get(map.getHeight() - y));
        } else {
            sb.append("                                      ");
        }
    }

    private void appendHerbivores(int y, StringBuilder sb) {
        List<Herbivore> allHerbivores = map.getAllHerbivores();
        if (map.getHeight() - y < allHerbivores.size()) {
            sb.append(allHerbivores.get(map.getHeight() - y));
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
