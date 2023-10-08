'''
0-1 너비 우선 탐색 이용!
'''

import sys
from collections import deque
m, n = map(int, sys.stdin.readline().split())
miros = [list(map(int, sys.stdin.readline().rstrip())) for _ in range(n)]

queue = deque([[0, 0, 0]])
visited = [[0 for _ in range(m)] for _ in range(n)]
visited[0][0] = 1

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
result = -1
while queue:
    nx, ny, num = queue.popleft()
    if nx == n - 1 and ny == m - 1:
        result = num
        break

    for i in range(4):
        if 0 <= nx + dx[i] < n and 0 <= ny + dy[i] < m and not visited[nx + dx[i]][ny + dy[i]]:
            new = miros[nx + dx[i]][ny + dy[i]]
            if new == 0:
                queue.appendleft([nx + dx[i], ny + dy[i], num])
            else:
                queue.append([nx + dx[i], ny + dy[i], num + 1])
            visited[nx + dx[i]][ny + dy[i]] = 1

print(result)
