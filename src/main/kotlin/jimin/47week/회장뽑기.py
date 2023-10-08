'''
bfs를 활용해서 문제를 해결했다
'''

import sys
from collections import deque

n = int(sys.stdin.readline())
friends = [[] for _ in range(n + 1)]
info = [0 for _ in range(n + 1)]
while True:
    a, b = map(int, sys.stdin.readline().split())
    if a == -1 and b == -1:
        break
    friends[a].append(b)
    friends[b].append(a)

def bfs(target):
    global n, info
    queue = deque([[target, 0]])
    visited = [0 for _ in range(n + 1)]

    while queue:
        now, num = queue.popleft()
        for i in friends[now]:
            if i != target and not visited[i]:
                queue.append([i, num + 1])
                visited[i] = num + 1

    return num


for i in range(1, n + 1):
    info[i] = bfs(i)

mini = min(info[1:])
print(mini, info.count(mini))

for i in range(1, n + 1):
    if info[i] == mini:
        print(i, end=" ")
