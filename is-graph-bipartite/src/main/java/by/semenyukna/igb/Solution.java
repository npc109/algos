package by.semenyukna.igb;

public class Solution {

  private boolean dfs(int n, int[][] graph, int[] state, int parentColor) {
    int color = parentColor == 1 ? 2 : 1;
    if (state[n] == 0) {
      state[n] = color;
      for (var i : graph[n]) {
        if (!dfs(i, graph, state, color)) {
          return false;
        }
      }
      return true;
    }
    return state[n] == color;
  }

  public boolean isBipartite(int[][] graph) {
    int[] state = new int[graph.length];
    int color = 1;
    for (var i = 0; i < state.length; i++) {
      if (state[i] != 0) {
        continue;
      }
      state[i] = color;
      for (var n : graph[i]) {
        if (!dfs(n, graph, state, color)) {
          return false;
        }
      }
    }
    return true;
  }
}
