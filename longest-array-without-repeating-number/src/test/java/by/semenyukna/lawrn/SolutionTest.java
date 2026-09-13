package by.semenyukna.lawrn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {

  @Test
  void testExample() {
    int[] a = {1, 2, 3, 1, 2, 3, 4, 5, 6};
    var s = new Solution();
    assertEquals(6, s.getLongest(a));
  }

  @Test
  void testAllUnique() {
    int[] a = {1, 2, 3, 4, 5};
    var s = new Solution();
    assertEquals(5, s.getLongest(a));
  }

  @Test
  void testAllEqual() {
    int[] a = {7, 7, 7, 7};
    var s = new Solution();
    assertEquals(1, s.getLongest(a));
  }

  @Test
  void testRepeatAfterWindow() {
    int[] a = {1, 2, 3, 2, 4, 5, 6};
    var s = new Solution();
    assertEquals(5, s.getLongest(a));
  }

  @Test
  void testSingleElement() {
    int[] a = {42};
    var s = new Solution();
    assertEquals(1, s.getLongest(a));
  }

  @Test
  void testEmpty() {
    int[] a = {};
    var s = new Solution();
    assertEquals(0, s.getLongest(a));
  }

  @Test
  void testRepeatedWithinWindow() {
    int[] a = {1, 2, 1, 2, 3};
    var s = new Solution();
    assertEquals(3, s.getLongest(a));
  }
}
