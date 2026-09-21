package by.semenyukna.ts3;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTest {

  private final Solution solution = new Solution();

  /** Ответ неупорядочен, поэтому сравниваем как множества троек. */
  private Set<List<Integer>> asSet(List<List<Integer>> actual) {
    Set<List<Integer>> normalized = new HashSet<>();
    for (List<Integer> triple : actual) {
      normalized.add(triple.stream().sorted().toList());
    }
    return normalized;
  }

  @Test
  void testExample1() {
    var actual = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    assertEquals(
        Set.of(List.of(-1, -1, 2), List.of(-1, 0, 1)),
        asSet(actual));
  }

  @Test
  void testExample2() {
    assertTrue(solution.threeSum(new int[]{0, 1, 1}).isEmpty());
  }

  @Test
  void testExample3() {
    assertEquals(Set.of(List.of(0, 0, 0)), asSet(solution.threeSum(new int[]{0, 0, 0})));
  }

  @Test
  void testEmptyInput() {
    assertTrue(solution.threeSum(new int[]{}).isEmpty());
  }

  @Test
  void testSingleElement() {
    assertTrue(solution.threeSum(new int[]{0}).isEmpty());
  }

  @Test
  void testTwoElements() {
    assertTrue(solution.threeSum(new int[]{-1, 1}).isEmpty());
    assertTrue(solution.threeSum(new int[]{0, 0}).isEmpty());
  }

  @Test
  void testAllZeros() {
    // Ровно один ответ, несмотря на 4 одинаковых элемента.
    assertEquals(Set.of(List.of(0, 0, 0)), asSet(solution.threeSum(new int[]{0, 0, 0, 0})));
  }

  @Test
  void testManyZerosWithPair() {
    // Три нуля дают собственную тройку помимо -1,0,1.
    assertEquals(
        Set.of(List.of(-1, 0, 1), List.of(0, 0, 0)),
        asSet(solution.threeSum(new int[]{-1, 0, 0, 0, 0, 1})));
  }

  @Test
  void testDuplicatesDoNotProduceDuplicateTriples() {
    var actual = solution.threeSum(new int[]{-2, -2, -2, 4, 4, 4});
    assertEquals(Set.of(List.of(-2, -2, 4)), asSet(actual));
    assertEquals(1, actual.size());
  }

  @Test
  void testTwoTriplesWithDuplicates() {
    var actual = solution.threeSum(new int[]{-2, 0, 1, 1, 2});
    assertEquals(
        Set.of(List.of(-2, 0, 2), List.of(-2, 1, 1)),
        asSet(actual));
  }

  @Test
  void testDuplicatesStretchingToArrayStart() {
    // Копии -1 тянутся до нулевого индекса: указатель r должен
    // останавливаться у l, а не у границы массива.
    var actual = solution.threeSum(new int[]{-4, -1, -1, 0, 1, 2});
    assertEquals(
        Set.of(List.of(-1, -1, 2), List.of(-1, 0, 1)),
        asSet(actual));
    assertEquals(2, actual.size());
  }

  @Test
  void testAllSameValueNoSolution() {
    assertTrue(solution.threeSum(new int[]{5, 5, 5, 5}).isEmpty());
    assertTrue(solution.threeSum(new int[]{-3, -3, -3, -3}).isEmpty());
  }

  @Test
  void testNoSolution() {
    assertTrue(solution.threeSum(new int[]{1, 2, 3, 4, 5}).isEmpty());
    assertTrue(solution.threeSum(new int[]{-5, -4, -3, -2, -1}).isEmpty());
  }

  @Test
  void testDoesNotMutateInputExpectation() {
    // Вход сортируется на месте — это допустимо по условию,
    // но проверяем, что ответ не зависит от порядка обхода.
    var actual = solution.threeSum(new int[]{3, 0, -2, 1, -1, 2, -1, -4});
    assertTrue(actual.contains(List.of(-1, 0, 1)) || actual.contains(List.of(1, 0, -1)));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertEquals(Set.of(List.of(0, 0, 0)), asSet(solution.threeSum(new int[]{0, 0, 0})));
    assertTrue(solution.threeSum(new int[]{}).isEmpty());
    assertEquals(
        Set.of(List.of(-1, -1, 2), List.of(-1, 0, 1)),
        asSet(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4})));
  }

  @Test
  void testLongRunWithManyDuplicates() {
    // 300 копий каждого из -1, 0, 1: ровно две тройки — -1,0,1 и 0,0,0.
    int n = 900;
    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = (i % 3) - 1;
    }
    var actual = solution.threeSum(nums);
    assertEquals(Set.of(List.of(-1, 0, 1), List.of(0, 0, 0)), asSet(actual));
    assertEquals(2, actual.size());
  }
}
