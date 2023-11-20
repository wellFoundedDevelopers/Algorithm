'''
bfs를 이용해서 거리를 탐색함
'''

import sys
from collections import deque

n, m = map(int, sys.stdin.readline().split())
INF = 10_001
nodes = [[] for _ in range(n + 1)]

for i in range(n - 1):
    a, b, d = map(int, sys.stdin.readline().split())
    nodes[a].append([b, d])
    nodes[b].append([a, d])

def findDistance(a, b):
    global n, m
    queue = deque([[a, 0]])
    visited = [0 for _ in range(n + 1)]
    visited[0] = 1
    visited[a] = 1
    result = 0

    while queue:
        now, num = queue.popleft()

        if now == b:
            result = num
            break

        for next, dis in nodes[now]:
            if not visited[next]:
                queue.append([next, dis + num])
                visited[next] = 1

    return result


for i in range(m):
    a, b = map(int, sys.stdin.readline().split())
    result = findDistance(a, b)
    print(result)
