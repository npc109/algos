package by.semenyukna.csii;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  private boolean dfs(int el, List<List<Integer>> m, int[] state, List<Integer> res) {
    if (state[el] == 1) {
      return false;
    }
    if (state[el] == 2) {
      return true;
    }
    state[el] = 1;
    for (var n : m.get(el)) {
      if (!dfs(n, m, state, res)) {
        return false;
      }

    }
    state[el] = 2;
    res.add(el);
    return true;
  }

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<Integer> res = new ArrayList<>();
    int[] state = new int[numCourses];
    List<List<Integer>> m = new ArrayList<>();
    for (var i = 0; i < numCourses; i++) {
      m.add(new ArrayList());
    }
    for (var i : prerequisites) {
      m.get(i[0]).add(i[1]);
    }

    for (var k = 0; k < m.size(); k++) {
      if (!dfs(k, m, state, res)) {
        return new int[]{};
      }

    }

    return res.stream().mapToInt(i -> i).toArray();

  }
}
