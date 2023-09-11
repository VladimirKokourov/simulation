package ru.vkokourov.entities.creature;

import ru.vkokourov.map.Coordinates;
import ru.vkokourov.map.Map;

public class Predator extends Creature {

    public Predator(Map map, Coordinates coordinates) {
        super(map, coordinates);
        hunger = 5;
        speed = 3;
        amountOfMoves = speed;
        maxHunger = 25;
        satiety = 9;
        maxAge = 25;
        reproduceAge = 6;
        reproduceHunger = 8;
        food = Herbivore.class;
    }

    @Override
    public void reproduce() {
        Coordinates partnerCoordinates = map.getNeighbours(this.coordinates)
                .stream()
                .filter(c -> map.isTypeOfEntityOnTheCoordinates(Predator.class, c))
                .findFirst()
                .orElse(null);
        if (partnerCoordinates == null) {
            return;
        }

        Predator partner = (Predator) map.getEntity(partnerCoordinates);
        if (partner.isReadyToReproduce()) {
            map.getNeighbours(this.coordinates).stream()
                    .filter(map::isEmptySquare)
                    .findFirst()
                    .ifPresent(reproduceCoordinates -> new Predator(map, reproduceCoordinates));
            hunger += speed;
            partner.hunger += speed * 2;
        }
    }
}
