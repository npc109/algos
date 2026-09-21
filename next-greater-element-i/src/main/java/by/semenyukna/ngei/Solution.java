package by.semenyukna.ngei;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Deque<Integer> q = new ArrayDeque<>();
    Map<Integer, Integer> m = new HashMap<>();
    int[] r = new int[nums1.length];
    for (var i = 0; i < nums1.length; i++) {
      m.put(nums1[i], i);
      r[i] = -1;
    }
    for (var n = 0; n < nums2.length; n++) {
      while (!q.isEmpty() && nums2[n] > nums2[q.peekLast()]) {
        var c = q.pollLast();
        Integer idx = m.get(nums2[c]);
        if (idx != null) {
          r[idx] = nums2[n];
        }
      }
      q.add(n);
    }
    return r;
  }
}
