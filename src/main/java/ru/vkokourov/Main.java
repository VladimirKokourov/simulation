package ru.vkokourov;

/*
 * идеи:
 * 1. Режимы игры: простой(рандомный), настраиваемый, режим Бога(опционально)
 * 2. Карта: мал, средняя, большая. Камни 圞, деревья 孕, трава 灬, пустота 囗
 * 3. Существа:
 *   Хищники 乂 - нужно много есть, чтобы размножаться, маленькое потомство, долго живут (едят друг друга при голодании)
 *   Травоядные 丅 - низкий пород голода для размножения, большое потомство, быстро стареют
 *   Деревья 孕 - медленно размножаются; производят вокруг себя траву, которая превращается в дерево, если ее не
 *       успели съесть.
 *   Могилы 土 - остаются после смерти существ, после превращаются в траву.
 * 4. Характеристики:
 *   Текущий возраст
 *   Максимальный возраст
 *
 *   Скорость - количество клеток за ход (увел./умен. с возрастом)
 *   НР - увеличивается с возрастом
 *   Голод - увеличивается с возрастом
 *   Готовность к размножению
 *   Приоритет - убегать от хищника, еда, партнер.
 *   Пол (опционально)
 * 5. Конец игры: один из вида существ вымер.
 * 6. Цель игры: подобрать баланс, набрать большее количество ходов.
 *
 * Вид карты (выбрал иероглифы из-за одинаковой длины символов, нет проблем с кодировкой)
 * */

import ru.vkokourov.entities.*;
import ru.vkokourov.entities.creature.Herbivore;
import ru.vkokourov.entities.creature.Predator;
import ru.vkokourov.entities.plant.Grass;
import ru.vkokourov.entities.plant.Tree;

public class Main {
    public static void main(String[] args) {

        Map map = new Map(10,10);
        map.addEntity(new Grass(map, new Coordinates(1,1)));
        map.addEntity(new Herbivore(map, new Coordinates(2,2)));
        map.addEntity(new Predator(map, new Coordinates(3,4)));
        map.addEntity(new Rock(map, new Coordinates(5,4)));
        map.addEntity(new Tombstone(map, new Coordinates(7,7)));
        map.addEntity(new Tree(map, new Coordinates(8,1)));
        map.addEntity(new Tree(map, new Coordinates(1,9)));
        map.addEntity(new Tree(map, new Coordinates(5, 10)));
        map.addEntity(new Grass(map, new Coordinates(5, 10)));
        Renderer renderer = new Renderer(map);
        renderer.render();
    }
}
