# Algos

Слабоумие, отвага, литкод.

## 📁 Структура проекта

Каждый подпроект имеет одинаковую структуру: `<модуль>/src/main/java/by/semenyukna/<пакет>/`, зеркальный `src/test/java/...`, `README.MD` и `build.gradle.kts`.

```
algos/
├── word-ladder/                          # BFS, кратчайшая цепочка преобразований
├── serialize-binary-tree/                # Сериализация/десериализация бинарного дерева
├── population-next-right-pointers-in-node/ # next-указатели в совершенном дереве
├── pair-nodes-swap/                      # Попарная перестановка узлов связного списка
├── next-permutation/                     # Следующая лексикографическая перестановка
├── previous-permutation/                 # Предыдущая лексикографическая перестановка
├── remove-duplicates-from-sorted-array2/ # Удаление дубликатов с максимум 2 вхождениями
├── longest-consecutive-sequence/         # Самая длинная последовательность чисел
├── coin-change/                          # Размен монет (BFS, не оптимально)
├── container-with-most-water/            # Контейнер с максимальной площадью
├── reverse-linked-list/                  # Разворот связного списка
├── reorder-list/                         # Перестройка связного списка
├── clone-graph/                          # Клонирование графа
├── add-two-numbers/                      # Сумма двух чисел (связные списки)
├── longest-array-without-repeating-number/ # Подмассив без повторений (Sliding Window)
├── longest-substring-without-repeating-characters/ # Подстрока без повторов (Sliding Window)
├── longest-repeating-character-replacement/ # K замен в окне (Sliding Window)
├── two-sum2/                             # Two Sum II (Two Pointers)
├── find-minimum-in-rotated-sorted-array/ # Минимум в повёрнутом массиве (Binary Search)
├── koko-eating-bananas/                  # Минимальная скорость поедания (Binary Search по ответу)
├── split-array-largest-sum/              # Разбиение на k отрезков с минимальным максимумом (Binary Search по ответу)
├── combinations/                         # Комбинации из k чисел (Backtracking)
├── permutations/                         # Все перестановки (Backtracking)
├── warmer-day/                           # Daily Temperatures (Monotonic Stack)
├── longest-increasing-subsequence/       # НВП (DP)
├── binary-tree-maximum-path-sum/         # Максимальная сумма пути (Tree DFS)
├── kth-largest-element-in-an-array/      # K-й по величине (Min-heap)
├── top-k-frequent-elements/              # K самых частых элементов (Min-heap по частоте)
├── number-of-islands/                    # Количество островов (DFS с заливкой)
├── max-area-of-island/                   # Площадь максимального острова (DFS с заливкой)
├── rotting-oranges/                      # Гниющие апельсины (BFS по уровням, multi-source)
├── zero-one-matrix/                      # Расстояние до ближайшего нуля (multi-source BFS)
├── course-schedule/                      # Цикл в графе зависимостей (DFS с тремя состояниями)
├── course-schedule-ii/                   # Топологический порядок курсов (DFS, post-order)
├── pacific-atlantic-water-flow/          # Два океана (BFS от границ)
├── redundant-connection/                 # Лишнее ребро в графе (Union-Find)
├── number-of-provinces/                  # Число провинций (Union-Find + счётчик компонент)
├── surrounded-regions/                   # Захват областей (BFS от краёв)
├── is-graph-bipartite/                   # Двудольность (раскраска в два цвета, DFS)
├── shortest-path-in-binary-matrix/       # Кратчайший путь в матрице (BFS, 8 направлений)
├── house-robber/                         # Ограбление домов (1D DP, O(1) памяти)
├── house-robber-ii/                      # Ограбление домов в круге (1D DP)
├── climbing-stairs/                      # Число способов подняться (Фибоначчи, O(1) памяти)
├── diameter-of-binary-tree/              # Диаметр дерева (post-order DFS)
├── 3sum/                                 # Тройки с нулевой суммой (сортировка + two pointers)
├── next-greater-element-i/               # Следующий больший элемент (монотонный стек + HashMap)
├── validate-binary-search-tree/          # Проверка BST (DFS с диапазоном значений)
├── kth-smallest-element-in-a-bst/        # K-й наименьший в BST (in-order с ранним выходом)
├── lowest-common-ancestor-of-a-binary-tree/  # LCA (post-order, ответ возвращается наверх)
├── network-delay-time/                   # Задержка сети (Дейкстра, PriorityQueue)
├── min-cost-to-connect-all-points/       # MST: Краскал (Union-Find) + Прим (PriorityQueue)
├── cheapest-flights-within-k-stops/      # Bellman-Ford + DFS с мемоизацией
├── binary-tree-level-order-traversal/    # BFS по уровням (фиксация размера очереди)
├── build.gradle.kts                      # Общий конфигурационный файл Gradle
├── settings.gradle.kts                   # Настройки проекта
└── gradlew                               # Gradle wrapper
```

## 🛠 Требования

- **JDK 25** (настроено через Java Toolchain)
- **Gradle 9.x** (включён через wrapper)

## 📦 Сборка и тесты

```bash
# Сборка проекта
./gradlew build

# Запуск всех тестов
./gradlew test

# Запуск конкретного теста
./gradlew :word-ladder:test --tests "SolutionTest"
```

## 🧪 Тестирование

Проект использует **JUnit 5 (Jupiter)** для модульного тестирования. Тесты располагаются в `src/test/java` с зеркальной структурой пакетов.

Пример теста:
```java
@Test
void solveCheck0() {
  var result = WordLadder.ladderLength("hit","cog", List.of("hot","dot","dog","lot","log","cog"));
  var expected = 5;
  assertEquals(expected, result);
}
```

## 📚 Технологии

| Компонент | Версия |
|-----------|--------|
| Язык | Java 25 |
| Сборка | Gradle (Kotlin DSL) |
| Тестирование | JUnit 5 (Jupiter) |

