package by.semenyukna.cc;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  // Эталон: BFS по суммам. Слой k — все суммы, набираемые k монетами.
  private int bruteForce(int[] coins, int amount) {
    int[] dist = new int[amount + 1];
    Arrays.fill(dist, -1);
    dist[0] = 0;
    ArrayDeque<Integer> q = new ArrayDeque<>();
    q.add(0);
    while (!q.isEmpty()) {
      int s = q.poll();
      for (int c : coins) {
        long t = (long) s + c;
        if (t <= amount && dist[(int) t] == -1) {
          dist[(int) t] = dist[s] + 1;
          q.add((int) t);
        }
      }
    }
    return dist[amount];
  }

  private void check(int[] coins, int amount) {
    assertEquals(bruteForce(coins, amount), new Solution().coinChange(coins, amount),
        "coins = " + Arrays.toString(coins) + ", amount = " + amount);
  }

  @Test
  public void example1() {
    check(new int[]{1, 2, 5}, 11);
  }

  @Test
  public void example2() {
    check(new int[]{2}, 3);
  }

  @Test
  public void example3() {
    check(new int[]{1}, 0);
  }

  @Test
  public void testSolution1() {
    check(new int[]{1, 2, 3}, 5);
  }

  @Test
  public void testSolution2() {
    check(new int[]{2}, 3);
  }

  @Test
  public void testSolution3() {
    check(new int[]{1}, 0);
  }

  @Test
  public void testSolution4() {
    check(new int[]{2, 5}, 6);
  }

  @Test
  public void testSolution5() {
    check(new int[]{1, 2, 5}, 100);
  }

  @Test
  public void greedyWouldFail() {
    // Жадный «берём самую крупную» даст 4+1+1 = 3, оптимум 3+3 = 2.
    check(new int[]{1, 3, 4}, 6);
  }

  @Test
  public void greedyWouldFailAmountSeven() {
    check(new int[]{1, 3, 4}, 7);
  }

  @Test
  public void amountZeroAnyCoins() {
    check(new int[]{5, 7}, 0);
  }

  @Test
  public void unreachableSmallAmount() {
    check(new int[]{2}, 1);
  }

  @Test
  public void singleCoinExactAmount() {
    check(new int[]{7}, 7);
  }

  @Test
  public void coinLargerThanAmount() {
    check(new int[]{10, 20}, 5);
  }

  @Test
  public void maxCoinValue() {
    // coins[i] до 2^31 - 1: i - coin уходит в int-минус, ловим переполнение.
    check(new int[]{Integer.MAX_VALUE}, 2);
  }

  @Test
  public void maxCoinValueAndAmountZero() {
    check(new int[]{Integer.MAX_VALUE}, 0);
  }

  @Test
  public void minCoinAndMaxAmount() {
    assertEquals(10000, new Solution().coinChange(new int[]{1}, 10000));
  }

  @Test
  public void stressRandom() {
    Random rnd = new Random(11);
    for (int iter = 0; iter < 20000; iter++) {
      int n = 1 + rnd.nextInt(5);
      int[] coins = new int[n];
      for (int i = 0; i < n; i++) {
        coins[i] = 1 + rnd.nextInt(20);
      }
      check(coins, rnd.nextInt(60));
    }
  }

  @Test
  public void stressRandomWideAmounts() {
    Random rnd = new Random(99);
    for (int iter = 0; iter < 2000; iter++) {
      int n = 1 + rnd.nextInt(12);
      int[] coins = new int[n];
      for (int i = 0; i < n; i++) {
        coins[i] = 1 + rnd.nextInt(300);
      }
      check(coins, rnd.nextInt(2000));
    }
  }
}
