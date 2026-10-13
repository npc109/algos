package by.semenyukna.mst.kruskal;

import java.util.ArrayList;
import java.util.List;

public class KruskalSolution {

  private int getDist(int x1, int x2, int y1, int y2) {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
  }

  private boolean union(int a, int b, int[] parent, int[] size) {
    var fa = find(a, parent);
    var fb = find(b, parent);
    if (fa == fb) {
      return true;
    }
    if (size[fa] < size[fb]) {
      parent[fa] = fb;
      size[fb] += size[fa];
    } else {
      parent[fb] = fa;
      size[fa] += size[fb];
    }

    return false;

  }

  private int find(int i, int[] parent) {
    while (parent[i] != i) {
      var cur = i;
      i = parent[i];
      parent[cur] = parent[i];
    }
    return i;
  }

  public int minCostConnectPoints(int[][] points) {
    var s = 0;

    var parent = new int[points.length];
    var size = new int[points.length];
    List<int[]> edges = new ArrayList<>();
    var c = 0;
    for (var i = 0; i < points.length - 1; i++) {
      for (var j = i + 1; j < points.length; j++) {
        edges.add(new int[]{getDist(points[i][0], points[j][0], points[i][1], points[j][1]), i, j});
      }
    }

    for (var i = 0; i < parent.length; i++) {
      parent[i] = i;
      size[i] = 1;
    }
    edges.sort((a, b) -> Integer.compare(a[0], b[0]));
    for (var i = 0; i < edges.size(); i++) {
      if (c == points.length - 1) {
        break;
      }
      var cur = edges.get(i);
      if (!union(cur[1], cur[2], parent, size)) {
        s = s + cur[0];
        c++;
      }

    }

    return s;
  }
}
