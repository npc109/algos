package by.semenyukna.zom;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

  private void helper(int[][] mat, int x, int y, int v, Queue<int[]> q) {
    if (x < 0 || y < 0 || x >= mat.length || y >= mat[x].length || mat[x][y] >= 0) {
      return;
    }
    mat[x][y] = v + 1;
    q.add(new int[]{x, y});
  }

  public int[][] updateMatrix(int[][] mat) {
    Queue<int[]> q = new ArrayDeque();

    for (var x = 0; x < mat.length; x++) {
      for (var y = 0; y < mat[x].length; y++) {
        if (mat[x][y] == 0) {
          q.add(new int[]{x, y});
        } else {
          mat[x][y] = -1;
        }
      }
    }
    while (!q.isEmpty()) {
      var el = q.poll();
      var x = el[0];
      var y = el[1];
      helper(mat, x + 1, y, mat[x][y], q);
      helper(mat, x - 1, y, mat[x][y], q);
      helper(mat, x, y + 1, mat[x][y], q);
      helper(mat, x, y - 1, mat[x][y], q);
    }
    return mat;
  }
}
