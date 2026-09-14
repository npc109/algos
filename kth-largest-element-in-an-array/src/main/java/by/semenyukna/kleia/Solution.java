package by.semenyukna.kleia;


import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
  public int findKthLargest(int[] nums, int k) {

    PriorityQueue<Integer> q = new PriorityQueue<Integer>();
    for(var num:nums){
      q.add(num);
      if(k<q.size()){
        q.poll();
      }
    }
    return q.peek();
  }
}