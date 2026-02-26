package by.semenyukna.cc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

  public int coinChange(int[] coins, int amount) {
    if (amount == 0) {
      return 0;
    }
    Queue<Integer> queue = new LinkedList<>();
    HashSet<Integer> visited = new HashSet<>();
    for (var x : coins) {
      queue.add(x);
    }
    var step = 1;

    while (!queue.isEmpty()) {
      var size = queue.size();
      for (int i = 0; i < size; i++) {
        var cur = queue.poll();
        if (visited.contains(cur)) {
          continue;
        }
        visited.add(cur);
        if (cur == amount) return step;
        if (cur < amount) {
          for (var x : coins) {
            queue.add(x + cur);
          }
        }
      }
      step++;
    }
    return -1;
  }
}