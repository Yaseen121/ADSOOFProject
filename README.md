# Algorithms and Data Structures in an Object Oriented Framework Project - A Word Storage Data Structure Implementation (Winter 2017)

A word storage data structure built in Java.

### Overview

First I created a data type called Word Storage, which holds a word, a count and a link to a left and right branch/node. 

![adsoof-project-word-storage](https://i.imgur.com/DEArZM4.png)

I then created an array of this data type. I used the inbuilt hashCode() method to store words indexed by their hashCode to simulate a Hash Table like structure. However, some words may result in the same hash code. In order to deal with this, I used the left and right node links that I had in my data type to create a Binary Tree Structure. So, words that result in the same hash code would then be attached to the initial word. words that are lexicographically less will be stored on left branches and longer words will be stored on right branches. 

![adsoof-project-binary-tree](https://i.imgur.com/QTUL2X8.png)

If a word is added more than once than the count of that Word Storage is increased and vice versa. If however, the word is removed and the count is 0, then the word storage is removed and the binary tree is shifted appropriately. 

## Installation

```
git clone https://github.com/Yaseen121/ADSOOFProject.git
cd ADSOOFProject
Open project in chosen IDE.
```
