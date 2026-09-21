package by.semenyukna.ngei;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertArrayEquals(
        new int[]{-1, 3, -1},
        solution.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}));
  }

  @Test
  void testExample2() {
    assertArrayEquals(
        new int[]{3, -1},
        solution.nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4}));
  }

  @Test
  void testExample3() {
    assertArrayEquals(
        new int[]{7, 7, 7, 7, 7},
        solution.nextGreaterElement(new int[]{1, 3, 5, 2, 4}, new int[]{6, 5, 4, 3, 2, 1, 7}));
  }

  @Test
  void testSingleElementWithNoGreater() {
    assertArrayEquals(
        new int[]{-1},
        solution.nextGreaterElement(new int[]{1}, new int[]{1}));
  }

  @Test
  void testSingleElementWithGreater() {
    assertArrayEquals(
        new int[]{2},
        solution.nextGreaterElement(new int[]{1}, new int[]{1, 2}));
  }

  @Test
  void testNums1EqualsNums2StrictlyIncreasing() {
    assertArrayEquals(
        new int[]{2, 3, 4, -1},
        solution.nextGreaterElement(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}));
  }

  @Test
  void testStrictlyDecreasingAllMinusOne() {
    // Ни у одного элемента нет большего справа.
    assertArrayEquals(
        new int[]{-1, -1, -1, -1},
        solution.nextGreaterElement(new int[]{5, 4, 3, 2}, new int[]{5, 4, 3, 2, 1}));
  }

  @Test
  void testLastElementIsAlwaysMinusOne() {
    // Последний элемент nums2 не имеет элементов справа — для него ответ -1.
    assertArrayEquals(
        new int[]{-1},
        solution.nextGreaterElement(new int[]{9}, new int[]{1, 9}));
  }

  @Test
  void testGreaterExistsButNotImmediately() {
    // Для 2 следующий больший — 100, хотя между ними 0 и 1.
    assertArrayEquals(
        new int[]{100},
        solution.nextGreaterElement(new int[]{2}, new int[]{2, 0, 1, 100}));
  }

  @Test
  void testSkipsSmallerElements() {
    // Для 2 следующий больший — 3; для 1 тоже 3: он первый правее и больше.
    assertArrayEquals(
        new int[]{3, 3},
        solution.nextGreaterElement(new int[]{2, 1}, new int[]{2, 1, 3, 5}));
  }

  @Test
  void testNums1IsSubsetInDifferentOrder() {
    // Порядок nums1 не влияет на порядок ответа: ответ идёт по nums1.
    assertArrayEquals(
        new int[]{-1, 2, 3},
        solution.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 2, 3, 4}));
  }

  @Test
  void testEmptyNums1() {
    assertArrayEquals(
        new int[]{},
        solution.nextGreaterElement(new int[]{}, new int[]{1, 2, 3}));
  }

  @Test
  void testSingleElementNums2NoGreater() {
    assertArrayEquals(
        new int[]{-1},
        solution.nextGreaterElement(new int[]{7}, new int[]{7}));
  }

  @Test
  void testNegativeValues() {
    // Каждый следующий элемент больше предыдущего: -3 -> -2, -2 -> -1, -1 -> 0.
    assertArrayEquals(
        new int[]{-2, -1, 0},
        solution.nextGreaterElement(new int[]{-3, -2, -1}, new int[]{-3, -2, -1, 0}));
  }

  @Test
  void testNegativeValuesStrictlyDecreasing() {
    assertArrayEquals(
        new int[]{-1, -1, -1},
        solution.nextGreaterElement(new int[]{-1, -2, -3}, new int[]{-1, -2, -3}));
  }

  @Test
  void testNegativeAndPositiveMixed() {
    // Для -5 следующий больший — 4, а не 5: 4 стоит раньше.
    assertArrayEquals(
        new int[]{4, 5, 2},
        solution.nextGreaterElement(new int[]{-5, 4, 1}, new int[]{-5, 4, 1, 2, 5}));
  }

  @Test
  void testZeroIsGreaterThanNegative() {
    assertArrayEquals(
        new int[]{0, 1},
        solution.nextGreaterElement(new int[]{-1, 0}, new int[]{-1, 0, 1}));
  }

  @Test
  void testInputsAreNotMutated() {
    int[] nums1 = {4, 1, 2};
    int[] nums2 = {1, 3, 4, 2};
    int[] nums1Copy = nums1.clone();
    int[] nums2Copy = nums2.clone();

    solution.nextGreaterElement(nums1, nums2);

    assertArrayEquals(nums1Copy, nums1, "nums1 must not be mutated");
    assertArrayEquals(nums2Copy, nums2, "nums2 must not be mutated");
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertArrayEquals(
        new int[]{-1, 3, -1},
        solution.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}));
    assertArrayEquals(
        new int[]{3, -1},
        solution.nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4}));
    assertArrayEquals(
        new int[]{-1, -1, -1, -1},
        solution.nextGreaterElement(new int[]{5, 4, 3, 2}, new int[]{5, 4, 3, 2, 1}));
  }

  @Test
  void testLargeWorstCaseDescendingThenAscending() {
    // Стек вырастает до n, затем всё вынимается по одному — проверка амортизации.
    int n = 1000;
    int[] nums2 = new int[n];
    for (int i = 0; i < n; i++) {
      nums2[i] = n - i;          // n, n-1, ..., 1 — строго по убыванию
    }
    int[] nums1 = nums2.clone();
    int[] expected = new int[n];
    for (int i = 0; i < n; i++) {
      expected[i] = -1;
    }
    assertArrayEquals(expected, solution.nextGreaterElement(nums1, nums2));
  }

  @Test
  void testLargeAscending() {
    // Каждый элемент получает следующего соседа — стек почти пуст всё время.
    int n = 1000;
    int[] nums2 = new int[n];
    for (int i = 0; i < n; i++) {
      nums2[i] = i;
    }
    int[] nums1 = nums2.clone();
    int[] expected = new int[n];
    for (int i = 0; i < n - 1; i++) {
      expected[i] = i + 1;
    }
    expected[n - 1] = -1;
    assertArrayEquals(expected, solution.nextGreaterElement(nums1, nums2));
  }

  @Test
  void testLargeZigzag() {
    // Чередование вверх-вниз: стек то растёт, то схлопывается.
    int n = 200;
    int[] nums2 = new int[n];
    for (int i = 0; i < n; i++) {
      nums2[i] = (i % 2 == 0) ? (n - i) : i;
    }
    int[] nums1 = {nums2[0], nums2[1], nums2[n - 1]};
    assertArrayEquals(solution.nextGreaterElement(nums1, nums2), brute(nums1, nums2));
  }

  @Test
  void testRandomAgainstBruteForce() {
    // Эталон: наивный O(n*m) поиск следующего большего.
    java.util.Random rnd = new java.util.Random(2024);
    for (int seed = 0; seed < 300; seed++) {
      int m = rnd.nextInt(30) + 1;
      int[] nums2 = distinctRandom(rnd, m);
      int n = rnd.nextInt(m) + 1;
      int[] nums1 = pickSubset(rnd, nums2, n);
      assertArrayEquals(
          solution.nextGreaterElement(nums1, nums2),
          brute(nums1, nums2),
          "mismatch on nums1=" + java.util.Arrays.toString(nums1)
              + " nums2=" + java.util.Arrays.toString(nums2));
    }
  }

  /** Массив длины m из различных значений в диапазоне [-50, 50]. */
  private int[] distinctRandom(java.util.Random rnd, int m) {
    java.util.LinkedHashSet<Integer> set = new java.util.LinkedHashSet<>();
    while (set.size() < m) {
      set.add(rnd.nextInt(101) - 50);
    }
    int[] out = new int[m];
    int i = 0;
    for (int v : set) {
      out[i++] = v;
    }
    return out;
  }

  /** Случайное подмножество nums2 размера n (элементы без повторов). */
  private int[] pickSubset(java.util.Random rnd, int[] nums2, int n) {
    java.util.List<Integer> pool = new java.util.ArrayList<>();
    for (int v : nums2) {
      pool.add(v);
    }
    java.util.Collections.shuffle(pool, rnd);
    int[] out = new int[n];
    for (int i = 0; i < n; i++) {
      out[i] = pool.get(i);
    }
    return out;
  }

  /** Наивное эталонное решение — независимая реализация условия. */
  private int[] brute(int[] nums1, int[] nums2) {
    int[] out = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
      out[i] = -1;
      int pos = -1;
      for (int j = 0; j < nums2.length; j++) {
        if (nums2[j] == nums1[i]) {
          pos = j;
          break;
        }
      }
      for (int j = pos + 1; j < nums2.length; j++) {
        if (nums2[j] > nums1[i]) {
          out[i] = nums2[j];
          break;
        }
      }
    }
    return out;
  }

  @Test
  void testBruteForceReferenceIsCorrect() {
    // Самопроверка эталона на вручную разобранных случаях: если «эталон» начнёт
    // врать, падение будет здесь, а не в тестах решения.
    assertArrayEquals(new int[]{-1, 3, -1}, brute(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}));
    assertArrayEquals(new int[]{3, -1}, brute(new int[]{2, 4}, new int[]{1, 2, 3, 4}));
    assertArrayEquals(new int[]{3, 3}, brute(new int[]{2, 1}, new int[]{2, 1, 3, 5}));
    assertArrayEquals(new int[]{-2, -1, 0}, brute(new int[]{-3, -2, -1}, new int[]{-3, -2, -1, 0}));
    assertArrayEquals(new int[]{4, 5, 2}, brute(new int[]{-5, 4, 1}, new int[]{-5, 4, 1, 2, 5}));
    assertArrayEquals(new int[]{100}, brute(new int[]{2}, new int[]{2, 0, 1, 100}));
    assertArrayEquals(new int[]{-1}, brute(new int[]{9}, new int[]{1, 9}));
  }

  @Test
  void testHardcodedExpectationsMatchReference() {
    // Ловит расхождение между руками написанным ожиданием и эталоном.
    assertArrayEquals(brute(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}), new int[]{-1, 3, -1});
    assertArrayEquals(brute(new int[]{2, 4}, new int[]{1, 2, 3, 4}), new int[]{3, -1});
    assertArrayEquals(brute(new int[]{2, 1}, new int[]{2, 1, 3, 5}), new int[]{3, 3});
    assertArrayEquals(brute(new int[]{-3, -2, -1}, new int[]{-3, -2, -1, 0}), new int[]{-2, -1, 0});
    assertArrayEquals(brute(new int[]{-5, 4, 1}, new int[]{-5, 4, 1, 2, 5}), new int[]{4, 5, 2});
    assertArrayEquals(brute(new int[]{5, 4, 3, 2}, new int[]{5, 4, 3, 2, 1}), new int[]{-1, -1, -1, -1});
    assertArrayEquals(brute(new int[]{1, 3, 5, 2, 4}, new int[]{6, 5, 4, 3, 2, 1, 7}),
        new int[]{7, 7, 7, 7, 7});
    assertArrayEquals(brute(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}), new int[]{2, 3, 4, -1});
    assertArrayEquals(brute(new int[]{4, 1, 2}, new int[]{1, 2, 3, 4}), new int[]{-1, 2, 3});
    assertArrayEquals(brute(new int[]{-1, 0}, new int[]{-1, 0, 1}), new int[]{0, 1});
  }
}
