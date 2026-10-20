package by.semenyukna.lswrc;

import java.util.HashSet;
import java.util.Set;

class Solution {
  public int lengthOfLongestSubstring(String s) {
    var fast = 0;
    var slow = 0;
    Set<Character> pool = new HashSet<>();
    var result = 0;
    var res = 0;
    while (fast < s.length()) {
      var cur = s.charAt(fast);
      if (!pool.contains(cur)) {
        res++;
        fast++;
        pool.add(cur);
      } else {
        var scur = s.charAt(slow);
        if (scur == cur) {
          fast++;
          slow++;
          res = fast - slow;
          continue;
        }
        pool.remove(scur);
        slow++;
      }
      result = Math.max(res, result);
    }
    return result;
  }
}
