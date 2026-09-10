package by.semenyukna.wd;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

  public int[] solution(int[] temperatures) {
    int[] res = new int[temperatures.length];
    Deque<Integer> s = new ArrayDeque<Integer>();
    for (var i = 0; i < temperatures.length; i++) {
      var t = temperatures[i];
      while (!s.isEmpty() && temperatures[s.peek()] < t) {
        var cur = s.pop();
        res[cur] = i - cur;
      }
      s.push(i);
    }
    return res;
  }
}