'''
자신과 같은 높이만 bfs를 돈다!
자신보다 높이가 크면 isPeak을 False로 한다.
이때 높이가 크다고 False를 바로 return하면 안된다. -> 같은 높이인 친구들은 한방에 다 돌아야한다
https://chan9.tistory.com/110
'''



import sys
from collections import deque


def bfs(x, y):
    global n, m, ground, visited
    queue = deque([[x, y]])
    visited[x][y] = 1
    dx = [0, 0, -1, -1, -1, 1, 1, 1]
    dy = [1, -1, 1, 0, -1, 1, 0, -1]
    isPeak = True
    while queue:
        nx, ny = queue.popleft()
        now = ground[nx][ny]
        for i in range(8):
            if ground[nx + dx[i]][ny + dy[i]] > now:
                isPeak = False
            elif not visited[nx + dx[i]][ny + dy[i]] and ground[nx + dx[i]][ny + dy[i]] == now:
                queue.append([nx + dx[i], ny + dy[i]])
                visited[nx + dx[i]][ny + dy[i]] = 1
    return isPeak


n, m = map(int, sys.stdin.readline().split())
ground = [[0 for _ in range(m + 2)]]
visited = [[0 for _ in range(m + 2)] for _ in range(n + 2)]
for i in range(n):
    ground.append([0] + list(map(int, sys.stdin.readline().split())) + [0])
ground.append([0 for _ in range(m + 2)])

result = 0
for i in range(1, n + 1):
    for j in range(1, m + 1):
        if not visited[i][j] and ground[i][j]:
            isPeak = bfs(i, j)
            if isPeak:
                result += 1

print(result)