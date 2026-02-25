package by.semenyukna.rd;

public class Solution {
  public int removeDuplicates(int[] nums) {
    var w = 2;
    for (int r = 2; r < nums.length; r++) {
      if (nums[r] != nums[w - 2]) {
        nums[w] = nums[r];
        w++;
      }
    }
    return w;
  }

}