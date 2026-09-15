package by.semenyukna.ro;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

  private void bfs(int[][] grid, int x, int y, Queue<int[]> q) {
    if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length || grid[x][y] == 0 || grid[x][y] == 2) {
      return;
    }
    grid[x][y] = 2;
    q.add(new int[]{x,y});
  }


  public int orangesRotting(int[][] grid) {
    Queue<int[]> q = new LinkedList<>();
    var r = -1;
    var noNeed = true;
    for (var i = 0; i < grid.length; i++) {
      for (var j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 2) {
          q.add(new int[]{i, j});
        }
        if(grid[i][j]==1){
          noNeed=false;
        }
      }
    }
    if(noNeed){
      return 0;
    }

    while (!q.isEmpty()) {
      var s = q.size();
      for (var i = 0; i < s; i++) {
        var el = q.poll();
        bfs(grid, el[0] + 1, el[1], q);
        bfs(grid, el[0] - 1, el[1], q);
        bfs(grid, el[0], el[1] + 1, q);
        bfs(grid, el[0], el[1] - 1, q);
      }
      r++;

    }
    for (int[] ints : grid) {
      for (int anInt : ints) {
        if (anInt == 1) {
          return -1;
        }
      }
    }
    return r;
  }
}
