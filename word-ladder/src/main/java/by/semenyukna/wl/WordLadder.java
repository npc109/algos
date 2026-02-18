package by.semenyukna.wl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordLadder {


  record Element(String word, int step) {
  }


  public static boolean checkWord(String word1, String word2) {
    var c = 0;
    for (int i = 0; i < word1.length(); i++) {
      if (word1.charAt(i) != word2.charAt(i)) {
        c++;
      }
    }
    return c == 1;
  }

  public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    HashSet<String> wordSet = new HashSet<>(wordList);
    if (beginWord.equals(endWord) || !wordSet.contains(endWord)) {
      return 0;
    }
    Queue<Element> queue = new LinkedList<Element>();
    HashSet<String> visited = new HashSet<>();
    queue.add(new Element(beginWord, 1));
    visited.add(beginWord);
    while (!queue.isEmpty()) {
      var curElement = queue.poll();
      var curWord = curElement.word;
      var curStep = curElement.step;
      for (String word : wordSet) {
        if (checkWord(curWord, word) && !visited.contains(word)) {
          if (word.equals(endWord)) {
            return curStep + 1;
          } else {
            queue.add(new Element(word, curStep + 1));
            visited.add(word);
          }
        }
      }
    }
    return 0;
  }

}