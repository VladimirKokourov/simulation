package ru.vkokourov;

import ru.vkokourov.simulation.Simulation;

import java.util.Scanner;

public class GameLauncher {

    public static final String ONE_OR_TWO_REGEX = "[12]";
    public static final String TWO = "2";

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в симуляцию.");
        System.out.println("Хищники 乂, Травоядные 丅, Камни 圞, Деревья 孕, Трава 灬, Могилы 土, Пустота 囗");
        System.out.println("Правила игры:");
        System.out.println("1. Хищники едят травоядных, травоядные едят траву.");
        System.out.println("2. Хищники делают 3 хода за раз, травоядные - 2.");
        System.out.println("3. Когда существа сытые и достаточно взрослые, они стремятся размножаться.");
        System.out.println("4. Деревья производят траву вокруг себя. Если траву никто не ест, со временем она становится деревом.");
        System.out.println("5. После смерти существ появляется могила, через один ход она превращается в траву.");
        System.out.println("6. Игра продолжается, пока один из видов не вымрет.");

        String enter;

        enter = getEnter(scanner);

        if (enter.equals(TWO)) {
            return;
        }

        Simulation simulation = new Simulation();
        while (!enter.equals(TWO)) {
            simulation.init();
            while (!simulation.isGameOver()) {
                Thread.sleep(500);
                simulation.makeTurn();
            }
            System.out.println("Хотите сыграть еще?");
            enter = getEnter(scanner);
        }

    }

    private static String getEnter(Scanner scanner) {
        String result;
        do {
            System.out.println("Введите (1) для старта, (2) для выхода:");
            result = scanner.next();
            if (!result.matches(ONE_OR_TWO_REGEX)) {
                System.out.println("Некорректный ввод.");
            } else {
                break;
            }
        } while (true);
        return result;
    }
}
