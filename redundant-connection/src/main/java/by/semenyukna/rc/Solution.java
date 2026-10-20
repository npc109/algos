package by.semenyukna.rc;

class Solution {

  private boolean union(int a, int b, int[] parent) {
    var fa = find(a, parent);
    var fb = find(b, parent);
    if (fa == fb) {
      return false;
    }
    parent[fa] = fb;
    return true;

  }

  private int find(int x, int[] parent) {
    while (x != parent[x]) {
      parent[x] = parent[parent[x]];
      x = parent[x];
    }
    return x;
  }

  public int[] findRedundantConnection(int[][] edges) {
    int[] parent = new int[edges.length];
    for (var i = 0; i < parent.length; i++) {
      parent[i] = i;
    }
    for (var e : edges) {
      if (!union(e[0] - 1, e[1] - 1, parent)) {
        return new int[]{e[0], e[1]};
      }
    }
    return new int[]{};
  }
}
