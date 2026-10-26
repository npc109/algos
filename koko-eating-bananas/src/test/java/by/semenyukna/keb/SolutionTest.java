package by.semenyukna.keb;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  // Эталон: перебор k от 1 до max(piles) по определению из условия.
  private int bruteForce(int[] piles, int h) {
    int max = 0;
    for (int p : piles) {
      max = Math.max(max, p);
    }
    for (int k = 1; k <= max; k++) {
      long hours = 0;
      for (int p : piles) {
        hours += (p + k - 1) / k;
      }
      if (hours <= h) {
        return k;
      }
    }
    throw new IllegalStateException("недостижимо: k = max(piles) всегда укладывается");
  }

  private void check(int[] piles, int h) {
    assertEquals(bruteForce(piles, h), new Solution().minEatingSpeed(piles, h),
        "piles = " + Arrays.toString(piles) + ", h = " + h);
  }

  @Test
  void example1() {
    check(new int[]{3, 6, 7, 11}, 8);
  }

  @Test
  void example2() {
    check(new int[]{30, 11, 23, 4, 20}, 5);
  }

  @Test
  void example3() {
    check(new int[]{30, 11, 23, 4, 20}, 6);
  }

  @Test
  void onePileOneHour() {
    // Ветка l == h.
    check(new int[]{1}, 1);
    check(new int[]{7}, 1);
  }

  @Test
  void pilesCountEqualsHours() {
    // Ветка l == h: по одной куче за час, ответ — максимальная куча.
    check(new int[]{3, 7, 5}, 3);
    check(new int[]{1, 1, 1, 1}, 4);
    check(new int[]{2, 2}, 2);
  }

  @Test
  void allPilesEqualOddHours() {
    check(new int[]{5, 5, 5}, 6);
  }

  @Test
  void kEqualsOne() {
    // h = sum(piles): по одному банану в час.
    check(new int[]{1, 2, 3}, 6);
  }

  @Test
  void slowestPossibleSpeed() {
    check(new int[]{1, 1, 1}, 3);
  }

  @Test
  void hugeH() {
    check(new int[]{312884470}, 968709470);
  }

  @Test
  void maxPileValueSinglePile() {
    check(new int[]{1000000000}, 1000000000);
  }

  @Test
  void overflowCandidate() {
    // 3 кучи по 805306368, h = 10^9. Сумма часов при k = 1 не влезает в int.
    check(new int[]{805306368, 805306368, 805306368}, 1000000000);
  }

  @Test
  void intOverflowInPlusMinusOne() {
    // n + k - 1 при n, k ~ 10^9: проверяем, что не переполняется в int.
    check(new int[]{805306368, 805306368, 805306368}, 1000000000);
  }

  @Test
  void duplicatePiles() {
    check(new int[]{7, 7, 7, 7}, 10);
  }

  @Test
  void stressRandom() {
    Random rnd = new Random(2024);
    for (int iter = 0; iter < 20000; iter++) {
      int n = 1 + rnd.nextInt(8);
      int[] piles = new int[n];
      for (int i = 0; i < n; i++) {
        piles[i] = 1 + rnd.nextInt(40);
      }
      int sum = 0;
      for (int p : piles) {
        sum += p;
      }
      check(piles, n + rnd.nextInt(sum - n + 1));
    }
  }

  @Test
  void stressRandomWideRange() {
    Random rnd = new Random(7);
    for (int iter = 0; iter < 300; iter++) {
      int n = 1 + rnd.nextInt(20);
      int[] piles = new int[n];
      for (int i = 0; i < n; i++) {
        piles[i] = 1 + rnd.nextInt(100000);
      }
      long sum = 0;
      for (int p : piles) {
        sum += p;
      }
      long h = n + (long) (rnd.nextDouble() * (sum - n));
      check(piles, (int) Math.min(h, 1000000000L));
    }
  }
}
