package by.semenyukna.rc;

public class Solution {

  private int find(int x, int[] parent) {
    while (parent[x] != x) {
      var cur = x;
      x = parent[x];
      parent[cur] = parent[x];
    }
    return x;
  }

  private boolean union(int a, int b, int[] parent, int[] size) {
    int pa = find(a, parent);
    int pb = find(b, parent);
    if (pa == pb) {
      return true;
    }
    if (size[pa] > size[pb]) {
      size[pa] += size[pb];
      parent[pb] = pa;
    } else {
      size[pb] += size[pa];
      parent[pa] = pb;
    }

    return false;
  }

  public int[] findRedundantConnection(int[][] edges) {
    int[] parrent = new int[edges.length];
    int[] size = new int[edges.length];
    for (var i = 0; i < parrent.length; i++) {
      parrent[i] = i;
      size[i] = 1;
    }
    for (var n : edges) {
      if (union(n[0] - 1, n[1] - 1, parrent, size)) {
        return new int[]{n[0], n[1]};
      }

    }
    return new int[]{};
  }
}
