package by.semenyukna.combinations;


import java.util.ArrayList;
import java.util.List;


public class Solution {


  private void backtrack(int k, int n, List<List<Integer>> res, List<Integer> cur, int start) {
    if (cur.size() == k) {
      res.add(new ArrayList<>(cur));
      return;
    } else {
      for (int i = start; i < n; i++) {
        cur.add(i + 1);
        backtrack(k, n, res, cur, i+1);
        cur.removeLast();
      }
    }
  }


  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    backtrack(k, n, res, new ArrayList<>(), 0);
    return res;
  }
}