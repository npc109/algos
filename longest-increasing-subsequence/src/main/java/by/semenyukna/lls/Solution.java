package by.semenyukna.lls;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {

  public int search(int[] nums, int ind, int[] r) {
    if (r[ind] != 0) {
      return r[ind];
    }
    var max = 1;
    for (var i = ind; i >= 0; i--) {
      if (nums[ind] > nums[i]) {
        max = Math.max(max, 1 + search(nums, i, r));
      }
    }
    return max;

  }


  public int lengthOfLIS(int[] nums) {
    var res = 1;
    int[] r = new int[nums.length];
    for (var i = 0; i < nums.length; i++) {
      int cur = search(nums, i, r);
      res = Math.max(res, cur);
      r[i] = cur;
    }
    return res;
  }
}