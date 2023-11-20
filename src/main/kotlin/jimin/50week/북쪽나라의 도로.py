'''

https://dodobow.tistory.com/35

근데 왜 1과 1로 돌고 난 후의 max만 확인하면 될까?
'''

import sys
from collections import deque

roads = [[] for _ in range(10_001)]
while True:
    try:
        a, b, c = map(int, sys.stdin.readline().split())
        roads[a].append([b, c])
        roads[b].append([a, c])
    except:
        break


def bfs(start):
    queue = deque([start])

    visited[start] = 0

    while queue:
        now = queue.popleft()

        for i, w in roads[now]:
           if visited[i] == -1:
               visited[i] = visited[now] + w
               queue.append(i)


visited = [-1 for _ in range(10_001)]
bfs(1)
s = visited.index(max(visited))
visited = [-1 for _ in range(10_001)]
bfs(s)
print(max(visited))
