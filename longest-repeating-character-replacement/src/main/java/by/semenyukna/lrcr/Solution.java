package by.semenyukna.lrcr;

import java.util.HashMap;
import java.util.Map;

class Solution {
  public int characterReplacement(String s, int k) {
    var fast = 0;
    var slow = 0;
    var result = 0;
    var mf = 0;
    Map<Character, Integer> m = new HashMap<>();
    while (fast < s.length() && slow <= fast) {
      var curL = s.charAt(fast);
      var curV = m.getOrDefault(curL, 0) + 1;
      m.put(curL, curV);
      mf = Math.max(mf, curV);
      if (mf + k <= fast - slow) {
        var curS = s.charAt(slow);
        m.put(curS, m.get(curS) - 1);
        slow++;
        fast++;
        continue;
      }
      result = Math.max(fast + 1 - slow, result);
      fast++;
    }
    return result;
  }
}
