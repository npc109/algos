package by.semenyukna.ts;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {

    private HashMap<java.util.List<Integer>, Integer> memo = new HashMap<>();

    private int bt(int target, int[] nums, int start) {
        var k = new ArrayList<Integer>();
        k.add(target);
        k.add(start);
        var cur = memo.get(k);
        if (cur != null) {
            return cur;
        }
        if (start == nums.length) {
            if (target == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        memo.put(k, bt(target + nums[start], nums, start + 1) + bt(target + nums[start] * -1, nums, start + 1));
        return memo.get(k);

    }

    public int findTargetSumWays(int[] nums, int target) {
        return bt(target, nums, 0);

    }
}
