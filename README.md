# Algos

Проект для решения алгоритмических задач на Java.

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

   
## 🔗 Лицензия

Образовательный проект.
