package ru.vkokourov;

import ru.vkokourov.simulation.Simulation;

import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в симуляцию.");
        System.out.println("Хищники 乂, Травоядные 丅, Камни 圞, Деревья 孕, Трава 灬, Могилы 土, Пустота 囗");
        System.out.println("Дополнения: \n1. Когда существа сытые и достаточно взрослые, они стремятся размножаться.");
        System.out.println("2. После смерти остается могила, которая после 1 хода становится травой.");
        System.out.println("3. Деревья производят траву. Через какое-то время трава превращается в дерево.\n");

        String enter;
        do {
            System.out.println("Введите (1) для старта, (2) для выхода:");
            enter = scanner.next();
        } while (!enter.matches("[12]"));

        if (enter.equals("2")) {
            return;
        }

        Simulation simulation = new Simulation();
        simulation.init();
        while (!simulation.isGameOver()) {
            Thread.sleep(300);
            simulation.makeTurn();
        }
    }
}
