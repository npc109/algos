# Algos

Слабоумие, отвага, литкод.

## 📁 Структура проекта

```
algos/
├── word-ladder/                        # Задача о кратчайшей цепочке преобразований (BFS)
│   ├── src/main/java/.../wl/
│   │   └── WordLadder.java
│   ├── src/test/java/.../wl/
│   │   └── WordLadderTest.java
│   └── build.gradle.kts
├── serialize-binary-tree/              # Сериализация/десериализация бинарного дерева
│   ├── src/main/java/.../sbt/
│   │   ├── TreeNode.java
│   │   └── Codec.java
│   ├── src/test/java/.../sbt/
│   │   └── CodecTest.java
│   └── build.gradle.kts
├── population-next-right-pointers-in-node/  # Заполнение next-указателей в совершенном дереве
│   ├── src/main/java/.../rp/
│   │   ├── Node.java
│   │   └── Solution.java
│   ├── src/test/java/.../rp/
│   │   └── SolutionTest.java
│   └── build.gradle.kts
├── pair-nodes-swap/                    # Попарная перестановка узлов связного списка
│   ├── src/main/java/.../pns/
│   │   ├── ListNode.java
│   │   └── Solution.java
│   ├── src/test/java/.../pns/
│   │   └── SolutionTest.java
│   └── build.gradle.kts
├── next-permutation/                   # Следующая лексикографическая перестановка
│   ├── src/main/java/.../np/
│   │   └── Solution.java
│   ├── src/test/java/.../np/
│   │   └── SolutionTest.java
│   └── build.gradle.kts
├── previous-permutation/               # Предыдущая лексикографическая перестановка
│   ├── src/main/java/.../pp/
│   │   └── Solution.java
│   ├── src/test/java/.../pp/
│   │   └── SolutionTest.java
│   └── build.gradle.kts
├── remove-duplicates-from-sorted-array2/ # Удаление дубликатов с максимум 2 вхождениями
│   ├── src/main/java/.../rd/
│   │   └── Solution.java
│   ├── src/test/java/.../rd/
│   │   └── SolutionTest.java
│   └── build.gradle.kts
├── longest-consecutive-sequence/       # Поиск самой длинной последовательности чисел
│   ├── src/main/java/.../lcs/
│   │   └── Solution.java
│   ├── src/test/java/.../lcs/
│   │   └── SolutionTest.java
│   └── build.gradle.kts
├── coin-change/                        # Размен монет: минимальное количество монет (BFS, не оптимально)
│   ├── src/main/java/.../cc/
│   │   └── Solution.java
│   ├── src/test/java/.../cc/
│   │   └── SolutionTest.java
│   └── build.gradle.kts
├── container-with-most-water/          # Контейнер с максимальной площадью (two pointers)
│   ├── src/main/java/.../cwmw/
│   │   └── Solution.java
│   ├── src/test/java/.../cwmw/
│   │   └── SolutionTest.java
│   └── build.gradle.kts
├── reverse-linked-list/                # Разворот связного списка (итеративно)
│   ├── src/main/java/.../rll/
│   │   ├── ListNode.java
│   │   └── Solution.java
│   ├── src/test/java/.../rll/
│   │   └── SolutionTest.java
│   └── build.gradle.kts
├── reorder-list/                       # Перестройка связного списка (L0→Ln→L1→Ln+1...)
│   ├── src/main/java/.../rl/
│   │   ├── ListNode.java
│   │   └── Solution.java
│   ├── src/test/java/.../rl/
│   │   └── SolutionTest.java
│   └── build.gradle.kts
├── clone-graph/                        # Клонирование неориентированного графа (DFS + HashMap)
│   ├── src/main/java/.../cg/
│   │   ├── Node.java
│   │   └── Solution.java
│   ├── src/test/java/.../cg/
│   │   └── SolutionTest.java
│   └── build.gradle.kts
├── build.gradle.kts                    # Общий конфигурационный файл Gradle
├── settings.gradle.kts                 # Настройки проекта
└── gradlew                             # Gradle wrapper
```

## 🛠 Требования

- **JDK 25** (настроено через Java Toolchain)
- **Gradle 9.x** (включён через wrapper)

## 📦 Сборка и запуск

```bash
# Сборка проекта
./gradlew build

# Запуск задачи
./gradlew :word-ladder:run

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

