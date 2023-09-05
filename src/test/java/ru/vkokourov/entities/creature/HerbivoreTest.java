package ru.vkokourov.entities.creature;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vkokourov.Coordinates;
import ru.vkokourov.Map;
import ru.vkokourov.entities.plant.Grass;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class HerbivoreTest {

    private Herbivore herbivore;
    private Map map;
    private Grass grass;

    @BeforeEach
    void setUp() {
        map = new Map(4,4);
        grass = new Grass(map, new Coordinates(3, 3));
        herbivore = new Herbivore(map, new Coordinates(1,1));
    }

    @Test
    void findPathToFood() {
        Stack<Coordinates> expected = new Stack<>();
        expected.push(new Coordinates(2, 3));
        expected.push(new Coordinates(1, 3));
        expected.push(new Coordinates(1, 2));
        assertIterableEquals(expected, herbivore.findPathToFood());
    }
}