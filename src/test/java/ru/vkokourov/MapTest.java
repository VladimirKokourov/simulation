package ru.vkokourov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vkokourov.entities.other.Rock;
import ru.vkokourov.entities.creature.Creature;
import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.entities.plant.Grass;
import ru.vkokourov.map.Coordinates;
import ru.vkokourov.map.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private Herbivore herbivore;
    private Map map;
    private Grass grass;
    private Predator predator;

    @BeforeEach
    void setUp() {
        map = new Map(10, 10);
        grass = new Grass(map, new Coordinates(3, 3));
        Rock rock = new Rock(map, new Coordinates(2, 2));
        herbivore = new Herbivore(map, new Coordinates(1, 1));
        predator = new Predator(map, new Coordinates(2, 1));
    }

    @Test
    void isTypeOfEntityOnTheCoordinates() {
        assertTrue(map.isTypeOfEntityOnTheCoordinates(Herbivore.class, new Coordinates(1, 1)));
        assertFalse(map.isTypeOfEntityOnTheCoordinates(Grass.class, new Coordinates(1, 2)));
        assertFalse(map.isTypeOfEntityOnTheCoordinates(Grass.class, new Coordinates(1, 3)));
    }

    @Test
    void isTypeOfEntityExist() {
        assertTrue(map.isTypeOfEntityExist(Grass.class));
        assertTrue(map.isTypeOfEntityExist(Rock.class));
        assertFalse(map.isTypeOfEntityExist(Predator.class));
    }

    @Test
    void findPath() {
        Stack<Coordinates> expected = new Stack<>();
        expected.push(new Coordinates(2, 3));
        expected.push(new Coordinates(1, 3));
        expected.push(new Coordinates(1, 2));
        assertIterableEquals(expected, map.findPath(herbivore.getCoordinates(), Grass.class));
    }

    @Test
    void findEmptyPath() {
        Stack<Coordinates> expected = new Stack<>();
        assertIterableEquals(expected, map.findPath(predator.getCoordinates(), Herbivore.class));
    }

    @Test
    void getAllCreatures() {
        List<Creature> expected = new ArrayList<>();
        Predator predator = new Predator(map, new Coordinates(1,3));
        expected.add(herbivore);
        expected.add(predator);
        assertIterableEquals(expected, map.getAllPredators());
    }

}