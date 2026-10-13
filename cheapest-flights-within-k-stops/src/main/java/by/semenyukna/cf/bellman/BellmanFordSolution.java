package by.semenyukna.cf.bellman;

import java.util.Arrays;

public class BellmanFordSolution {

  public int findCheapestPrice(int n, int[][] f, int src, int dst, int k) {
    int[] x = new int[n];
    for (var i = 0; i < x.length; i++) {
      x[i] = Integer.MAX_VALUE;
    }
    x[src] = 0;
    for (var i = 0; i < k + 1; i++) {
      int[] next = Arrays.copyOf(x, x.length);
      for (var cur : f) {
        if (x[cur[0]] != Integer.MAX_VALUE && x[cur[0]] + cur[2] < next[cur[1]]) {
          next[cur[1]] = x[cur[0]] + cur[2];
        }

      }
      x = next;
    }
    return x[dst] == Integer.MAX_VALUE ? -1 : x[dst];
  }
}
