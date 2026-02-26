package by.semenyukna.cc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {

    @Test
    public void testSolution1() {
        var s =  new Solution();
        var coins = new int[]{1, 2, 3};
        var res = s.coinChange(coins, 5);
        assertEquals(2, res);
    }

  @Test
  public void testSolution2() {
    var s =  new Solution();
    var coins = new int[]{2};
    var res = s.coinChange(coins, 3);
    assertEquals(-1, res);
  }

  @Test
  public void testSolution3() {
    var s =  new Solution();
    var coins = new int[]{1};
    var res = s.coinChange(coins, 0);
    assertEquals(0, res);
  }


  @Test
  public void testSolution4() {
    var s =  new Solution();
    var coins = new int[]{2,5};
    var res = s.coinChange(coins, 6);
    assertEquals(3, res);
  }
  @Test
  public void testSolution5() {
    var s =  new Solution();
    var coins = new int[]{1,2,5};
    var res = s.coinChange(coins, 100);
    assertEquals(20, res);
  }

}