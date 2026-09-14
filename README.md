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
├── two-sum2/                             # Two Sum II (Two Pointers)
├── find-minimum-in-rotated-sorted-array/ # Минимум в повёрнутом массиве (Binary Search)
├── combinations/                         # Комбинации из k чисел (Backtracking)
├── warmer-day/                           # Daily Temperatures (Monotonic Stack)
├── longest-increasing-subsequence/       # НВП (DP)
├── binary-tree-maximum-path-sum/         # Максимальная сумма пути (Tree DFS)
├── kth-largest-element-in-an-array/      # K-й по величине (Min-heap)
├── number-of-islands/                    # Количество островов (DFS с заливкой)
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
./gradlew :word-ladder:test --tests "WordLadderTest"
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

## Структура кода

- **Пакеты:**
  - `by.semenyukna.wl` — Word Ladder
  - `by.semenyukna.sbt` — Serialize Binary Tree
  - `by.semenyukna.rp` — Population Next Right Pointers
  - `by.semenyukna.pns` — Pair Nodes Swap
  - `by.semenyukna.np` — Next Permutation
  - `by.semenyukna.pp` — Previous Permutation
  - `by.semenyukna.rd` — Remove Duplicates From Sorted Array 2
  - `by.semenyukna.lcs` — Longest Consecutive Sequence
  - `by.semenyukna.cc` — Coin Change (BFS, не оптимально)
  - `by.semenyukna.cwmw` — Container With Most Water
  - `by.semenyukna.rll` — Reverse Linked List
  - `by.semenyukna.rl` — Reorder List
  - `by.semenyukna.cg` — Clone Graph
  - `by.semenyukna.atn` — Add Two Numbers
  - `by.semenyukna.ts2` — Two Sum II
  - `by.semenyukna.lawrn` — Longest Array Without Repeating Number
  - `by.semenyukna.fmrsa` — Find Minimum in Rotated Sorted Array
  - `by.semenyukna.combinations` — Combinations
  - `by.semenyukna.wd` — Warmer Day (Daily Temperatures)
  - `by.semenyukna.lls` — Longest Increasing Subsequence
  - `by.semenyukna.btmps` — Binary Tree Maximum Path Sum
  - `by.semenyukna.kleia` — Kth Largest Element in an Array
  - `by.semenyukna.noi` — Number of Islands
