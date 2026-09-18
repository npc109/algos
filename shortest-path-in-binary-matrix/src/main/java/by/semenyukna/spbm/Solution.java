package by.semenyukna.spbm;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

  private static final int[][] DIRECTIONS = {
      {-1, -1}, {-1, 0}, {-1, 1},
      {0, -1}, {0, 1},
      {1, -1}, {1, 0}, {1, 1}
  };

  private void push(int[] current, Queue<int[]> queue, int[][] grid) {
    for (var direction : DIRECTIONS) {
      int x = current[0] + direction[0];
      int y = current[1] + direction[1];
      if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != 1) {
        grid[x][y] = 1;
        queue.add(new int[]{x, y, current[2] + 1});
      }
    }
  }

  public int shortestPathBinaryMatrix(int[][] grid) {
    Queue<int[]> queue = new ArrayDeque<>();
    if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) {
      return -1;
    }

    queue.add(new int[]{0, 0, 1});
    while (!queue.isEmpty()) {
      var current = queue.poll();
      if (current[0] == grid.length - 1 && current[1] == grid[0].length - 1) {
        return current[2];
      }
      push(current, queue, grid);
    }

    return -1;
  }
}
