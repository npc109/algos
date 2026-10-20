package by.semenyukna.nop;

class Solution {

  private boolean union(int a, int b, int[] parent) {
    var fa = find(a, parent);
    var fb = find(b, parent);
    if (fa == fb) {
      return true;
    } else {
      parent[fa] = fb;
    }

    return false;
  }

  private int find(int cur, int[] parent) {
    while (cur != parent[cur]) {
      parent[cur] = parent[parent[cur]];
      cur = parent[cur];
    }
    return cur;

  }

  public int findCircleNum(int[][] isConnected) {
    var res = isConnected.length;
    int[] parent = new int[isConnected.length];
    for (var i = 0; i < parent.length; i++) {
      parent[i] = i;
    }
    for (var i = 0; i < isConnected.length; i++) {
      for (var j = 0; j < isConnected[i].length; j++) {
        if (isConnected[i][j] != 1) continue;
        if (!union(i, j, parent)) {
          res--;
        }
      }

    }
    return res;
  }
}
