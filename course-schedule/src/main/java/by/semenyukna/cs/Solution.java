package by.semenyukna.cs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

  private boolean dfs(Integer el, Map<Integer, List<Integer>> m, Map<Integer, Integer> v) {
    var cur = v.get(el);
    if (cur == 1) {
      return false;
    }
    if (cur == 2) {
      return true;
    }
    v.put(el, 1);
    for (var n : m.get(el)) {
      if (!dfs(n, m, v)) {
        return false;
      }
    }
    v.put(el, 2);
    return true;
  }

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> m = new HashMap<>();
    Map<Integer, Integer> v = new HashMap<>();
    for (var i = 0; i < numCourses; i++) {
      m.put(i, new ArrayList());
      v.put(i, 2);
    }
    for (var num : prerequisites) {
      m.get(num[0]).add(num[1]);
      v.put(num[0], 0);
    }
    for (var k : m.keySet()) {
      if (!dfs(k, m, v)) {
        return false;
      }
    }
    return true;
  }
}
