package by.semenyukna.wl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  // ---------- примеры с LeetCode ----------

  @Test
  void testExample1() {
    // hit -> hot -> dot -> dog -> cog = 5 слов
    assertEquals(5, solution.ladderLength("hit", "cog",
        List.of("hot", "dot", "dog", "lot", "log", "cog")));
  }

  @Test
  void testExample2EndWordMissing() {
    // endWord "cog" нет в списке -> 0
    assertEquals(0, solution.ladderLength("hit", "cog",
        List.of("hot", "dot", "dog", "lot", "log")));
  }

  // ---------- границы ----------

  @Test
  void testBeginEqualsEnd() {
    // По условию LeetCode beginWord != endWord, но проверяем устойчивость.
    assertEquals(0, solution.ladderLength("hit", "hit",
        List.of("hot", "dot")));
  }

  @Test
  void testEmptyWordList() {
    assertEquals(0, solution.ladderLength("hit", "cog", List.of()));
  }

  @Test
  void testDirectTransformation() {
    // hot -> dot: одно преобразование = 2 слова
    assertEquals(2, solution.ladderLength("hot", "dot", List.of("dot")));
  }

  @Test
  void testNoPathAtAll() {
    // Слова не связаны: hit -> xxx невозможно
    assertEquals(0, solution.ladderLength("hit", "cog",
        List.of("xxx", "yyy", "cog")));
  }

  @Test
  void testBeginNotInWordList() {
    // beginWord не обязан быть в списке, но должен быть «мостом».
    // hit -> hot -> dot
    assertEquals(3, solution.ladderLength("hit", "dot", List.of("hot", "dot")));
  }

  @Test
  void testBeginInWordList() {
    // beginWord есть в списке — не должно ломать.
    assertEquals(3, solution.ladderLength("hit", "dot", List.of("hit", "hot", "dot")));
  }

  @Test
  void testEndWordIsOneChangeAway() {
    // a и c различаются в одной букве, c в списке -> 2 слова.
    assertEquals(2, solution.ladderLength("a", "c", List.of("c")));
  }

  @Test
  void testSingleCharacterWords() {
    // a и c различаются в одной букве -> одно преобразование -> 2 слова.
    assertEquals(2, solution.ladderLength("a", "c", List.of("b", "c")));
  }

  @Test
  void testSingleCharacterNeedsTwoSteps() {
    // 'c' не в списке, только 'b': a -> b, а до 'c' не дойти -> 0.
    assertEquals(0, solution.ladderLength("a", "c", List.of("b")));
  }

  // ---------- структура путей ----------

  @Test
  void testChainOfLengthN() {
    // aaa -> aab -> abb -> bbb = 4 слова
    assertEquals(4, solution.ladderLength("aaa", "bbb",
        List.of("aab", "abb", "bbb")));
  }

  @Test
  void testShortcutIsChosen() {
    // hit -> hot -> dot -> cog?? Нет: dot и cog различаются в трёх буквах.
    // Единственный путь: hit -> hot -> dot -> dog -> cog = 5 слов,
    // но cot в списке даёт более короткий: hit -> hot -> cot -> cog = 4.
    assertEquals(4, solution.ladderLength("hit", "cog",
        List.of("cot", "cog", "hot", "dot", "dog")));
  }

  @Test
  void testMultiplePathsTakesShortest() {
    // Пути: abc -> xbc -> xyc -> xyz (4 слова)
    //        abc -> axc -> ayc -> ayz -> xyz (5 слов)
    // BFS должен найти короткий.
    assertEquals(4, solution.ladderLength("abc", "xyz",
        List.of("xbc", "xyc", "xyz", "axc", "ayc", "ayz")));
  }

  @Test
  void testLongChain() {
    // aaa -> aab -> abb -> cbb -> ccc невозможно: abb и cbb (1), cbb и ccc (1).
    // Но abb -> bbc различаются в двух буквах, путь рвётся -> 0.
    assertEquals(0, solution.ladderLength("aaa", "ccc",
        List.of("aab", "abb", "bbc", "ccc")));
  }

  @Test
  void testLongChainValid() {
    // aaa и ccc различаются в 3 позициях, минимум 4 слова:
    // aaa -> aac -> acc -> ccc
    assertEquals(4, solution.ladderLength("aaa", "ccc",
        List.of("aac", "acc", "ccc")));
  }

  @Test
  void testCycleInGraphDoesNotLoop() {
    // hot -> dot -> dog = 3 слова. Цикл hot->dot->lot->hot не зацикливает BFS.
    assertEquals(3, solution.ladderLength("hot", "dog",
        List.of("dot", "lot", "hot", "dog")));
  }

  @Test
  void testAllWordsSameLength() {
    // abcde и abdde различаются в ОДНОЙ букве (позиция 2: c->d) -> 2 слова.
    assertEquals(2, solution.ladderLength("abcde", "abdde",
        List.of("abdde", "abxde")));
  }

  // ---------- сверка с независимым эталоном ----------

  @Test
  void testAgainstBruteForceReference() {
    // Эталон: BFS на ЯВНО построенном графе (сравнение всех пар, O(n^2 * L)).
    // На маленьких входах это допустимо и независимо от решения.
    Random rnd = new Random(20260101);
    for (int iter = 0; iter < 300; iter++) {
      int L = rnd.nextInt(3) + 2;                    // 2..4
      int n = rnd.nextInt(8) + 1;                    // 1..8 слов
      Set<String> words = new LinkedHashSet<>();
      while (words.size() < n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < L; i++) {
          sb.append((char) ('a' + rnd.nextInt(3)));   // только 'a','b','c' — плотный граф
        }
        words.add(sb.toString());
      }
      List<String> wordList = List.copyOf(words);

      StringBuilder b = new StringBuilder();
      StringBuilder e = new StringBuilder();
      for (int i = 0; i < L; i++) {
        b.append((char) ('a' + rnd.nextInt(3)));
        e.append((char) ('a' + rnd.nextInt(3)));
      }
      String begin = b.toString();
      String end = e.toString();
      if (begin.equals(end)) {
        continue;
      }

      assertEquals(
          reference(begin, end, wordList),
          solution.ladderLength(begin, end, wordList),
          "begin=" + begin + " end=" + end + " words=" + wordList);
    }
  }

  /** Эталон: явный граф + BFS. Независимая реализация. */
  private int reference(String begin, String end, List<String> wordList) {
    Set<String> set = new HashSet<>(wordList);
    if (!set.contains(end)) {
      return 0;
    }
    Set<String> all = new HashSet<>(wordList);
    all.add(begin);

    // BFS по уровню «слова»
    Set<String> visited = new HashSet<>();
    List<String> frontier = new java.util.ArrayList<>();
    frontier.add(begin);
    visited.add(begin);
    int level = 1;

    while (!frontier.isEmpty()) {
      List<String> next = new java.util.ArrayList<>();
      for (String cur : frontier) {
        if (cur.equals(end)) {
          return level;
        }
        for (String cand : all) {
          if (!visited.contains(cand) && diffByOne(cur, cand)) {
            visited.add(cand);
            next.add(cand);
          }
        }
      }
      frontier = next;
      level++;
    }
    return 0;
  }

  private boolean diffByOne(String a, String b) {
    if (a.length() != b.length()) {
      return false;
    }
    int d = 0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) != b.charAt(i) && ++d > 1) {
        return false;
      }
    }
    return d == 1;
  }
}
