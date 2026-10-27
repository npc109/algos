package by.semenyukna.perm;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> cur) {
    if (cur.size() == nums.length) {
      res.add(new ArrayList<>(cur));
      return;
    }
    for (var n : nums) {
      if (!cur.contains(n)) {
        cur.add(n);
        backtrack(nums, res, cur);
        cur.removeLast();
      }
    }
  }

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    backtrack(nums, res, new ArrayList<>());
    return res;
  }
}
