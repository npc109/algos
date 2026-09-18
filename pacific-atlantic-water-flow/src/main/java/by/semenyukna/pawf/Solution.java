package by.semenyukna.pawf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

  private int isIsset(int[] poss, int[] size, int[][] h) {
    if (poss[0] < 0 || poss[1] < 0 || poss[1] >= size[1] || poss[0] >= size[0]) {
      return Integer.MIN_VALUE;
    }
    return h[poss[0]][poss[1]];

  }

  private void bfs(int[] el, int[][] h, boolean[][][] v, Queue<int[]> q, int[] size, int o) {

    var cur = h[el[0]][el[1]];
    if (v[el[0]][el[1]][o]) {
      return;
    }
    v[el[0]][el[1]][o] = true;

    var l = isIsset(new int[]{el[0] + 1, el[1]}, size, h);
    var r = isIsset(new int[]{el[0] - 1, el[1]}, size, h);
    var t = isIsset(new int[]{el[0], el[1] + 1}, size, h);
    var d = isIsset(new int[]{el[0], el[1] - 1}, size, h);
    if (cur <= l) {
      q.add(new int[]{el[0] + 1, el[1]});
    }
    if (cur <= r) {
      q.add(new int[]{el[0] - 1, el[1]});
    }
    if (cur <= t) {
      q.add(new int[]{el[0], el[1] + 1});
    }
    if (cur <= d) {
      q.add(new int[]{el[0], el[1] - 1});
    }

  }

  public List<List<Integer>> pacificAtlantic(int[][] h) {
    Queue<int[]> right = new LinkedList<int[]>();
    Queue<int[]> left = new LinkedList<int[]>();
    int[] size = new int[]{h.length > 0 ? h.length : 0, h.length > 0 ? h[0].length : 0};
    boolean[][][] v = new boolean[size[0]][size[1]][2];
    List<List<Integer>> res = new ArrayList<>();
    for (var x = 0; x < h.length; x++) {
      for (var y = 0; y < h[x].length; y++) {
        if (x == 0 || y == 0) {
          left.add(new int[]{x, y});
        }
        if (x == h.length - 1 || y == h[x].length - 1) {
          right.add(new int[]{x, y});
        }

      }
    }
    while (!left.isEmpty()) {
      bfs(left.poll(), h, v, left, size, 0);
    }

    while (!right.isEmpty()) {
      bfs(right.poll(), h, v, right, size, 1);
    }

    for (var i = 0; i < v.length; i++) {
      for (var j = 0; j < v[i].length; j++) {
        if (v[i][j][0] && v[i][j][1]) {
          res.add(List.of(i, j));
        }
      }
    }
    return res;
  }
}
