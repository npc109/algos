package by.semenyukna.nop;

public class Solution {

  private boolean union(int a, int b, int[] parent) {
    var pa = find(a, parent);
    var pb = find(b, parent);
    if (pb == pa) {
      return true;
    }
    parent[pb] = pa;
    return false;
  }

  private int find(int x, int[] parent) {
    while (x != parent[x]) {
      parent[x] = parent[parent[x]];
      x = parent[x];
    }
    return x;
  }

  public int findCircleNum(int[][] isConnected) {
    var parent = new int[isConnected.length];
    for (var i = 0; i < parent.length; i++) {
      parent[i] = i;
    }
    var c = parent.length;
    for (var i = 0; i < parent.length; i++) {
      for (var j = i + 1; j < parent.length; j++) {
        if (isConnected[i][j] == 1) {
          if (!union(i, j, parent)) {
            c--;
          }
        }

      }
    }
    return c;
  }
}
