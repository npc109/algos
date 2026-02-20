# 297. Serialize and Deserialize Binary Tree
- leetcode
    - https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
- LVL
    - Hard
- Topics
    - BFS
    - String
    - Design
    - Tree
    - Binary tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example 1:

    Input: root = [1,2,3,null,null,4,5]
    Output: [1,2,3,null,null,4,5]

Example 2:

    Input: root = []
    Output: []


**Описание задачи:** Реализовать алгоритмы сериализации и десериализации бинарного дерева.

**Алгоритм:** Обход дерева по уровням (level-order traversal) с использованием очереди.

**Пример:**
```
Дерево:
       1
      / \
     2   3
        / \
       4   5

Сериализация: [1,2,3,null,null,4,5]
```