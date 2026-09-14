package by.semenyukna.noi;

public class Solution {

  private boolean dfs(char[][] grid, int x, int y) {
    if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length) {
      return false;
    }

    if (grid[x][y] == '0') {
      return false;
    }

    grid[x][y] = '0';
    dfs(grid, x + 1, y);
    dfs(grid, x - 1, y);
    dfs(grid, x, y + 1);
    dfs(grid, x, y - 1);
    return true;

  }

  public int numIslands(char[][] grid) {
    var counter = 0;
    for (var i = 0; i < grid.length; i++) {
      for (var j = 0; j < grid[i].length; j++) {
        var r = dfs(grid, i, j);
        if (r) {
          counter++;
        }
      }
    }
    return counter;

  }
}
