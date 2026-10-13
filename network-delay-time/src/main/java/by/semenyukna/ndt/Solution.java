package by.semenyukna.ndt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
  public int networkDelayTime(int[][] times, int n, int k) {
    Map<Integer, Integer> minMap = new HashMap<>();
    Map<Integer, ArrayList<int[]>> m = new HashMap<>();
    var result = -1;
    for (var i : times) {
      var mapValue = m.get(i[0]);
      if (mapValue != null) {
        mapValue.add(i);
      } else {
        var r = new ArrayList<int[]>();
        r.add(i);
        m.put(i[0], r);
      }
    }

    Queue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2]);
    var initValue = m.get(k);
    if (initValue != null) {
      for (var i : initValue) {
        q.add(i);
      }
    }
    minMap.put(k, 0);
    while (!q.isEmpty()) {
      var el = q.poll();
      var cur = minMap.getOrDefault(el[1], Integer.MAX_VALUE);
      var val = el[2];
      if (val < cur) {
        minMap.put(el[1], el[2]);
        var c = m.get(el[1]);
        if (c != null) {
          for (var i : c) {
            if (i[1] != k) {
              if (val + i[2] < minMap.getOrDefault(i[1], Integer.MAX_VALUE)) {
                q.add(new int[]{i[0], i[1], val + i[2]});
              }
            }
          }
        }
      }

    }
    result = minMap.size() == n ? Collections.max(minMap.values()) : -1;
    return result;
  }
}
