package by.semenyukna.mst.prim;

import java.util.PriorityQueue;
import java.util.Queue;

public class PrimSolution {

  private int getDist(int x1, int x2, int y1, int y2) {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
  }

  public int minCostConnectPoints(int[][] points) {
    boolean[] visited = new boolean[points.length];
    Queue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    var c = 0;
    visited[0] = true;
    for (var i = 1; i < points.length; i++) {
      q.add(new int[]{i, getDist(points[0][0], points[i][0], points[0][1], points[i][1])});
    }
    var s = 1;
    while (s < points.length) {
      var cur = q.poll();
      if (!visited[cur[0]]) {
        visited[cur[0]] = true;
        for (var i = 0; i < points.length; i++) {
          if (i != cur[0] && !visited[i]) {
            q.add(new int[]{i, getDist(points[cur[0]][0], points[i][0], points[cur[0]][1], points[i][1])});
          }
        }
        s = s + 1;
        c = c + cur[1];
      }
    }
    return c;

  }

}
