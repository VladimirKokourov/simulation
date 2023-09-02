package ru.vkokourov;

public class Simulation {

    private final Renderer renderer;
    private int countTurn;

    public Simulation(Renderer renderer) {
        this.renderer = renderer;
        countTurn = 0;
    }

    public void init() {
        renderer.render();
    }
}
