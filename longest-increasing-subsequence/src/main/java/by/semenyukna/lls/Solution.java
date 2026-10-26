package by.semenyukna.lls;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  private int lowerBound(List<Integer> arr, int target) {
    int left = 0;
    int right = arr.size() - 1;

    while (left <= right) {
      int mid = (left + right) / 2;
      if (arr.get(mid) < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return left;
  }

  public int lengthOfLIS(int[] nums) {
    List<Integer> tails = new ArrayList<>();
    for (var n : nums) {
      if (tails.isEmpty() || tails.getLast() < n) {
        tails.add(n);
      } else {
        tails.set(lowerBound(tails, n), n);
      }
    }
    return tails.size();
  }
}
