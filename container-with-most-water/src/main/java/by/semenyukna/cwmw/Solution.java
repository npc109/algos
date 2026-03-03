package by.semenyukna.cwmw;

public class Solution {

  public int maxArea(int[] height) {
    var l = 0;
    int[] x = {1,2};
    var r = height.length - 1;
    var max = 0;
    while (l < r) {
      var s = Math.min(height[r], height[l]) * (r - l);
      max = Math.max(max, s);
      if (height[r] < height[l]) {
        r--;
      } else {
        l++;
      }
    }
    return max;
  }
}