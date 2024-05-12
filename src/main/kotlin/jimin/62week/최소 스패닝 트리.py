'''
https://4legs-study.tistory.com/111
'''

import sys

v, e = map(int, sys.stdin.readline().split())

trees = []
parents = [i for i in range(v + 1)]

for i in range(e):
    a, b, c = map(int, sys.stdin.readline().split())
    trees.append([a, b, c])


def find_root(x):
    if parents[x] == x:
        return x
    parents[x] = find_root(parents[x])
    return parents[x]


trees.sort(key=lambda x: x[2])
result = []

for i in range(e):
    a = find_root(trees[i][0])
    b = find_root(trees[i][1])

    if a == b:
        continue
    else:
        if a < b:
            parents[b] = a
        else:
            parents[a] = b

    result.append(trees[i][2])

    if len(result) == v - 1:
        break

print(sum(result))
