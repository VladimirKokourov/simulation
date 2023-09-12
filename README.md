# Simulation - консольная игра наблюдения за существами.
Учебный проект в рамках курса от Сергея Жукова https://zhukovsd.github.io/java-backend-learning-course/

### Правила игры
Для отображения были выбраны иероглифы из-за одинаковой длины символов и отсутсвия проблем с кодировкой.
Хищники 乂, Травоядные 丅, Камни 圞, Деревья 孕, Трава 灬, Могилы 土, Пустота 囗
 
1. Хищники едят травоядных, травоядные едят траву.
2. Хищники делают 3 хода за раз, травоядные - 2.
3. Когда существа сытые и достаточно взрослые, они стремятся размножаться.
4. Деревья производят траву вокруг себя. Если траву никто не ест, со временем она становится деревом.
5. После смерти существ появляется могила, через один ход она превращается в траву.
6. Игра продолжается, пока один из видов не вымрет.

### Цель проекта
Самостоятельно реализовать логику игры в ООП стиле с использованием алгоритма поиска в ширину.

### Стэк
Java 17