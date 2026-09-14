package by.semenyukna.ts2;


public class Solution {

  public int[] twoSum(int[] numbers, int target) {
    int l = 0;
    int r = numbers.length -1;
    while (l < r) {
      var s = (long) numbers[l] + numbers[r];
      if (s == target) {
        return new int[]{l+1, r+1};
      } else if (s < target) {
        l++;
      } else {
        r--;
      }

    }
    return new int[]{0, 0};
  }
}