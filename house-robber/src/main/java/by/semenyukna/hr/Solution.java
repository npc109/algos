package by.semenyukna.hr;

public class  Solution {

  public int rob(int[] nums) {
    int prev1 = 0;
    int prev2 = 0;
    for (var num : nums) {
      int tmp = prev1;
      prev1 = Math.max(prev2 + num, prev1);
      prev2 = tmp;
    }

    return prev1;
  }
}
