package by.semenyukna.wl;

import java.util.*;

public class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    int res = 1;
    Set<String> wordSet = new HashSet<>(wordList);
    if (!wordSet.contains(endWord))
      return 0;
    if (beginWord.equals(endWord))
      return 0;

    Queue<String> q = new ArrayDeque<>();
    Set<String> visited = new HashSet<>();
    q.add(beginWord);
    visited.add(beginWord);

    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        String cur = q.poll();
        char[] ca = cur.toCharArray();
        for (int pos = 0; pos < ca.length; pos++) {
          char old = ca[pos];
          for (char ch = 'a'; ch <= 'z'; ch++) {
            if (ch == old)
              continue;
            ca[pos] = ch;
            String next = new String(ca);
            if (wordSet.contains(next) && visited.add(next)) {
              if (next.equals(endWord))
                return res + 1;
              q.add(next);
            }
          }
          ca[pos] = old;
        }
      }
      res++;
    }
    return 0;
  }
}