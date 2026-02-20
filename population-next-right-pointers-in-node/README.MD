# 116. Populating Next Right Pointers in Each Node

- leetcode
    - https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description
- LVL
    - MEDIUM
- Topics
    - BFS
    - Tree
    - Binary tree

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The
binary tree has the following definition:

    struct Node {
        int val;
        Node *left;
        Node *right;
        Node *next;
    }

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be
set to NULL.

Initially, all next pointers are set to NULL.

Exemple 1

    Input: root = [1,2,3,4,5,6,7]
    Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

Example 2:

    Input: root = []
    Output: []

**Описание задачи:** Заполнить указатели `next` в каждом узле совершенного бинарного дерева так, чтобы каждый указатель `next` указывал на следующий узел справа на том же уровне.

**Алгоритм:** Обход дерева без дополнительной памяти (O(1) space). Использует уже установленные связи `next` для перехода к следующему уровню.

**Пример:**
```
Дерево до:                    Дерево после:
       1                           1 → NULL
      / \                         / \
     2   3                       2 → 3 → NULL
    / \ / \                     / \ / \
   4  5 6  7                   4→5→6→7 → NULL
```
