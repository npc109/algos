package by.semenyukna.cwmw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {

  @Test
  public void testSolution1() {
    int[] arr = {1,8,6,2,5,4,8,3,7};
    var s = new Solution();
    assertEquals(49, s.maxArea(arr));
  }

  @Test
  public void testSolution2() {
    int[] arr = {1,1};
    var s = new Solution();
    assertEquals(1, s.maxArea(arr));
  }

}