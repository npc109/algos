package by.semenyukna.cf.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DfsSolution {

  private Integer res = Integer.MAX_VALUE;
  private Map<Integer, Map<Integer, Integer>> visited = new HashMap<>();

  public void dfs(int src, int dst, Map<Integer, List<int[]>> m, int k, int cur) {
    if (src == dst) {
      res = Math.min(res, cur);
    }
    if (k == -1 || cur >= res) {
      return;
    }
    var now = m.get(src);
    var v = visited.get(src);

    if (now == null || (v != null && v.getOrDefault(k, Integer.MAX_VALUE) < cur)) {
      return;
    }
    if (v == null) {
      v = new HashMap<Integer, Integer>();
      visited.put(src, v);
    }
    v.put(k, cur);

    for (var x : now) {
      dfs(x[1], dst, m, k - 1, cur + x[2]);
    }

  }

  public int findCheapestPrice(int n, int[][] f, int src, int dst, int k) {
    Map<Integer, List<int[]>> m = new HashMap<>();
    for (var x : f) {
      var c = m.get(x[0]);
      if (c != null) {
        c.add(x);
      } else {
        c = new ArrayList<>();
        c.add(x);
        m.put(x[0], c);
      }

    }
    dfs(src, dst, m, k, 0);
    return res == Integer.MAX_VALUE ? -1 : res;
  }
}
