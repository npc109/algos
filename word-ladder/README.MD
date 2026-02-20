# Word Ladder
- leetcode
    - https://leetcode.com/problems/word-ladder/description/
- LVL
    - Hard
- Topics
  - Hash Table
  - String
  - BFS
    
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.



**Example 1:**

    **Input**: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    
    **Output**: 5
    
    **Explanation**: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

**Example 2:**

    **Input**: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
    **Output**: 0
    **Explanation**: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.


Constraints:

1 <= beginWord.length <= 10

endWord.length == beginWord.length

1 <= wordList.length <= 5000

wordList[i].length == beginWord.length

beginWord, endWord, and wordList[i] consist of lowercase English letters.

beginWord != endWord

All the words in wordList are unique.


**Описание задачи:** Дана цепочка слов, где каждое следующее слово отличается от предыдущего ровно одной буквой. Необходимо найти кратчайший путь преобразования от начального слова к конечному, используя слова из заданного пула.

**Алгоритм:** Поиск в ширину (BFS) для нахождения кратчайшего пути в графе, где вершины — слова, а рёбра соединяют слова с одной отличающейся буквой.

**Пример:**
```
Вход: "hit" → "cog"
Пул слов: ["hot","dot","dog","lot","log","cog"]
Результат: 5
```

**Визуализация пути:**
```
hit → hot → dot → dog → cog
      ↑     ↑     ↑     ↑
     одна буква отличается
```