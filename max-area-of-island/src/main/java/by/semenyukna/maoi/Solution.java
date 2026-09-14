package by.semenyukna.maoi;

public class Solution {

  private int sumDfs(int[][] grid, int x, int y) {
    if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length || grid[x][y] == 0) {
      return 0;
    }
    grid[x][y] = 0;
    return 1 + sumDfs(grid, x + 1, y)
        + sumDfs(grid, x - 1, y)
        + sumDfs(grid, x, y + 1)
        + sumDfs(grid, x, y - 1);
  }

  public int maxAreaOfIsland(int[][] grid) {
    int m = 0;
    for (var i = 0; i < grid.length; i++) {
      for (var j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1) {
          m = Math.max(m, sumDfs(grid, i, j));
        }
      }

    }

    return m;
  }
}
