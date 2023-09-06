package ru.vkokourov;

import ru.vkokourov.entities.Entity;
import ru.vkokourov.entities.creature.Creature;

import java.util.*;
import java.util.stream.Collectors;

public class Map {

    private final int weight;
    private final int height;
    private final HashMap<Coordinates, Entity> entities;
    private final Random random;

    public Map(int weight, int height) {
        this.weight = weight;
        this.height = height;
        entities = new HashMap<>();
        random = new Random();
    }

    public void addEntity(Entity entity) {
        Coordinates coordinates = entity.getCoordinates();
        if (entities.containsKey(coordinates)) {
            entities.replace(coordinates, entity);
        } else {
            entities.put(coordinates, entity);
        }
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates, getEntity(coordinates));
    }

    public List<Coordinates> getNeighbours(Coordinates coordinates) {
        List<Coordinates> neighbours = new ArrayList<>();
        int x = coordinates.x();
        int y = coordinates.y();
        neighbours.add(new Coordinates(x, y + 1));
        neighbours.add(new Coordinates(x + 1, y));
        neighbours.add(new Coordinates(x, y - 1));
        neighbours.add(new Coordinates(x - 1, y));

        return neighbours.stream().filter(this::isCoordinatesExist).toList();
    }

    public boolean isCoordinatesExist(Coordinates coordinates) {
        int x = coordinates.x();
        int y = coordinates.y();
        return x > 0 && x <= weight && y > 0 && y <= height;
    }

    public boolean isEmptySquare(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public boolean isTypeOfEntityExist(Class<? extends Entity> entityClass) {
        return entities.values().stream()
                .anyMatch(entity -> entity.getClass() == entityClass);
    }

    public Stack<Coordinates> findPath(Coordinates startCoordinates, Class<? extends Entity> goal) {
        Stack<Coordinates> path = new Stack<>();
        LinkedList<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> checked = new HashSet<>();
        HashMap<Coordinates, Coordinates> parents = new HashMap<>();
        Coordinates goalCoordinates = null;
        // breadth first search
        queue.add(startCoordinates);
        checked.add(startCoordinates);
        while (!queue.isEmpty()) {
            Coordinates current = queue.removeLast();
            if (isTypeOfEntityOnTheCoordinates(goal, current)) {
                goalCoordinates = current;
                break;
            }
            for (Coordinates neighbour : getNeighbours(current)) {
                if (!checked.contains(neighbour)
                        &&
                        (isEmptySquare(neighbour) || isTypeOfEntityOnTheCoordinates(goal, neighbour))) {
                    checked.add(neighbour);
                    parents.put(neighbour, current);
                    queue.addFirst(neighbour);
                }
            }
        }
        // impossible to come to the goal
        if (goalCoordinates == null) {
            return path;
        }
        // build path
        Coordinates child = goalCoordinates;
        Coordinates parent = parents.get(child);
        while (parent != startCoordinates) {
            path.push(parent);
            child = parent;
            parent = parents.get(child);
        }

        return path;
    }

    public boolean isTypeOfEntityOnTheCoordinates(Class<? extends Entity> entity, Coordinates coordinates) {
        if (getEntity(coordinates) == null) {
            return false;
        }
        return getEntity(coordinates).getClass().getSimpleName().equals(entity.getSimpleName());
    }

    public Coordinates getCoordinatesRandomEmptySquare() {
        int x;
        int y;
        do {
            x = random.nextInt(weight + 1);
            y = random.nextInt(height + 1);
        } while (!isEmptySquare(new Coordinates(x, y)));
        return new Coordinates(x, y);
    }

    public List<Creature> getAllCreatures() {
        List<Creature> creatures = (entities.values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(Entity::getCreature)
                .toList());
        return creatures.stream().map(Entity::getCreature).collect(Collectors.toList());
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }
}
