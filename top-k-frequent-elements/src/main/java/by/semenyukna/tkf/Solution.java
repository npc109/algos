package by.semenyukna.tkf;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
      int[] r  = new int[k];
      Map<Integer,Integer> m = new HashMap<>();
      PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->a[1]-b[1]);
      for(var n: nums){
        var c = m.getOrDefault(n,0);
        c = c+1;
        m.put(n,c);
      }
     for(var key: m.keySet()){
          q.add(new int[]{key,m.get(key)});
          if(q.size()>k){
            q.poll();
          }
     }

    for(var i=0; i<r.length;i++){
        r[i]=q.poll()[0];
    }
        return r;
    }
}
