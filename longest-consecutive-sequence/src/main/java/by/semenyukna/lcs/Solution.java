package by.semenyukna.lcs;

import java.util.HashSet;

public class Solution {

  public int longestConsecutive(int[] nums) {
    HashSet<Integer> h = new HashSet();
    for (var x : nums) {
      h.add(x);
    }
    var biggest = 0;
    for (var el : h) {
      if (!h.contains(el - 1)) {
        var l = 1;
        while (h.contains(el + l)) {
          l++;
        }
        biggest = Math.max(l, biggest);
      }
    }

    return biggest;
  }

}