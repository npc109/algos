package by.semenyukna.np;

public class Solution {

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public void nextPermutation(int[] nums) {
    if (nums.length == 1) return;
    var breakPoint = -1;
    var n = nums.length - 1;
    for (int i = n; i > 0; i--) {
      if (nums[i] > nums[i - 1]) {
        breakPoint = i;
        break;
      }
    }
    if (breakPoint == -1) {
      var l = 0;
      var r = n;
      while (l < r) {
        swap(nums, l, r);
        r--;
        l++;
      }
      return;
    }
    for (int i = n; i >= breakPoint-1; i--) {
      if (nums[i] > nums[breakPoint-1]) {
        swap(nums, i, breakPoint-1);
        break;
      }
    }

    while (breakPoint < n) {
      swap(nums, breakPoint, n);
      n--;
      breakPoint++;
    }

  }

}